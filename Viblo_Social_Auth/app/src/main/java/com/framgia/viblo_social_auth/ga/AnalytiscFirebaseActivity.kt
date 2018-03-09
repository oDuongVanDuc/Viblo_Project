package com.framgia.viblo_social_auth.ga

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.framgia.viblo_social_auth.R
import com.google.firebase.analytics.FirebaseAnalytics
import java.util.*

class AnalytiscFirebaseActivity : AppCompatActivity() {


   var firebaseAnalytics :FirebaseAnalytics? =null
    var foods  = arrayOf("Apple","Banana","Grape","Mango","Orange")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_by_firebase)

        // Obtain the Firebase Analytics instance.
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        val food =  Food()
        food.id = 1
        food.name = foods[1]


        val bundle = Bundle()
        bundle.putInt(FirebaseAnalytics.Param.ITEM_ID, food.id)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, food.name)

        //Logs an app event.
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        //Sets whether analytics collection is enabled for this app on this device.
        firebaseAnalytics.setAnalyticsCollectionEnabled(true)

        //Sets the minimum engagement time required before starting a session. The default value is 10000 (10 seconds). Let's make it 20 seconds just for the fun
        firebaseAnalytics.setMinimumSessionDuration(20000);

        //Sets the duration of inactivity that terminates the current session. The default value is 1800000 (30 minutes).
        firebaseAnalytics.setSessionTimeoutDuration(500);

        //Sets the user ID property.
        firebaseAnalytics.setUserId(String.valueOf(food.getId()))

        //Sets a user property to a given value.
        firebaseAnalytics.setUserProperty("Food", food.getName())


    }

     fun randomIndex() {
        val min = 0
        val max = foods.size- 1
         rand(min,max)
    }

    fun rand(from: Int, to: Int) : Int {
        val rand = Random()
        return rand.nextInt(to - from) + from
    }

}
