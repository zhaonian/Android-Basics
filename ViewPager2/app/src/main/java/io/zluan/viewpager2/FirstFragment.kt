package io.zluan.viewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.timer_duration.view.*
import kotlin.math.abs
import kotlin.math.max

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var viewpager: ViewPager2

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment("From FirstFragment")
            findNavController().navigate(action)
        }

        val durations = listOf("15", "30", "45", "60", "1", "2", "3", "5")
        viewpager = view.findViewById(R.id.viewPager)
        viewpager.adapter = DurationsAdapter().apply {
            setItems(durations)
        }
        viewPager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
//            isHorizontalFadingEdgeEnabled = true
//            setFadingEdgeLength(-300)
//            offsetLeftAndRight(-500)
//            setPadding(-150, 0, -150, 0)
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            currentItem = 3
        }

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)

//        viewPager.setPageTransformer { page, position ->
//            val viewPager = page.parent.parent as ViewPager2
//            val offset = position * -(2 * offsetPx + pageMarginPx)
//            if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
//                if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
//                    page.translationX = -offset
//                } else {
//                    page.translationX = offset
//                }
//            } else {
//                page.translationY = offset
//            }
//        }
//        viewPager.setPageTransformer(ZoomOutPageTransformer())
        val t = CompositePageTransformer().apply {
            addTransformer(ZoomOutPageTransformer())
            addTransformer { page, position ->
                val viewPager = page.parent.parent as ViewPager2
                val offset = position * -(2 * offsetPx + pageMarginPx)
                if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.translationX = -offset
                    } else {
                        page.translationX = offset
                    }
                } else {
                    page.translationY = offset
                }
            }
        }
        viewpager.setPageTransformer(t)
    }

    class DurationViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) :
                this(LayoutInflater.from(parent.context).inflate(R.layout.timer_duration, parent, false))

        fun bind(category: String) {
            itemView.textHolder.text = category
        }
    }

    class DurationsAdapter : RecyclerView.Adapter<DurationViewHolder>() {
        private var list: List<String> = listOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DurationViewHolder {
            return DurationViewHolder(parent)
        }

        override fun onBindViewHolder(holder: DurationViewHolder, position: Int) {
            holder.bind(list[position])
        }

        fun setItems(list: List<String>) {
            this.list = list
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int = list.size
    }


    class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        val scaleFactor = max(MIN_SCALE, 1 - abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        // Fade the page relative to its size.
                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }

    companion object {
        private const val MIN_SCALE = 0.85f
        private const val MIN_ALPHA = 0.5f
    }
}
