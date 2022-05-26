package com.iot.client.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebCamController {


    @RequestMapping(value = "/user/webcam" , method = RequestMethod.GET)
    public ModelAndView checkQR(){
        return new ModelAndView("user/webcam");
    }


}
