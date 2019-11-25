package haoke.im.dao.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import haoke.im.dao.MessageDao;
import haoke.im.pojo.Message;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<Message> findListByFromAndTo(Long fromId, Long toId, Integer page, Integer rows) {
        Criteria forward_1 = Criteria.where("to.id").is(toId).and("from.id").is(fromId);
        Criteria forward_2 = Criteria.where("to.id").is(fromId).and("from.id").is(toId);
        Criteria criteria=new Criteria().orOperator(forward_1,forward_2);

        PageRequest pagerequest = PageRequest.of(page-1, rows, Sort.by(Sort.Direction.ASC,"send_date"));
        Query query=new Query(criteria).with(pagerequest);


        return mongoTemplate.find(query, Message.class);
    }

    @Override
    public Message findMessageById(String id) {
        return mongoTemplate.findById(id,Message.class);
    }

    @Override
    public UpdateResult updateMessageState(ObjectId id, Integer status) {
        Query query=Query.query(Criteria.where("id").is(id));
        Update update=new Update();
        update.set("status",status);
        if(status==1){
            update.set("send_date",new Date());
        }
        if(status==2){
            update.set("read_date",new Date());
        }
        return mongoTemplate.updateMulti(query,update,Message.class);
    }

    @Override
    public Message saveMessage(Message message) {
        return mongoTemplate.save(message);
    }

    @Override
    public DeleteResult deleteMessage(String id) {
        Query query=Query.query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query,Message.class);
    }
}
