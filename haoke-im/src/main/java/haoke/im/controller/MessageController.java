package haoke.im.controller;

import haoke.im.pojo.Message;
import haoke.im.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
@CrossOrigin
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Message> getMessages(@RequestParam("fromId")Long fromId,
                                     @RequestParam("toId") Long toId,
                                     @RequestParam(value = "page",
                                             defaultValue = "1") Integer page,
                                     @RequestParam(value = "rows",
                                             defaultValue = "10") Integer rows){
        return messageService.getMessages(fromId,toId,page,rows);
    }
}
