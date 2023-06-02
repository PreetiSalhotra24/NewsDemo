package web.connect.newsdemo.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import web.connect.newsdemo.R
import web.connect.newsdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController : NavController?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        observeNavigationChanges()

        setContentView(binding.root)

    }

    private fun observeNavigationChanges() {

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHost.navController

        navController?.addOnDestinationChangedListener { controller, destination, args ->

            when (destination.id) {
                R.id.splashFragment -> {
                    binding.inHeaders.root.visibility = View.GONE
                }
                else -> {
                    binding.apply{
                        inHeaders.root.visibility = View.VISIBLE
                        binding.inHeaders.tvHeading.text = destination.label
                    }

                }
            }
        }
    }

}
