package sample.com.myproxyframework.net.async;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.Map;

import cz.msebera.android.httpclient.Header;
import sample.com.myproxyframework.net.HttpRequest;
import sample.com.myproxyframework.net.ICallback;

/**
 * Created by BenQ on 2018/5/22.
 */

public class AsycHttpRequest implements HttpRequest {

    private AsyncHttpClient asyncHttpClient;
    public AsycHttpRequest(){
        asyncHttpClient = new AsyncHttpClient();
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
        asyncHttpClient.get(url,new TextHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.onFailure(statusCode, throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                callback.onSuccess(responseString);
            }

//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                callback.onSuccess(new String(responseBody));
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                callback.onFailure(statusCode, error);
//            }
        });

    }

    @Override
    public void post(String url, Map<String, String> params, final ICallback callback) {
        RequestParams requestParams = new RequestParams();
        if (null != params){
            for (Map.Entry<String,String> entry : params.entrySet()){
                requestParams.put(entry.getKey(),entry.getValue());

            }
        }
        System.out.println("url = "+url);
        asyncHttpClient.post(url,requestParams,new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.onFailure(statusCode, throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                callback.onSuccess(responseString);
            }
        });

    }
}
