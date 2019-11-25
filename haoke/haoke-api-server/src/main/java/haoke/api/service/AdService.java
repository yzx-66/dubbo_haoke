package haoke.api.service;

import haoke.server.ad.api.ApiAdService;
import haoke.server.ad.pojo.Ad;
import haoke.server.commen.vo.PageInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class AdService {

    @Reference(version = "1.0.0")
    private ApiAdService apiAdService;

    public PageInfo<Ad> queryAdList(Integer type,Integer page,Integer pageSize){
        return apiAdService.queryAdList(type,page,pageSize);
    }
}
