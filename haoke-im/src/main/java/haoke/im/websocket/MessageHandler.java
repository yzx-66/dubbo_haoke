package haoke.im.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import haoke.im.dao.MessageDao;
import haoke.im.pojo.Message;
import haoke.im.pojo.User;
import haoke.im.pojo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MessageHandler extends TextWebSocketHandler {

    private static final Map<Long, WebSocketSession> SESSION_MAP=new HashMap<>();

    @Autowired
    private MessageDao messageDao;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        Map<String, Object> attributes = session.getAttributes();
        Long uid= (Long) attributes.get("uid");
        SESSION_MAP.put(uid,  session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        Long uid = (Long) session.getAttributes().get("uid");
        String payload = message.getPayload();

        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(payload);
        long toId = jsonNode.get("toId").asLong();
        String msg = jsonNode.get("msg").asText();

        Message sendMsg = Message.builder()
                .from(UserData.USER_MAP.get(uid))
                .to(UserData.USER_MAP.get(toId))
                .msg(msg)
                .status(1)
                .sendDate(new Date())
                .build();

        WebSocketSession toUserSession = SESSION_MAP.get(toId);
        if(toUserSession!=null){
            sendMsg.setStatus(2);
            sendMsg.setReadDate(new Date());
            toUserSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(sendMsg)));
        }

        messageDao.saveMessage(sendMsg);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        Map<String, Object> attributes = session.getAttributes();
        Long uid= (Long) attributes.get("uid");
        SESSION_MAP.remove(uid);
    }
}
