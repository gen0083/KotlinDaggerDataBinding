package jp.gcreate.sample.kotlindaggerdatabinding

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.gcreate.sample.kotlindaggerdatabinding.databinding.ActivityMainBinding
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {
    @field:[Inject Named("injectedString")]
    lateinit var injectedString: String

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        CustomApp.getComponent(this).inject(this)
        binding.simpleText.text = injectedString
    }
}
