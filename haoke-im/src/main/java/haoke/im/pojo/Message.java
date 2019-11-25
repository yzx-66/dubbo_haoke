package haoke.im.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "message")
public class Message {


    @Id
    private ObjectId id;
    private String msg;
    /**
     * 消息状态，1-未读，2-已读
     */
    @Indexed
    private Integer status;
    @Field("send_date")
    @Indexed
    private Date sendDate;
    @Field("read_date")
    private Date readDate;
    @Indexed
    private User from;
    @Indexed
    private User to;

}
