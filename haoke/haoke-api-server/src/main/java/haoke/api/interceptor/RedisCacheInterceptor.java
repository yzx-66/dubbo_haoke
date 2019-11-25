package haoke.api.interceptor;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class RedisCacheInterceptor implements HandlerInterceptor {



    public <T> T getBean(Class<T> clazz,HttpServletRequest request){
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return applicationContext.getBean(clazz);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RedisTemplate redisTemplate=getBean(StringRedisTemplate.class,request);
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        if(!(StringUtils.equalsIgnoreCase(method,"get") || StringUtils.equalsIgnoreCase(requestURI,"/graphql"))){
            return true;
        }

        String redisKey=generateKey(request);
        if(redisTemplate.hasKey(redisKey)){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print(JSON.parse((String) redisTemplate.opsForValue().get(redisKey)));

            // 支持跨域
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Token");
            response.setHeader("Access-Control-Allow-Credentials", "true");

            return false;
        }

        return true;
    }

    public static String generateKey(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        Map<String, String[]> parameterMap = request.getParameterMap();


        ObjectMapper objectMapper=new ObjectMapper();
        try{
            String params=objectMapper.writeValueAsString(parameterMap);
            return requestURI+"::"+method+"::"+params;
        } catch (JsonProcessingException e) {
            return requestURI+"::"+method;
        }

    }
}
