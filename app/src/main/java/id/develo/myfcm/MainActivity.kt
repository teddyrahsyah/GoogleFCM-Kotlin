package id.develo.myfcm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging
import id.develo.myfcm.firebase.MyFirebaseMessagingService

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val subscribeButton: Button = findViewById(R.id.btn_subscribe)
        subscribeButton.setOnClickListener {
            FirebaseMessaging.getInstance().subscribeToTopic("news")
            val msg = getString(R.string.msg_subscribed)
            Log.d(TAG, msg)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        val logTokenButton: Button = findViewById(R.id.btn_token)
        logTokenButton.setOnClickListener {
            val deviceToken = MyFirebaseMessagingService
            val msg = getString(R.string.msg_token_fmt, deviceToken)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

            Log.d(TAG, "Refreshed token: $deviceToken")
            FirebaseMessaging.getInstance().token.addOnCompleteListener {
                val msgLog = getString(R.string.msg_token_fmt, it)
                Toast.makeText(this, msgLog, Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Refreshed token: $deviceToken")
            }
        }

    }
}