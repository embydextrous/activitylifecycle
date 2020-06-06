package com.embydextrous.activitylifecycle

import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button

/**
 * Lifecycle (Above Android P, Prior to P onSaveInstanceState is called before onStart)
 *
 * Start Activity A
 * A.onCreate() -> A.onStart() -> A.onResume()
 * Start Activity B
 * A.onPause() -> B.onCreate() -> B.onStart() -> B.onResume() -> A.onStop() -> A.onSaveInstanceState()
 * BackPress
 * B.onPause() -> A.onStart() -> A.onRestoreInstanceState() {called only if A was destroyed} -> A.onResume() -> B.onStop() -> B.onDestroy()
 * BackPress
 * A.onPause() -> A.onStop() -> A.onDestroy()
 *
 * Start Activity A
 * A.onCreate() -> A.onStart() -> A.onResume()
 * Rotate
 * A.onPause() -> A.onStop() -> A.onSaveInstanceState() -> A.onDestroy() -> A.onCreate() -> A.onStart() -> A.onRestoreInstanceState() -> A.onResume()
 *
 * In case of [startActivityForResult] when user presses back [onActivityResult] is called before [onStart]
 *
 * Other important methods (Over Android Q where multiple activities can be in resumed state)
 * [onTopResumedActivityChanged]
 * [onWindowFocusChanged] -> Prior to Q trust [onResume], above it [onTopResumedActivityChanged]
 * [onConfigurationChanged] does not recreate on config changes marked in manifest
 * [onMultiWindowModeChanged]
 * [onUserLeaveHint] -> called when home is tapped, not called when someone calls you -> called before onPause
 */
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
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.fragment_second)
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }

        findViewById<Button>(R.id.button4).setOnClickListener {
            startActivity(
                Intent(this, FourthActivity::class.java).apply { putExtra("optimize", false) }
            )
        }

        findViewById<Button>(R.id.button5).setOnClickListener {
            startActivity(
                Intent(this, FourthActivity::class.java).apply { putExtra("optimize", true) }
            )
        }

        findViewById<Button>(R.id.button6).setOnClickListener {
            startActivityForResult(Intent(this, SecondActivity::class.java), 1000)
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
        Log.d(TAG, newConfig.toString())
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean, newConfig: Configuration?) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig)
        Log.d(TAG, "Multiwindow: $isInMultiWindowMode")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult")
    }
}
