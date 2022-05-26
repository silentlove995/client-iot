package com.iot.client.api.admin;

import com.google.zxing.WriterException;
import com.iot.client.constants.AppConstant;
import com.iot.client.dto.UserDTO;
import com.iot.client.dto.output.sqlcustom.UserOutPut;
import com.iot.client.dto.realtime.Device;
import com.iot.client.service.UserService;
import com.iot.client.utils.FormUtil;
import com.iot.client.utils.response.PageList;
import com.iot.client.utils.response.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author datdv
 */
@RestController
@RequestMapping("/api/admin/user")
public class UserAPI {

    @Autowired
    private  UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody UserDTO user) {
        user = userService.save(user);
        return ResponseEntityBuilder.getBuilder().setMessage(AppConstant.message.user.SAVE_USER_SUCCESS).setDetails(user).build();
    }

    @RequestMapping(value = "" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllUser() {
        List<UserDTO> users = userService.getAllUser();
        return ResponseEntityBuilder.getBuilder().setDetails(users).setMessage(AppConstant.message.user.GET_ALL_USER_SUCCESS).build();
    }

    @RequestMapping(value = "/by-condition" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllUser(@RequestParam Map<String , String> model) {
        Pageable pageable = FormUtil.toPageable(model);
        if(model.get("username") != ""){
            PageList<UserOutPut> users = userService.getAllUserByCondition(pageable,model.get("username"));
            return ResponseEntityBuilder.getBuilder().setMessage(AppConstant.message.user.GET_ALL_USER_BY_CONDITION_SUCCESS).setDetails(users).build();
        }
        PageList<UserOutPut> users = userService.getAllUserByCondition(pageable);
        return ResponseEntityBuilder.getBuilder().setMessage(AppConstant.message.user.GET_ALL_USER_BY_CONDITION_SUCCESS).setDetails(users).build();
    }


}
