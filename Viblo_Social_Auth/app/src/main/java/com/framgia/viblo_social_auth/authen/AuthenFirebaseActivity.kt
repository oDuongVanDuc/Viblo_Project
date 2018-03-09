package com.framgia.viblo_social_auth.authen

import android.app.Fragment
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.framgia.viblo_social_auth.MainActivity
import com.framgia.viblo_social_auth.R

class AuthenFirebaseActivity : AppCompatActivity() {


    private val TAG = "LoginActivity"

    internal var authenFragment: AuthenFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_by_firebase)
        authenFragment = AuthenFragment()
        if (savedInstanceState == null)
        {
            addFragment(authenFragment!!)
        }

    }

    fun addFragment(mfragment: Fragment) {
        val manager = fragmentManager
        //TestFragment testFragment = new TestFragment();
        val transaction = manager.beginTransaction()
        transaction.add(R.id.fragment, mfragment, "test")
        transaction.commit()
    }

    protected fun replaceFragment(
            fragment: Fragment,
            backStackStateName: String?) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment, fragment)
                .addToBackStack(backStackStateName)
                .commit()
    }

    private fun updateUI() {
        val intent = Intent(this@AuthenFirebaseActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}
