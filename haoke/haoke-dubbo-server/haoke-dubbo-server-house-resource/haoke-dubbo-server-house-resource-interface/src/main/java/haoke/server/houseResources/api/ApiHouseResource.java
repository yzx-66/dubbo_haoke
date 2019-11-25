package haoke.server.houseResources.api;

import haoke.server.commen.vo.PageInfo;
import haoke.server.houseResources.pojo.HouseResources;

public interface ApiHouseResource {

    int insertHouseResource(HouseResources houseResources);

    boolean updateHouseResource(HouseResources houseResources);

    PageInfo<HouseResources> quertHouseResourceList(int pageNum, int pageSize, HouseResources queryCondition);

    HouseResources queryHouseResourceById(Long id);
}
