package haoke.api.graphql;

import graphql.schema.DataFetchingEnvironment;
import haoke.api.service.HouseResourceService;
import haoke.server.houseResources.api.ApiHouseResource;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HouseResourcesDataFetcher implements MyDataFetcher {

    @Autowired
    private HouseResourceService houseResourceService;

    @Override
    public String fieldName() {
        return "HouseResources";
    }

    @Override
    public Object dataFetcher(DataFetchingEnvironment environment) {
        Long id=environment.getArgument("id");
        return houseResourceService.queryHouseResourceById(id);
    }
}
