package hr.tvz.android.mvprep

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.beer_item.view.*

class BeersAdapter(
    private val parentActivity: ListActivity,
    private val beers: ArrayList<Beer>,
    private val twoPane: Boolean
) : RecyclerView.Adapter<BeersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.beer_item, p0, false)
        return ViewHolder(view, parentActivity, twoPane)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val beer = beers[p1]
        p0.bindBeer(beer)
    }

    override fun getItemCount(): Int = beers.size


    class ViewHolder(private val view: View, private val parentActivity: ListActivity, private val twoPane: Boolean) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private var beer: Beer? = null

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (twoPane) {
                val fragment = DetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ITEM_KEY, beer)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, DetailsActivity::class.java)
                intent.putExtra(ITEM_KEY, beer)
                v.context.startActivity(intent)
            }
        }

        fun bindBeer(beer: Beer) {
            this.beer = beer
            view.beer_name.text = beer.name
            view.beer_alcohol.text = beer.alcohol
            Picasso.get()
                .load(beer.imageUrl)
                .resize(40, 40)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(view.beer_image)
        }

        companion object {
            const val ITEM_KEY = "BEER"
        }
    }
}