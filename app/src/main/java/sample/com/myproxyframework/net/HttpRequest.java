package sample.com.myproxyframework.net;

import java.util.Map;

/**
 * 负责网络请求, 在thread  里面执行，不能拿到返回结果
 * 需要一个 ICallback 返回请求数据
 * Created by BenQ on 2018/5/22.
 */

public interface HttpRequest {
    void get(String url, Map<String ,String> params,ICallback callback);
    void post(String url, Map<String ,String> params,ICallback callback);
}
