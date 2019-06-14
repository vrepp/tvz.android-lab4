package hr.tvz.android.mvprep

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private var item: Beer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)

        item = intent.getParcelableExtra<Beer>(BeersAdapter.ViewHolder.ITEM_KEY)

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(
                        BeersAdapter.ViewHolder.ITEM_KEY,
                        intent.getParcelableExtra<Beer>(BeersAdapter.ViewHolder.ITEM_KEY)
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_share) {
            actionShare()
        } else if (item?.itemId == R.id.action_open_url) {
            actionOpenUrl()
        }

        return true
    }

    private fun actionShare() {
        item?.let {
            val intent = Intent(it.name)
            sendBroadcast(intent)
        }
    }

    private fun actionOpenUrl() {
        item?.let {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(it.url)
            startActivity(intent)
        }
    }
}
