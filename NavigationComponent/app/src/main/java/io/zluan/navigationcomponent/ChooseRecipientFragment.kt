package io.zluan.navigationcomponent


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_choose_recipient.*

/**
 * A simple [Fragment] subclass.
 */
class ChooseRecipientFragment : Fragment(), View.OnClickListener {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.next_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.next_btn -> {
                if (!input_recipient.text.isNullOrEmpty()) {
                    val bundle = bundleOf("recipient" to input_recipient.text.toString())
                    navController.navigate(
                        R.id.action_chooseRecipientFragment_to_specifyAmountFragment,
                        bundle
                    )
                } else {
                    Snackbar.make(v, "Enter a recipient", Snackbar.LENGTH_SHORT).show()
                }
            }
            R.id.cancel_btn -> activity?.onBackPressed()
        }
    }
}
