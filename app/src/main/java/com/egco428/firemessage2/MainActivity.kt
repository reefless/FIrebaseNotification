package com.egco428.firemessage2

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.egco428.firemessage2.FirebaseService.Config

class MainActivity : AppCompatActivity() {
    var mRegistation: BroadcastReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRegistation = object: BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                if(intent!!.action == Config.STRPUSH){
                    val message = intent.getStringExtra("message")
                    Log.d("Notification", message)
                    showNotification("EGCI428", message)
                }
            }
        }
    }
    private fun showNotification(title:String, message:String){
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(applicationContext)
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pIntent)

        val notificationManager = baseContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, builder.build())
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistation)
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistation, IntentFilter("Registration Complete"))
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistation, IntentFilter(Config.STRPUSH))
    }
}
