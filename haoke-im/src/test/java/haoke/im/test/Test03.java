package haoke.im.test;

import haoke.im.pojo.User;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@RocketMQMessageListener(
        topic = "test_topic",
        consumerGroup = "test_my-consumer",
        selectorExpression = "*")
public class Test03 implements RocketMQListener<User> {


    @Override
    public void onMessage(User user) {
        System.out.println(user);
    }

    @Test
    public void test() throws InterruptedException {
        Thread.sleep(100000L);
    }
}
