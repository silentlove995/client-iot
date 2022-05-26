package com.iot.client.controller.admin;

import com.iot.client.builder.HistoryDeviceSearchBuilder;
import com.iot.client.dto.output.sqlcustom.HistoryDeviceOutPut;
import com.iot.client.service.HistoryDeviceService;
import com.iot.client.utils.BuildMapUtils;
import com.iot.client.utils.FormUtil;
import com.iot.client.utils.response.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class HistoryDeviceController {

    @Autowired
    HistoryDeviceService historyDeviceService;

    @Autowired
    BuildMapUtils buildMapUtils;

    @RequestMapping(value = "/admin/history/device" , method = RequestMethod.GET)
    public ModelAndView listHistoruDevice(@ModelAttribute("model") HistoryDeviceSearchBuilder historyDeviceSearchBuilder){
        ModelAndView modelAndView = new ModelAndView("admin/history/device/list");
        Map<String , String> model = buildMapUtils.convertDtoToMap(historyDeviceSearchBuilder);
        Pageable pageable = FormUtil.toPageable(model);
        PageList<HistoryDeviceOutPut> result = historyDeviceService.findAllHistoryDeviceByCondition(initHistory(model), pageable);
        modelAndView.addObject("list", result);
        return modelAndView;
    }

    private HistoryDeviceSearchBuilder initHistory(Map<String , String> model){
        return HistoryDeviceSearchBuilder.builder()
                .name(model.get("name"))
                .code(model.get("code"))
                .email(model.get("email"))
                .numberPhone(model.get("numberphone"))
                .price(model.get("price") != null ? Integer.parseInt(model.get("price")) : null)
                .timeForm(model.get("timeForm") != null ? Integer.parseInt(model.get("timeForm")) : null)
                .timeTo(model.get("timeTo") != null ? Integer.parseInt(model.get("timeTo")) : null)
                .startDate(model.get("startDate"))
                .startToDate(model.get("startToDate"))
                .endDate(model.get("endDate"))
                .endToDate(model.get("endToDate"))
                .totalTime(model.get("totalTime"))
                .totalPayment(model.get("totalPayment") != null ? Integer.parseInt(model.get("totalPayment")) : null)
                .build();
    }
}
