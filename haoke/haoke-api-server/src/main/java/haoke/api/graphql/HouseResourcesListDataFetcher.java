package haoke.api.graphql;

import graphql.schema.DataFetchingEnvironment;
import haoke.api.service.HouseResourceService;
import haoke.api.vo.TableResult;
import haoke.server.houseResources.pojo.HouseResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HouseResourcesListDataFetcher implements MyDataFetcher {

    @Autowired
    private HouseResourceService houseResourceService;



    @Override
    public String fieldName() {
        return "HouseResourcesList";
    }

    @Override
    public Object dataFetcher(DataFetchingEnvironment environment) {
        Integer page=environment.getArgument("page");
        Integer pageSize=environment.getArgument("pageSize");
        if(page==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        TableResult<HouseResources> houseResourcesTableResult = houseResourceService.queryHouseResourceList(page, pageSize, null);
        return houseResourcesTableResult;
    }
}
