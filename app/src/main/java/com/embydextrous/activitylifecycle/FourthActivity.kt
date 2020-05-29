package com.embydextrous.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class FourthActivity : AppCompatActivity() {

    private val TAG = FourthActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_fourth)

        val optimize = intent.extras?.getBoolean("optimize") ?: false

        val fragment = supportFragmentManager.findFragmentByTag(FirstFragment::class.java.simpleName)
        if (fragment != null) {
            supportFragmentManager.beginTransaction().show(fragment)
        } else {
            supportFragmentManager.beginTransaction()
                .addToBackStack(FirstFragment::class.java.simpleName)
                .add(
                    R.id.container,
                    FirstFragment.newInstance(),
                    FirstFragment::class.java.simpleName
                )
                .commit()
        }

        findViewById<Button>(R.id.button1).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .addToBackStack(SecondFragment::class.java.simpleName)
                .add(
                    R.id.container,
                    SecondFragment.newInstance(),
                    SecondFragment::class.java.simpleName
                )
                .commit()
                findViewById<Button>(R.id.button1).isClickable = false
                findViewById<Button>(R.id.button2).isClickable = false
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setAllowOptimization(optimize)
                .addToBackStack(SecondFragment::class.java.simpleName)
                .replace(
                    R.id.container,
                    SecondFragment.newInstance(),
                    SecondFragment::class.java.simpleName
                )
                .commit()
                findViewById<Button>(R.id.button1).isClickable = false
                findViewById<Button>(R.id.button2).isClickable = false
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

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}
