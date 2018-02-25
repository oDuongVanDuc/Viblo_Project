package com.framgia.viblo_social_auth

import android.app.Activity
import android.app.Fragment
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

/**
 * Created by dvduc on 2/20/2018.
 */

public class SuccessFragment : Fragment {

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
    //Firebase references
    private var aAuthenActivity: AuthenFirebaseActivity? = null

    public constructor() : super() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_login_success, container, false)
        initialise()
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(aAuthenActivity)
    }

    private fun initialise() {
        aAuthenActivity = AuthenFirebaseActivity()
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
        tvFirstName = activity.findViewById<View>(R.id.tv_first_name) as TextView
        tvLastName = activity.findViewById<View>(R.id.tv_last_name) as TextView
        tvEmail = activity.findViewById<View>(R.id.tv_email) as TextView
        tvEmailVerifiied = activity.findViewById<View>(R.id.tv_email_verifiied) as TextView
        mContext = activity.baseContext


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