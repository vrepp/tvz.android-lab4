package hr.tvz.android.mvprep

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        const val TAG = "FCM Token"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        val intent = Intent(applicationContext, ImageActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        Log.d(TAG, "From: " + remoteMessage!!.from!!)
        Log.d(TAG, "Notification Message Body: " + remoteMessage.notification!!.body!!)
    }

    override fun onNewToken(p0: String?) {
        Log.d(TAG, "Refreshed token: $p0")
        super.onNewToken(p0)
    }
}