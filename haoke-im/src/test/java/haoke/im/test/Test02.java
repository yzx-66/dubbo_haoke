package haoke.im.test;


import haoke.im.pojo.User;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test02 {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void test01(){
        rocketMQTemplate.setMessageQueueSelector((List<MessageQueue> mqs, Message msg, Object arg)->{
            /**
             * mqs：要发送消息的topic下的所有消息队列集合
             * msg：发送的消息
             * arg：发送消息时传递的参数 通过该参数指定发送到哪个队列
             */
            Integer queueNum =(Integer) arg % 4;
            return mqs.get(queueNum);
        });
        for(int i=0;i<100;i++){
            String msgStr = "type: "+i%4+" value:" + i;
            rocketMQTemplate.syncSendOrderly("haoke_order_topic",msgStr,String.valueOf(i));
            //rocketMQTemplate.convertAndSend("test_topic"i);
        }
    }
}
