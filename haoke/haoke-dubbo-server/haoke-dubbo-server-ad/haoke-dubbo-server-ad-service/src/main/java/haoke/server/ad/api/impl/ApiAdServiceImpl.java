package haoke.server.ad.api.impl;

import haoke.server.ad.api.ApiAdService;
import haoke.server.ad.pojo.Ad;
import haoke.server.ad.service.AdService;
import haoke.server.commen.vo.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class ApiAdServiceImpl implements ApiAdService {

    @Autowired
    private AdService adService;


    @Override
    public PageInfo<Ad> queryAdList(Integer type, Integer page, Integer pageSize) {
        Ad record=new Ad();
        record.setType(type);
        return adService.queryAdList(record, page,pageSize);
    }
}
