package test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test01 {

    @Test
    public void test01() throws JsonProcessingException {
        Map<String,Object> datamap=new HashMap<>();
        Map<String,Object> listMap=new HashMap<>();
        List<Object> list1=new ArrayList();
        for(int i=0;i<10;i++){
            list1.add(i);
        }
        listMap.put("list",list1);
        datamap.put("data",listMap);
        ObjectMapper objectMapper=new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(datamap);
        System.out.println(jsonString);
    }

    @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
    class User implements Serializable {

        private static final long serialVersionUID = -493439243433085768L;

        private int id;
        private String username;

        public User(int id,String username){
            this.id=id;
            this.username=username;
        }
    }

    @Test
    public void test02() throws JsonProcessingException {
        User user=new User(1,"111");
        ObjectMapper objectMapper=new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(user));
    }
}
