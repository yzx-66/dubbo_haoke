package haoke.im.jms;

import haoke.im.pojo.User;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.util.List;

@RocketMQMessageListener(
        topic = "test_topic",
        consumerGroup = "test_my-consumer",
        selectorExpression = "*",
        messageModel = MessageModel.CLUSTERING,
        consumeMode = ConsumeMode.ORDERLY
        )
@Component
public class TestListner implements  RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void onMessage(MessageExt messageExt) {
        System.out.println(Thread.currentThread().getName() + " onMessage: " + new String(messageExt.getBody()));
    }


    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext){
                System.out.println(Thread.currentThread().getName() + " prepareStart: " + new String(list.get(0).getBody()));
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
    }

}

