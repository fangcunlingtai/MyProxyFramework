package sample.com.myproxyframework.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * 中间层
 * Created by BenQ on 2018/5/22.
 */

public class HttpRequestPresenter implements HttpRequest {
    private HttpRequest httpRequest;
    private static volatile HttpRequestPresenter ourInstance ;

    public static HttpRequestPresenter getInstance() {
        return ourInstance;
    }

    // 静态代理

    private HttpRequestPresenter( HttpRequest httpRequest) {
        this.httpRequest=httpRequest;
    }

    public static void init(HttpRequest httpRequest){
        if (null == ourInstance){
            synchronized (HttpRequestPresenter.class){
                if (null == ourInstance){
                    ourInstance = new HttpRequestPresenter(httpRequest);
                }
            }
        }
    }
    @Override
    public void get(String url, Map<String, String> params, final ICallback callback) {
        httpRequest.get(url,params,callback);
    }

    @Override
    public void post(String url, Map<String, String> params, ICallback callback) {
        httpRequest.post(url,params,callback);
    }
}
