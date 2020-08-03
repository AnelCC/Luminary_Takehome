package cvdevelopers.takehome.utils.image

import android.app.Application
import android.widget.ImageView
import com.squareup.picasso.Picasso
import cvdevelopers.takehome.api.RandomUserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageLoader @Inject constructor(context: Application) {

    private val picasso = Picasso.Builder(context).build()

    fun loadCircularImage (url: String, imageView: ImageView) {
        val rc = picasso.load(url)
        rc.transform(CircleTransformation())
        rc.into(imageView)
    }
}