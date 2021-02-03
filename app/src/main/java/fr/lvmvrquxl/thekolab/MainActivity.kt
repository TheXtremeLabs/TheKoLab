package fr.lvmvrquxl.thekolab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.home.view.HomeActivity

internal class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.goToHome()
    }

    private fun goToHome() = this.startActivity(HomeActivity.intent(this))
}