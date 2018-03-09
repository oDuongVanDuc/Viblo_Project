package com.framgia.viblo_social_auth.authen

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.framgia.viblo_social_auth.R
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by dvduc on 2/20/2018.
 */

public class ForgotPasswordFragment : Fragment {

    private val TAG = "ForgotPasswordActivity"
    //UI elements
    private var etEmail: EditText? = null
    private var btnSubmit: Button? = null
    //Firebase references
    private var mAuth: FirebaseAuth? = null
    private var aAuthenActivity: AuthenFirebaseActivity? = null

    public constructor() : super() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_forgot_password, container, false)
        initialise()
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(aAuthenActivity)
    }

    private fun initialise() {
        aAuthenActivity = AuthenFirebaseActivity()
        etEmail = activity.findViewById<View>(R.id.et_email) as EditText
        btnSubmit = activity.findViewById<View>(R.id.btn_submit) as Button
        mAuth = FirebaseAuth.getInstance()
        btnSubmit!!.setOnClickListener { sendPasswordResetEmail() }
    }

    private fun sendPasswordResetEmail() {
        val email = etEmail?.text.toString()
        if (!TextUtils.isEmpty(email)) {
            mAuth!!
                    .sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val message = "Email sent."
                            Log.d(TAG, message)
                            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                            updateUI()
                        } else {
                            Log.w(TAG, task.exception!!.message)
                            Toast.makeText(activity, "No user found with this email.", Toast.LENGTH_SHORT).show()
                        }
                    }
        } else {
            Toast.makeText(activity, "Enter Email", Toast.LENGTH_SHORT).show()
        }
    }
    private fun updateUI() {
        val intent = Intent(activity, AuthenFirebaseActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}

}