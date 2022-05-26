package com.iot.client.controller.admin;

import com.iot.client.dto.PriceLevelDTO;
import com.iot.client.dto.SystemCardDTO;
import com.iot.client.dto.UserDTO;
import com.iot.client.service.DeviceService;
import com.iot.client.service.PriceLevelService;
import com.iot.client.service.SystemCardService;
import com.iot.client.service.UserService;
import com.iot.client.utils.BuildMapUtils;
import com.iot.client.utils.FormUtil;
import com.iot.client.utils.response.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller(value = "systemCardController")
public class SystemCardController {

    @Autowired
    SystemCardService systemCardService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin/card" , method = RequestMethod.GET)
    public ModelAndView listCard(@RequestParam Map<String , String > model){
        ModelAndView modelAndView = new ModelAndView("admin/systemcard/list");
        Pageable pageable = FormUtil.toPageable(model);
        PageList<SystemCardDTO> result = systemCardService.getAll(pageable);
        if(model.get("price") != null && !model.get("price").equals("")){
            result = systemCardService.findAllByPrice(pageable, Integer.parseInt(model.get("price")));
        }
        modelAndView.addObject("list", result);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/card/edit", method = RequestMethod.GET)
    public ModelAndView createCard(){
        return new ModelAndView("admin/systemcard/edit");
    }

}
