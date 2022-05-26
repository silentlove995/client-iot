package com.iot.client.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class FormUtil {

    /**
     * toPageable
     *
     * @param model
     * @return
     * @throws IllegalAccessException
     */
    public static Pageable toPageable(Map<String , String> model) {
        int page = 1;
        int size = 10;

        if (model.get("page") != null && !model.get("page").equals("")) {
            page = Integer.parseInt(model.get("page"));
        }
        if (model.get("size") != null && !model.get("size").equals("")) {
            size = Integer.parseInt(model.get("size"));
        }
        return new PageRequest(page - 1, size);
    }

}
