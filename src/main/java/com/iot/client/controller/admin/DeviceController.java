package com.iot.client.controller.admin;

import com.iot.client.builder.DeviceBuilder;
import com.iot.client.dto.DeviceDTO;
import com.iot.client.dto.definebuilder.SearchDeviceRequest;
import com.iot.client.dto.output.DeviceOutput;
import com.iot.client.dto.output.sqlcustom.UserOutPut;
import com.iot.client.service.DeviceService;
import com.iot.client.service.PriceLevelService;
import com.iot.client.service.UserService;
import com.iot.client.utils.BuildMapUtils;
import com.iot.client.utils.FormUtil;
import com.iot.client.utils.response.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class DeviceController {

    @Autowired
    private BuildMapUtils buildMapUtils;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private PriceLevelService priceLevelService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/device" , method = RequestMethod.GET)
    public ModelAndView listPage(@ModelAttribute("model") SearchDeviceRequest searchDeviceRequest){
        ModelAndView modelAndView = new ModelAndView("admin/device/list");
        Map<String , String> model = buildMapUtils.convertDtoToMap(searchDeviceRequest);
        Pageable pageable = FormUtil.toPageable(model);
        DeviceBuilder deviceBuilder = initSearchDeviceBuilder(model);
        PageList<DeviceOutput> result = deviceService.findAll(deviceBuilder, pageable);
        modelAndView.addObject("list", result);
        return modelAndView;
    }

    private DeviceBuilder initSearchDeviceBuilder (Map<String , String > model) {
        return DeviceBuilder.builder()
                .name(model.get("name"))
                .code(model.get("code"))
                .qrCode(model.get("qr_code"))
                .usages(model.get("usages") != null ? Integer.parseInt(model.get("usages")) : null)
                .fullName(model.get("fullname"))
                .username(model.get("username"))
                .email(model.get("email"))
                .numberPhone(model.get("numberphone"))
                .wallet(model.get("wallet") != null ? Integer.parseInt(model.get("wallet")) : null)
                .build();
    }

    @RequestMapping(value = "/admin/device/edit", method = RequestMethod.GET)
    public ModelAndView createDevice(@RequestParam Map<String , String > model){
            ModelAndView modelAndView = new ModelAndView("admin/device/edit");
        DeviceDTO deviceDTO = new DeviceDTO();
        if(model.get("id") != null){
            Long id = Long.parseLong(model.get("id"));
            deviceDTO = deviceService.findById(id);
        }
        Pageable pageable = FormUtil.toPageable(model);
        PageList<UserOutPut> userOutputs = userService.getAllUserByCondition(pageable);
        modelAndView.addObject("deviceDTO", deviceDTO);
        modelAndView.addObject("userOutput", userOutputs);
        return modelAndView;
    }
}
