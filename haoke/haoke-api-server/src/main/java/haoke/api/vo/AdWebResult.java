package haoke.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdWebResult {

    private List<?> list;

    private String msg;

    private Integer status;


    public static Map<String,Object> ok(List<?> list, String msg,Integer status){
        AdWebResult adWebResult=new AdWebResult(list,msg,status);
        Map<String,Object> result=new HashMap<>();
        Map<String,Object> dataMap=new HashMap<>();
        Map<String,Object> metaMap=new HashMap<>();

        dataMap.put("list",adWebResult.getList());
        result.put("data",dataMap);

        metaMap.put("msg",adWebResult.getMsg());
        metaMap.put("status",adWebResult.getStatus());
        result.put("meta",metaMap);

        return result;
    }
}
