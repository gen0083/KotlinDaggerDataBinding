package jp.gcreate.sample.kotlindaggerdatabinding

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import jp.gcreate.sample.kotlindaggerdatabinding.databases.OrmaHandler
import jp.gcreate.sample.kotlindaggerdatabinding.databases.TestData
import jp.gcreate.sample.kotlindaggerdatabinding.databinding.ActivityMainBinding
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {
    @field:[Inject Named("injectedString")]
    lateinit var injectedString: String

    @field:[Inject Named("contextString")]
    lateinit var contextString: String

    @Inject
    lateinit var orma: OrmaHandler

    @Inject
    lateinit var data: TestData

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        CustomApp.getComponent(this).inject(this)
        binding.simpleText.text = injectedString
        binding.simpleText.text = contextString
        Log.d("test", "from dagger " + data.toString())

        orma.db.insertIntoTestData(TestData(1, "hoge"))
        orma.db.selectFromTestData()
                .executeAsObservable()
                .subscribe { testData -> Log.d("test", "read from db id:" + testData.id + " name:" + testData.name) }
    }
}
