package haoke.server.houseResources.service;

import haoke.server.commen.vo.PageInfo;
import haoke.server.houseResources.pojo.HouseResources;

public interface HouseResourceService {

    int insertHouseResource(HouseResources houseResources);

    PageInfo<HouseResources> quertHouseResourceList(int pageNum, int pageSize, HouseResources queryCondition);

    HouseResources queryHouseResourceById(Long id);

    boolean updateHouseResource(HouseResources houseResources);
}
