package haoke.server.houseResources.api.impl;

import haoke.server.commen.vo.PageInfo;
import haoke.server.houseResources.api.ApiHouseResource;
import org.apache.dubbo.config.annotation.Service;
import haoke.server.houseResources.pojo.HouseResources;
import haoke.server.houseResources.service.HouseResourceService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class ApiHouseResourceImpl implements ApiHouseResource {

    @Autowired
    private HouseResourceService houseResourceService;

    @Override
    public int insertHouseResource(HouseResources houseResources) {
        return houseResourceService.insertHouseResource(houseResources);
    }

    @Override
    public boolean updateHouseResource(HouseResources houseResources) {
        return houseResourceService.updateHouseResource(houseResources);
    }

    @Override
    public PageInfo<HouseResources> quertHouseResourceList(int pageNum, int pageSize, HouseResources queryCondition) {
        return houseResourceService.quertHouseResourceList(pageNum,pageSize,queryCondition);
    }

    @Override
    public HouseResources queryHouseResourceById(Long id) {
        return houseResourceService.queryHouseResourceById(id);
    }


}
