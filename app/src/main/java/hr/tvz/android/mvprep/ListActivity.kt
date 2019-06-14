package hr.tvz.android.mvprep

import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.*

class ListActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: BeersAdapter
    private val batteryReceiver = BatteryLowReceiver()
    private var beers = ArrayList<Beer>()

    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        registerReceivers()

        beers = ArrayList(Beers.getBeers())

        if (item_detail_container != null) {
            twoPane = true
        }

        setupRecyclerView(item_list)
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(batteryReceiver)
    }

    private fun registerReceivers() {
        registerReceiver(batteryReceiver, IntentFilter("android.intent.action.BATTERY_LOW"))
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        linearLayoutManager = LinearLayoutManager(this)
        adapter = BeersAdapter(this, beers, twoPane)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }
}
