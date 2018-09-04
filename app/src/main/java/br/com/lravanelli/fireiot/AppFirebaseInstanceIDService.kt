package br.com.lravanelli.fireiot

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.iid.FirebaseInstanceId


class AppFirebaseInstanceIDService : FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d("FCM Token", "Refreshed token: " + refreshedToken!!)
        sendRegistrationToServer(refreshedToken)
    }

    private fun sendRegistrationToServer(token: String?) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("token")
        myRef.setValue(token)
    }
}