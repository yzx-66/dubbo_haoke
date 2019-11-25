package haoke.im.controller;


import haoke.im.pojo.Message;
import haoke.im.pojo.User;
import haoke.im.pojo.UserData;
import haoke.im.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Map<String,Object>> getUsers(@RequestParam("fromId")Long fromId){
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<Long, User> userEntry : UserData.USER_MAP.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", userEntry.getValue().getId());
            map.put("avatar", "https://yzx-haoke.oss-cn-hangzhou.aliyuncs.com/ad_pic/%E5%A4%B4%E5%83%8F.jpg");
            map.put("from_user", fromId);
            map.put("info_type", null);
            map.put("to_user", map.get("id"));
            map.put("username", userEntry.getValue().getUsername());
            // 获取最后一条消息 TODO 没有读取最新消息，而是读取了最早的那个消息
            List<Message> messages = this.messageService.getMessages(fromId,
                    userEntry.getValue().getId(), 1, 1);

            if (messages != null && !messages.isEmpty()) {
                Message message = messages.get(0);
                map.put("chat_msg", message.getMsg());
                map.put("chat_time", message.getSendDate().getTime());
            }
            result.add(map);
        }
        return result;
    }
}
