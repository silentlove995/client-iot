package com.iot.client.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.client.constants.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtTokenUtils {

    public String getUserNameFromToken(){
        return this.getTokenInfo(AppConstant.TOKEN.USER_NAME).toString();
    }

    public String getFullNameFromToken(){
        return this.getTokenInfo(AppConstant.TOKEN.FULL_NAME).toString();
    }

    public Long getUserIdFromToken(){
        return Long.parseLong(this.getTokenInfo(AppConstant.TOKEN.USER_ID).toString());
    }

    public String getEmailFromToken(){return this.getTokenInfo(AppConstant.TOKEN.EMAIL).toString(); }

    public Object getTokenInfo(String key){
        Map<String , Object> additionalInformation = new ObjectMapper().convertValue(com.iot.client.utils.SecurityUtils.getPrincipal() , Map.class);
        return additionalInformation.get(key);
    }

}
