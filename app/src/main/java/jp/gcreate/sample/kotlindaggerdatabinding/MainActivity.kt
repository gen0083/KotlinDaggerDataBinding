package jp.gcreate.sample.kotlindaggerdatabinding

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {
    @field:[Inject Named("injectedString")]
    lateinit var injectedString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CustomApp.appComponent.inject(this)

        val text = findViewById(R.id.simple_text) as TextView
        text.text = injectedString
    }
}
