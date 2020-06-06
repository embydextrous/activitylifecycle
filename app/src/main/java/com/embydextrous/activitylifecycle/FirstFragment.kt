package com.embydextrous.activitylifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
    @link https://medium.com/@bherbst/the-many-flavors-of-commit-186608a015b1

    commit() -> async
    commitNow() -> sync but does not support addToBackStack()
    commitAllowingStateLoss() -> To commit fragment transaction after onSaveInstanceState() is called on Activity

    empty constructor? Why? Config changes. See [SecondFragment]
    Lifecycle
    onAttach() -> onCreate() -> onCreateView() -> onActivityCreated() -> onStart() -> onResume() -> onPause() -> onStop() -> onDestroyView() -> onDestroy() -> onDetach()

    Fragment A -> Add Fragment B
    B.onAttach() -> B.onCreate() -> B.onCreateView() -> B.onActivityCreated() -> B.onStart() -> B.onResume()
    BackPress
    B.onPause() -> B.onStop() -> B.onDestroyView() -> B.onDestroy() -> B.onDetach()

    Fragment A -> Replace Fragment B (normal)
    B.onAttach() -> B.onCreate() -> A.onPause() -> A.onStop() -> A.onDestroyView() -> B.onCreateView() -> B.onActivityCreated() -> B.onStart() -> B.onResume()
    BackPress
    B.onPause() -> B.onStop() -> B.onDestroyView() -> B.onDestroy() -> B.onDetach() -> A.onCreateView() -> A.onActivityCreated() -> A.onStart() -> A.onResume()

    Fragment A -> Replace Fragment B (optimized)
    B.onAttach() -> B.onCreate() -> B.onCreateView() -> B.onActivityCreated() -> B.onStart() -> B.onResume() -> A.onPause() -> A.onStop() -> A.onDestroyView()
    BackPress
    A.onCreateView() -> A.onActivityCreated() -> A.onStart() -> A.onResume() -> B.onPause() -> B.onStop() -> B.onDestroyView() -> B.onDestroy() -> B.onDetach()

    Activity A hosting Fragment F in its onCreate()
        Activity -> onCreate()
        Fragment -> onAttach()
        Fragment -> onCreate()
        Fragment -> onCreateView()
        Fragment -> onActivityCreated()
        Fragment -> onStart(), Activity -> onStart()
        Activity -> onResume(), Fragment -> onResume()

    Back Press
        Fragment -> onPause(), Activity -> onPause()
        Fragment -> onStop(), Activity -> onStop()
        Fragment -> onDestroyView()
        Fragment -> onDestroy()
        Fragment -> onDetach()
        Activity -> OnDestroy()
 */
class FirstFragment : Fragment() {

    private val TAG = FirstFragment::class.java.simpleName

    companion object {
        fun newInstance(): Fragment = FirstFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }
}
