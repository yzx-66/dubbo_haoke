package haoke.api.service;

import haoke.api.vo.TableResult;
import haoke.api.vo.Pagination;
import haoke.server.commen.vo.PageInfo;
import haoke.server.houseResources.api.ApiHouseResource;
import haoke.server.houseResources.pojo.HouseResources;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class HouseResourceService {

    @Reference(version = "1.0.0")
    private ApiHouseResource apiHouseResource;

    public boolean insertHouseResource(HouseResources houseResources){
        return apiHouseResource.insertHouseResource(houseResources)==1;
    }

    public TableResult<HouseResources> queryHouseResourceList(int pageNum, int pageSize, HouseResources queryCondition){
        PageInfo<HouseResources> houseResourcesPageInfo = apiHouseResource.quertHouseResourceList(pageNum, pageSize, queryCondition);

        Pagination pagination=new Pagination(pageNum,pageSize,houseResourcesPageInfo.getTotal());
        TableResult<HouseResources> tableResult=new TableResult<HouseResources>(houseResourcesPageInfo.getRecords(),pagination);

        return tableResult;
    }


    public Object queryHouseResourceById(Long id) {
        return apiHouseResource.queryHouseResourceById(id);
    }

    public boolean updateHouseResource(HouseResources houseResources){
        return apiHouseResource.updateHouseResource(houseResources);
    }
}
