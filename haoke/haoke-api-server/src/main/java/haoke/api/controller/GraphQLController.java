package haoke.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionInput;
import graphql.GraphQL;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("graphql")
@CrossOrigin
public class GraphQLController {

    @Autowired
    private GraphQL graphQL;

    private static ObjectMapper MAPPER=new ObjectMapper();

    @GetMapping
    @ResponseBody
    //@Cacheable(value="postQuery",keyGenerator="simpleKeyGenerator")
    public Map<String,Object> getQuery(@RequestBody Map<String,Object> params) throws IOException {
        System.out.println("1!");
        return parseQuery(params);
    }

    @PostMapping
    @ResponseBody
    //@Cacheable(value="postQuery",keyGenerator="simpleKeyGenerator")
    public Map<String,Object> postQuery(@RequestBody Map<String,Object> params) throws IOException {
        return parseQuery(params);
    }

    public Map<String,Object> parseQuery(Map<String,Object> params) throws IOException {
        String query= (String) params.get("query");
        if(StringUtils.isBlank(query)){
            Map<String, Object> error = new HashMap<>();
            error.put("status", 500);
            error.put("msg", "查询出错");
            return error;
        }

        String operationName= (String) params.get("operationName");
        Map<String ,Object> variables= (Map<String, Object>) params.get("variables");

        ExecutionInput executionInput=ExecutionInput.newExecutionInput()
                .query(query)
                .operationName(operationName)
                .variables(variables)
                .build();

        return graphQL.execute(executionInput).toSpecification();
    }

}
