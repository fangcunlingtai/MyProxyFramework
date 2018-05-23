package sample.com.myproxyframework.net.okhttp;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import sample.com.myproxyframework.net.HttpRequest;
import sample.com.myproxyframework.net.ICallback;

/**
 * Created by BenQ on 2018/5/22.
 */

public class OKHttpRequest implements HttpRequest {

    private OkHttpClient okHttpClient;

    public OKHttpRequest() {
        okHttpClient = new OkHttpClient();

    }




    @Override
    public void get(String url, Map<String, String> params, final ICallback callback) {
        StringBuilder sb = new StringBuilder("?");
        // 拼接get 请求 url
        if (null != params){
            for (Map.Entry<String,String> entry : params.entrySet()){
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        url +=sb.toString();
        Request.Builder requestBuilder = new Request.Builder().url(url);
        requestBuilder.method("GET",null);
        final Request request = requestBuilder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.cacheResponse()){
                    String str = response.cacheResponse().toString();
                    callback.onSuccess(str);
                }else {
                    String str = response.body().string();
                    callback.onSuccess(str);
                }
            }
        });



    }

    @Override
    public void post(String url, Map<String, String> params, final ICallback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        if (null != params) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.cacheResponse()){
                    String str = response.cacheResponse().toString();
                    callback.onSuccess(str);
                }else {
                    String str = response.body().string();
                    callback.onSuccess(str);
                }
            }
        });

    }
}
