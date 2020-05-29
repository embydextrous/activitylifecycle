package com.embydextrous.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SecondActivity : AppCompatActivity() {

    companion object {
        private val TAG = SecondActivity::class.java.simpleName
    }

    /* Called once to setup UI */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d(TAG, "onCreate")
    }

    /* called when OS decides to kill the activity orientation change */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState")
    }

    /* called when activity becomes visible */
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    /* called when activity becomes interactive */
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    /* called when activity becomes non-interactive */
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    /* called when activity becomes invisible */
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    /* called when OS decides to kill the activity orientation change */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")

    }

    /* called when an activity is destroyed */
    /* when user presses back it is called */
    /* when user presses home it is not called */
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}
