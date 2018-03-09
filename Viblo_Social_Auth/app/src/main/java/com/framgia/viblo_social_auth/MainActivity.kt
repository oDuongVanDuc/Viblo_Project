package com.framgia.viblo_social_auth

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.framgia.viblo_social_auth.authen.AuthenFirebaseActivity

class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_authencation -> startActivity(AuthenFirebaseActivity())
            //R.id.btn_authencation -> startActivity(AuthenFirebaseActivity)
            //R.id.btn_authencation -> startActivity(AuthenFirebaseActivity)
            //R.id.btn_database -> addFragment(mPanelFragment, "Change Fragment")
            //R.id.btn_ga -> replaceFragment(mPanelFragment, "Change Fragment")
        }//case R.id.btnLogout:
    }



    private fun startActivity(targetActivity : AppCompatActivity ) {
        //start next activity
        val intent = Intent(this@MainActivity, targetActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    //mAuth.signOut()
}
