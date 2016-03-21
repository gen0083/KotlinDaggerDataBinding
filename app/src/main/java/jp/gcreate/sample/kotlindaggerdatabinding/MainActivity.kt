package jp.gcreate.sample.kotlindaggerdatabinding

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import jp.gcreate.sample.kotlindaggerdatabinding.databases.OrmaDatabase
import jp.gcreate.sample.kotlindaggerdatabinding.databases.TestData
import jp.gcreate.sample.kotlindaggerdatabinding.databinding.ActivityMainBinding
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {
    @field:[Inject Named("injectedString")]
    lateinit var injectedString: String

    @field:[Inject Named("hogeString")]
    lateinit var hogeString: String

    @Inject
    lateinit var orma: OrmaDatabase

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        CustomApp.appComponent.inject(this)
        binding.simpleText.text = hogeString

        orma.createTestData { TestData(1, "hoge") }
        orma.selectFromTestData().orderByIdDesc().executeAsObservable().subscribe {
            Log.d("test", "test data read from orma: ${it.name}");
        }
    }
}
