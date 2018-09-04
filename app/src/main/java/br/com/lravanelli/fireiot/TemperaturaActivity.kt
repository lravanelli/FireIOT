package br.com.lravanelli.fireiot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_temperatura.*

class TemperaturaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperatura)

        lerTemperatura()
        FirebaseMessaging.getInstance().subscribeToTopic("temperatura");
    }

    private fun lerTemperatura() {
        val database = FirebaseDatabase.getInstance().reference
        database.child("temperatura").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                val temp = p0?.getValue(Double::class.java)
                tvTemperatura.text = temp.toString()
            }
        })
    }
}
