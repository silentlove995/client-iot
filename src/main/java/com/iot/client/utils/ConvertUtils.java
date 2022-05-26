package com.iot.client.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.client.utils.error.CustomException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ConvertUtils {

    private String stringValue;

    public ConvertUtils(String stringValue) {
        this.stringValue = stringValue;
    }

    public <T> T toModel(Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(stringValue, tClass);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ConvertUtils of(String jsonValue) {
        return new ConvertUtils(jsonValue);
    }

}