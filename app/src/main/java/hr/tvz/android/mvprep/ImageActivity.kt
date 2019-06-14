package hr.tvz.android.mvprep

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.content_image.beer_image

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val item: Beer? = intent.getParcelableExtra<Beer>(BeersAdapter.ViewHolder.ITEM_KEY)
        Picasso.get()
            .load(item?.imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(beer_image)
    }

}
