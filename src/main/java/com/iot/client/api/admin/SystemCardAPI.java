package com.iot.client.api.admin;

import com.iot.client.constants.AppConstant;
import com.iot.client.dto.SystemCardDTO;
import com.iot.client.service.SystemCardService;
import com.iot.client.utils.response.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author datdv
 */
@RestController
@RequestMapping("/api/admin/card")
public class SystemCardAPI {

    @Autowired
    private SystemCardService systemCardService;

    /**
     * save
     *
     * @param input :
     * @return :
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody SystemCardDTO input) {
        input = systemCardService.save(input);
        return ResponseEntityBuilder.getBuilder().setDetails(input).setMessage(AppConstant.message.systemCard.SAVE_SUCCESS).build();
    }

    /**
     * updateStatus
     *
     * @param updateStatus :
     * @return :
     */
    @RequestMapping(value = "/status" , method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatus(@RequestBody SystemCardDTO updateStatus){
        systemCardService.updateStatus(updateStatus);
        return ResponseEntityBuilder.getBuilder().setMessage(AppConstant.message.systemCard.UPDATE_STATUS_SUCCESS).build();
    }



}
