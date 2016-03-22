package jp.gcreate.sample.kotlindaggerdatabinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import jp.gcreate.sample.kotlindaggerdatabinding.databases.OrmaDatabase;
import jp.gcreate.sample.kotlindaggerdatabinding.databases.TestData;
import jp.gcreate.sample.kotlindaggerdatabinding.databinding.ActivityMainBinding;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    @Inject
    @Named("injectedString")
    String injectedString;

    @Inject
    @Named("contextString")
    String contextString;

    @Inject
    OrmaDatabase orma;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        CustomApp.getComponent(this).inject(this);
        binding.simpleText.setText(injectedString);
        binding.simpleText.setText(contextString);

        orma.insertIntoTestData(new TestData(1,"hoge"));
        orma.selectFromTestData().executeAsObservable().subscribe(
                new Action1<TestData>() {
                    @Override
                    public void call(TestData testData) {
                        Log.d("test", "read from db id:" + testData.id + " name:" + testData.name);
                    }
                }
        );
    }
}
