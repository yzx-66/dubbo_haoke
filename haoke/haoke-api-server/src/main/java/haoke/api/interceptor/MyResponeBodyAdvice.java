package haoke.api.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haoke.api.controller.GraphQLController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.Duration;

@ControllerAdvice
public class MyResponeBodyAdvice implements ResponseBodyAdvice {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        boolean isGraphql = StringUtils.equalsIgnoreCase(GraphQLController.class.getName(), methodParameter.getExecutable().getDeclaringClass().getName());
        boolean isGet=methodParameter.hasMethodAnnotation(GetMapping.class);

        if(!(isGraphql||isGet)){
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        String redisKey = RedisCacheInterceptor.generateKey(((ServletServerHttpRequest) serverHttpRequest).getServletRequest());
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            String jsonData = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(redisKey,jsonData, Duration.ofMinutes(3));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return o;
    }
}
