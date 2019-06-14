package hr.tvz.android.mvprep

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.view.*

class DetailFragment : Fragment() {

    private var item: Beer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            item = it.getParcelable(BeersAdapter.ViewHolder.ITEM_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_details, container, false)
        item?.let {
            bindBeer(it, rootView)
        }

        return rootView
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }

    private fun bindBeer(beer: Beer, view: View) {
        view.beer_name.text = beer.name
        view.beer_brewer.text = beer.brewer
        view.beer_alcohol.text = beer.alcohol
        Picasso.get()
            .load(beer.imageUrl)
            .error(R.mipmap.ic_launcher)
            .into(view.beer_image)

        view.beer_image.setOnClickListener{ v: View? ->
            v?.let {
                val context = it.context
                val showImageIntent = Intent(context, ImageActivity::class.java)
                showImageIntent.putExtra(BeersAdapter.ViewHolder.ITEM_KEY, beer)
                context.startActivity(showImageIntent)
            }
        }
    }
}
