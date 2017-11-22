package com.egco428.firemessage2.FirebaseService

import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Created by 6272user on 11/21/2017 AD.
 */
class MyFirebaseMessagingService:FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessaging: RemoteMessage?) {
        handleNotification(remoteMessaging!!.notification.body)
    }
    private fun handleNotification(body:String?){
        val pushNotification = Intent(Config.STRPUSH)
        pushNotification.putExtra("message", body)
        LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification)
    }
}