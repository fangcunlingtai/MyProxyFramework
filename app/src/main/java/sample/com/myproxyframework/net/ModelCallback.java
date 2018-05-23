package sample.com.myproxyframework.net;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;

/**
 * Created by BenQ on 2018/5/22.
 */

public abstract class ModelCallback<T> implements ICallback {
    @Override
    public void onSuccess(String result) {
        Class<? extends T> geneticClass = getGeneticClass(this);
        T t = new Gson().fromJson(result,geneticClass);
        onSuccess(t);
    }

    protected Class<? extends T> getGeneticClass(Object object){
      //获得带有泛型的直接父类
      Type  genericSuperclass= object.getClass().getGenericSuperclass();

      //ParameterizedType 带参数的类型 泛型
        // getActualTypeArguments 参数的类型 泛型类型  [0]  就是 T
       return (Class<? extends T>) ((ParameterizedType)genericSuperclass).getActualTypeArguments()[0];
    };


    public abstract void onSuccess(T t);

    @Override
    public void onFailure(int code, Throwable throwable) {

    }
}
