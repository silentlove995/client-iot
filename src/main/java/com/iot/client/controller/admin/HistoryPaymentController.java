package com.iot.client.controller.admin;

import com.iot.client.builder.HistoryPaymentBuilder;
import com.iot.client.dto.output.sqlcustom.HistoryPaymentOutPut;
import com.iot.client.service.HistoryPaymentService;
import com.iot.client.utils.BuildMapUtils;
import com.iot.client.utils.FormUtil;
import com.iot.client.utils.response.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Objects;

@Controller
public class HistoryPaymentController {

    @Autowired
    HistoryPaymentService historyPaymentService;

    @Autowired
    BuildMapUtils buildMapUtils;

    @RequestMapping(value = "/admin/history/payment" , method = RequestMethod.GET)
    public ModelAndView listHistoryPayment(@ModelAttribute("model") HistoryPaymentBuilder historyPaymentBuilder){
        ModelAndView modelAndView = new ModelAndView("admin/history/payment/list");
        Map<String , String> model = buildMapUtils.convertDtoToMap(historyPaymentBuilder);
        Pageable pageable = FormUtil.toPageable(model);
        PageList<HistoryPaymentOutPut> result = historyPaymentService.findAllHistoryPayment(initHistory(model), pageable);
        modelAndView.addObject("list", result);
        return modelAndView;
    }

    private HistoryPaymentBuilder initHistory(Map<String , String> model){
        return HistoryPaymentBuilder.builder()
                .createdDateFrom(model.get("createdDateFrom"))
                .createdDateTo(model.get("createdDateTo"))
                .paymentType(model.get("paymentType"))
                .totalFrom(Objects.nonNull(model.get("totalFrom")) ? Long.parseLong(model.get("totalFrom")) : null)
                .totalTo(Objects.nonNull(model.get("totalTo")) ? Long.parseLong(model.get("totalTo")) : null)
                .username(model.get("username"))
                .email(model.get("email"))
                .numberPhone(model.get("numberPhone"))
                .build();
    }
}
