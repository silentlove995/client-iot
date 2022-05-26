package com.iot.client.controller.admin;

import com.iot.client.builder.PriceLevelBuilder;
import com.iot.client.dto.PriceLevelDTO;
import com.iot.client.service.DeviceService;
import com.iot.client.service.PriceLevelService;
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

import java.util.Map;

@Controller(value = "priceLevelController")
public class PriceLevelController {

    @Autowired
    private PriceLevelService priceLevelService;


    @RequestMapping(value = "/admin/pricelevel", method = RequestMethod.GET)
    public ModelAndView listPagePrice(@RequestParam Map<String, String> model) {
        ModelAndView modelAndView = new ModelAndView("admin/pricelevel/list");
        Pageable pageable = FormUtil.toPageable(model);
        PageList<PriceLevelDTO> result = priceLevelService.findAllPriceLevel(pageable);
        if (model.get("price") != null && !model.get("price").equals("")) {
            result = priceLevelService.findByPrice(Integer.parseInt(model.get("price")), pageable);
        }
        modelAndView.addObject("list", result);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/pricelevel/edit", method = RequestMethod.GET)
    public ModelAndView priceLevelUpdate(@RequestParam Map<String, String> model) {
        ModelAndView modelAndView = new ModelAndView("admin/pricelevel/edit");
        PriceLevelDTO priceLevelDTO = new PriceLevelDTO();
        if (model.get("id") != null) {
            Long id = Long.parseLong(model.get("id"));
            priceLevelDTO = priceLevelService.findOne(id);
        }
        modelAndView.addObject("priceLevelDTO", priceLevelDTO);
        return modelAndView;
    }

    private PriceLevelBuilder initSearchPriceLevelBuilder(Map<String, String> model) {
        PriceLevelBuilder builder = new PriceLevelBuilder();
        builder.setPrice(model.get("price") != null ? Integer.parseInt(model.get("price")) : null);
        builder.setType(model.get("type"));
        return builder;
    }

}
