package sample.com.myproxyframework.net;

/**
 * Created by BenQ on 2018/5/22.
 */

public  interface ICallback {
    void onSuccess(String result);
    void onFailure(int code,Throwable throwable);
}
