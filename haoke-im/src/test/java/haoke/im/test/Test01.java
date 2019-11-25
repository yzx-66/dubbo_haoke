package haoke.im.test;

import haoke.im.dao.MessageDao;
import haoke.im.pojo.Message;
import haoke.im.pojo.User;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test01 {

   @Autowired
   private MessageDao messageDAO;

    @Test
    public void test01(){
        Message message = Message.builder()
                .id(ObjectId.get())
                .msg("你好")
                .sendDate(new Date())
                .status(1)
                .from(new User(1001L, "zhangsan"))
                .to(new User(1002L, "lisi"))
                .build();
        this.messageDAO.saveMessage(message);

        message = Message.builder()
                .id(ObjectId.get())
                .msg("你也好")
                .sendDate(new Date())
                .status(1)
                .to(new User(1001L, "zhangsan"))
                .from(new User(1002L, "lisi"))
                .build();
        this.messageDAO.saveMessage(message);

        message = Message.builder()
                .id(ObjectId.get())
                .msg("我在学习开发IM")
                .sendDate(new Date())
                .status(1)
                .from(new User(1001L, "zhangsan"))
                .to(new User(1002L, "lisi"))
                .build();
        this.messageDAO.saveMessage(message);

        message = Message.builder()
                .id(ObjectId.get())
                .msg("那很好啊！")
                .sendDate(new Date())
                .status(1)
                .to(new User(1001L, "zhangsan"))
                .from(new User(1002L, "lisi")).build();
        this.messageDAO.saveMessage(message);
        System.out.println("ok");
    }

    @Test
    public void test02(){
        List<Message> listByFromAndTo = messageDAO.findListByFromAndTo(1001L, 1002L, 1, 10);
        System.out.println(listByFromAndTo);
    }


}
