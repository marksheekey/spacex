package co.uk.happyapper.lushspacex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.uk.happyapper.lushspacex.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            title = "Falcon 9 Launches"
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}