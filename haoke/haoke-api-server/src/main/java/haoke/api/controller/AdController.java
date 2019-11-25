package haoke.api.controller;

import haoke.api.service.AdService;
import haoke.api.vo.AdWebResult;
import haoke.server.ad.pojo.Ad;
import haoke.server.commen.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ad")
@CrossOrigin
public class AdController {

    @Autowired
    private AdService adService;

    @GetMapping
    public Map<String, Object> queryAdList(){
        PageInfo<Ad> adPageInfo = adService.queryAdList(1, 1, 3);
        List<Ad> ads = adPageInfo.getRecords();

        List orginMapList=new ArrayList();
        for(Ad ad:ads){
            Map originMap=new HashMap();
            originMap.put("original",ad.getUrl());
            orginMapList.add(originMap);
        }

        return AdWebResult.ok(orginMapList,"成功",200);
    }
}
