package com.iot.client.api.admin;

import com.iot.client.constants.AppConstant;
import com.iot.client.dto.PriceLevelDTO;
import com.iot.client.service.PriceLevelService;
import com.iot.client.utils.FormUtil;
import com.iot.client.utils.response.PageList;
import com.iot.client.utils.response.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author datdv
 */
@RestController
@RequestMapping("/api/admin/price")
public class PriceLevelAPI {

    @Autowired
    private PriceLevelService priceLevelService;
    /**
     * save
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody PriceLevelDTO input) {
        input = priceLevelService.save(input);
        return ResponseEntityBuilder.getBuilder().setDetails(input).setMessage(AppConstant.message.priceLevel.SAVE_SUCCESS).build();
    }

    /**
     * update
     *
     * @param id
     * @param update
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestBody PriceLevelDTO update) {
        update = priceLevelService.update(id, update);
        return ResponseEntityBuilder.getBuilder().setDetails(update).setMessage(AppConstant.message.priceLevel.UPDATE_SUCCESS).build();
    }

    /**
     * getAllPriceLevel
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllPriceLevel(@RequestParam Map<String , String> model) {
        Pageable pageable = FormUtil.toPageable(model);
        PageList<PriceLevelDTO> result = priceLevelService.findAllPriceLevel(pageable);
        return ResponseEntityBuilder.getBuilder().setMessage(AppConstant.message.priceLevel.GET_LIST_SUCCESS).setDetails(result).build();
    }

    /**
     * findOne
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
        PriceLevelDTO result = priceLevelService.findOne(id);
        return ResponseEntityBuilder.getBuilder().setDetails(result).setMessage(AppConstant.message.priceLevel.GET_SUCCESS).build();
    }

    /**
     * delete
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam("ids") List<Long> ids) {
        priceLevelService.delete(ids);
        return ResponseEntityBuilder.getBuilder().setMessage(AppConstant.message.priceLevel.DELETE_SUCCESS).build();
    }

    @RequestMapping(value = "/by-condition", method = RequestMethod.GET)
    public ResponseEntity<?> findByType(@RequestParam("priceLevelType") String priceLevelType) {
        List<PriceLevelDTO> result = priceLevelService.findByType(priceLevelType);
        return ResponseEntityBuilder.getBuilder().setDetails(result).setMessage(AppConstant.message.priceLevel.GET_SUCCESS).build();
    }

}
