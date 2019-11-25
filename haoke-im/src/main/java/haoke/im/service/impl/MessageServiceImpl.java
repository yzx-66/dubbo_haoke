package haoke.im.service.impl;

import haoke.im.dao.MessageDao;
import haoke.im.pojo.Message;
import haoke.im.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public List<Message> getMessages(Long fromId, Long toId, Integer page, Integer rows) {
        return messageDao.findListByFromAndTo(fromId,toId,page,rows);
    }
}
