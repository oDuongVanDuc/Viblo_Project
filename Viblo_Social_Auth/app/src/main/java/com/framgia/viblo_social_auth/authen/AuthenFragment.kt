package com.framgia.viblo_social_auth.authen

import android.app.Activity
import android.app.Fragment
import android.app.ProgressDialog
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
import com.framgia.viblo_social_auth.R
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by dvduc on 2/20/2018.
 */

public class AuthenFragment: Fragment {

    private val TAG = "LoginActivity"
    //global variables
    private var email: String? = null
    private var password: String? = null
    //UI elements
    private var tvForgotPassword: TextView? = null
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var btnLogin: Button? = null
    private var btnCreateAccount: Button? = null
    private var mProgressBar: ProgressDialog? = null
    //Firebase references
    private var mAuth: FirebaseAuth? = null
    private var aAuthenActivity: AuthenFirebaseActivity? = null

    public constructor() : super() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_authen, container, false)
        initialise()
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(aAuthenActivity)
    }

    private fun initialise() {
        aAuthenActivity = AuthenFirebaseActivity()
        tvForgotPassword = activity.findViewById<View>(R.id.tv_forgot_password) as TextView
        etEmail = activity.findViewById<View>(R.id.et_email) as EditText
        etPassword = activity.findViewById<View>(R.id.et_password) as EditText
        btnLogin = activity.findViewById<View>(R.id.btn_login) as Button
        btnCreateAccount = activity.findViewById<View>(R.id.btn_register_account) as Button
        mProgressBar = ProgressDialog(this.activity)
        mAuth = FirebaseAuth.getInstance()
        tvForgotPassword!!
                .setOnClickListener { startActivity(Intent(aAuthenActivity,
                        ForgotPasswordActivity::class.java)) }
        btnCreateAccount!!
                .setOnClickListener { startActivity(Intent(aAuthenActivity,
                        CreateAccountActivity::class.java)) }
        btnLogin!!.setOnClickListener { loginUser() }


    }
    private fun loginUser() {
        email = etEmail?.text.toString()
        password = etPassword?.text.toString()
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            mProgressBar!!.setMessage("Registering User...")
            mProgressBar!!.show()
            Log.d(TAG, "Logging in user.")
            mAuth!!.signInWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener(this.activity) { task ->
                        mProgressBar!!.hide()
                        if (task.isSuccessful) {
                            // Sign in success, update UI with signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            //updateUI()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(aAuthenActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
        } else {
            Toast.makeText(aAuthenActivity, "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }
}