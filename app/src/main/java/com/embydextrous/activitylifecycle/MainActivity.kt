package com.embydextrous.activitylifecycle

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    /* Called once to setup UI */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")

        findViewById<Button>(R.id.button1).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Sample Alert")
                .setNeutralButton("Dismiss") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
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

    /* Usable over Android Q for initializations like camera */
    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        super.onTopResumedActivityChanged(isTopResumedActivity)
        Log.d(TAG, "Top resumed $isTopResumedActivity")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        newConfig.toString()
    }
}
