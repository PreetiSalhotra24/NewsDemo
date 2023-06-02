package web.connect.newsdemo.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import web.connect.newsdemo.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private var splashTimeOut = 3000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)

        getStartScreenFromSplashScreen()

        return binding.root
    }

    private fun getStartScreenFromSplashScreen() {
        try {
            Handler(Looper.myLooper()!!).postDelayed({
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToAllNewsFragment()
                )
            }, splashTimeOut.toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}