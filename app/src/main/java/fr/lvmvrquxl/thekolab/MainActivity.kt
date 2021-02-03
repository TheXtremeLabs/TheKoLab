package fr.lvmvrquxl.thekolab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.lvmvrquxl.thekolab.home.view.HomeActivity

internal class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.goToHome()
    }

    private fun goToHome() = this.startActivity(
        Intent(this, HomeActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    )
}