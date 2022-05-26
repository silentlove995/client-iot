package com.iot.client.controller.user;

import com.iot.client.builder.DeviceBuilder;
import com.iot.client.dto.DeviceDTO;
import com.iot.client.dto.SystemCardDTO;
import com.iot.client.dto.output.DeviceOutput;
import com.iot.client.service.DeviceService;
import com.iot.client.service.SystemCardService;
import com.iot.client.service.UserService;
import com.iot.client.utils.FormUtil;
import com.iot.client.utils.response.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class UpCardController {

    @Autowired
    DeviceService deviceService;

    @RequestMapping(value = "/user/card" , method = RequestMethod.GET)
    public ModelAndView upCard(@RequestParam Map<String , String > model){
        ModelAndView modelAndView = new ModelAndView("user/card");
        Pageable pageable = FormUtil.toPageable(model);
        PageList<DeviceOutput> result = deviceService.findAll(new DeviceBuilder(), pageable);
        modelAndView.addObject("list", result);
        return modelAndView;
    }
}
