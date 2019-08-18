package io.keyu.dagger.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import io.keyu.dagger.R

/**
 * All the dependencies that do not change throughout the entire app such as retorfit and glide
 */
@Module
abstract class AppModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideRequestOptions(): RequestOptions {
            return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background)
        }

        @Provides
        @JvmStatic
        fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager {
            return Glide.with(application).setDefaultRequestOptions(requestOptions)
        }

        @Provides
        @JvmStatic
        fun provideAppLogoDrawable(application: Application): Drawable {
            return ContextCompat.getDrawable(application, R.drawable.ic_android_material_blue_24dp)!!
        }
    }
}
