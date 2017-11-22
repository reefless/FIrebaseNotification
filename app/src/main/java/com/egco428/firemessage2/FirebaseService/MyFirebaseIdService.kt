package com.egco428.firemessage2.FirebaseService

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * Created by 6272user on 11/21/2017 AD.
 */
class MyFirebaseIdService: FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val refreshedToken = FirebaseInstanceId.getInstance().token
        sendRegistrationToServer(refreshedToken!!)
    }

    private fun sendRegistrationToServer(refreshedToken:String){
        Log.d("DEBUG", refreshedToken)

    }
}