package com.framgia.viblo_social_auth

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class LoginSuccessActivity : AppCompatActivity() {

    //Firebase references
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null
    //UI elements
    private var tvFirstName: TextView? = null
    private var tvLastName: TextView? = null
    private var tvEmail: TextView? = null
    private var tvEmailVerifiied: TextView? = null
    var mContext : Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_success)
        initialise()
    }

    private fun initialise() {
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
        tvFirstName = findViewById<View>(R.id.tv_first_name) as TextView
        tvLastName = findViewById<View>(R.id.tv_last_name) as TextView
        tvEmail = findViewById<View>(R.id.tv_email) as TextView
        tvEmailVerifiied = findViewById<View>(R.id.tv_email_verifiied) as TextView
        mContext = this.baseContext
    }

    override fun onStart() {
        super.onStart()
        val mUser = mAuth!!.currentUser
        if(mUser != null){
            val mUserReference = mDatabaseReference!!.child(mUser!!.uid)
            tvEmail!!.text = mUser.email
            tvEmailVerifiied!!.text = mUser.isEmailVerified.toString()
            mUserReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    tvFirstName!!.text = snapshot.child("firstName").value as String
                    tvLastName!!.text = snapshot.child("lastName").value as String
                }
                override fun onCancelled(databaseError: DatabaseError) {}
            })
        }
        else
            Toast.makeText(mContext, "Cant not conect to Firebase real database", Toast.LENGTH_LONG).show()


    }
    //mAuth.signOut()
}
