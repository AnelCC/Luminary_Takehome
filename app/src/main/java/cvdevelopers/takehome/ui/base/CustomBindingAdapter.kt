package mvvm.sample.foods.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import cvdevelopers.takehome.utils.image.CircleTransformation


object CustomBindingAdapter{
    @JvmStatic
    @BindingAdapter("bind:image_url")
    fun loadImage(imageView: ImageView, url: String) {
        Picasso.with(imageView.context).load(url).transform(CircleTransformation()).into(imageView)
        //ImageLoader(provideApplication).loadCircularImage(url,imageView)
    }
}