package sample.com.myproxyframework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import sample.com.myproxyframework.model.WeatherInfo;
import sample.com.myproxyframework.net.HttpRequestPresenter;
import sample.com.myproxyframework.net.ModelCallback;
import sample.com.myproxyframework.net.okhttp.OKHttpRequest;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView textView,textView1;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.mytext);
        textView1 = findViewById(R.id.mytext1);
//        HttpRequestPresenter.init(new AsycHttpRequest());
        HttpRequestPresenter.init(new OKHttpRequest());
        Map<String,String> params = new HashMap<>();
        params.put("city","长沙");
        params.put("key","13cb58f5884f9749287abbead9c658f2");

        HttpRequestPresenter.getInstance().get("http://restapi.amap.com/v3/weather/weatherInfo"
                , params, new ModelCallback<WeatherInfo>() {
                    @Override
                    public void onSuccess(WeatherInfo weatherInfo) {
                        Log.e(TAG,weatherInfo.toString());
                        System.out.println(TAG+ weatherInfo.toString());
                        textView.setText(weatherInfo.toString());
                    }

                    @Override
                    public void onFailure(int code, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
        HttpRequestPresenter.getInstance().post("http://restapi.amap.com/v3/weather/weatherInfo"
                , params, new ModelCallback<WeatherInfo>() {
                    @Override
                    public void onSuccess(WeatherInfo weatherInfo) {
                        Log.e(TAG,weatherInfo.toString());
                        System.out.println(TAG+ weatherInfo.toString());
                        textView1.setText(weatherInfo.toString());
                    }

                    @Override
                    public void onFailure(int code, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }
}
