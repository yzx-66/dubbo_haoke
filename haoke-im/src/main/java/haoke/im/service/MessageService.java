package haoke.im.service;

import haoke.im.pojo.Message;

import java.util.List;

public interface MessageService {
    List<Message> getMessages(Long fromId, Long toId, Integer page, Integer rows);
}
