package fr.lvmvrquxl.thekolab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.lvmvrquxl.thekolab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        super.setContentView(view)
    }
}