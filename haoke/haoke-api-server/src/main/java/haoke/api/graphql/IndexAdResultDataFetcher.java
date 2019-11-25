package haoke.api.graphql;

import graphql.schema.DataFetchingEnvironment;
import haoke.api.service.AdService;
import haoke.api.vo.ad.IndexAdResult;
import haoke.api.vo.ad.IndexAdResultData;
import haoke.server.ad.pojo.Ad;
import haoke.server.commen.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class IndexAdResultDataFetcher implements MyDataFetcher {

    @Autowired
    private AdService adService;

    @Override
    public String fieldName() {
        return "IndexAdList";
    }

    @Override
    public Object dataFetcher(DataFetchingEnvironment environment) {
        PageInfo<Ad> adPageInfo = adService.queryAdList(1, 1, 3);
        List<Ad> ads = adPageInfo.getRecords();
        List<IndexAdResultData> originalMapList=new ArrayList<>();

        for(Ad ad:ads){
            Map<String,Object> originalMap=new HashMap<>();
            originalMapList.add(new IndexAdResultData(ad.getUrl()));
        }

        return  new IndexAdResult(originalMapList);
    }
}
