package com.bjh.news.data;

/**
 * Created by baijunhui on 17-3-17.
 */

public class Result<T> {
    T data;
    int start;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
