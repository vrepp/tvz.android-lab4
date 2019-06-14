package hr.tvz.android.mvprep

import android.app.Application
import android.util.Log
import com.raizlabs.android.dbflow.config.FlowManager

class MVPRepApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FlowManager.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d("TESTIS", "testis levi")
        FlowManager.destroy()
    }
}