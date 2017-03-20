package com.bjh.news.okHttp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by baijunhui on 17-3-15.
 */

public abstract class ResponseCallback<T> {
    public Type mClass = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    public abstract void onFail();
    public abstract void onSuccess(T result);
}
