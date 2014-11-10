package com.soap.customerprofile.util;

/**
 * Created by steven on 2014/11/09.
 */
public class Utils {
    public static <T> T ifNotNull(T data, T existingData) {
        return data == null ? existingData : data;
    }
}

