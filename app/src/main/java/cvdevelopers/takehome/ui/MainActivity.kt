package cvdevelopers.takehome.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cvdevelopers.githubstalker.R
import cvdevelopers.takehome.LuminaryTakeHomeApplication
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        LuminaryTakeHomeApplication.appComponent.inject(this)
    }

}
