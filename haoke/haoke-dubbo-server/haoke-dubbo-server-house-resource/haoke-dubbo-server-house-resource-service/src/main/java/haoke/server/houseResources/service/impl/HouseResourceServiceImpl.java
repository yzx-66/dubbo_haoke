package haoke.server.houseResources.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import haoke.server.commen.vo.PageInfo;
import haoke.server.houseResources.mapper.HouseResourceMapper;
import haoke.server.houseResources.pojo.HouseResources;
import haoke.server.houseResources.service.HouseResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HouseResourceServiceImpl implements HouseResourceService {

    @Resource
    private HouseResourceMapper houseResourceMapper;

    @Override
    public int insertHouseResource(HouseResources houseResources) {
        if(houseResources==null){
            return -1;
        }
        if(StringUtils.isBlank(houseResources.getTitle())){
            return -1;
        }
        //....

        return houseResourceMapper.insert(houseResources);
    }

    @Override
    public PageInfo<HouseResources> quertHouseResourceList(int pageNum, int pageSize, HouseResources queryCondition) {
        IPage<HouseResources> houseResourcesIPage=new Page<HouseResources>(pageNum,pageSize);
        QueryWrapper<HouseResources> queryWapper=new QueryWrapper(queryCondition);
       // queryWapper.orderByDesc("updated");

        IPage<HouseResources> result = houseResourceMapper.selectPage(houseResourcesIPage, queryWapper);
        return new PageInfo<HouseResources>((int) result.getTotal(),pageNum,pageSize,result.getRecords());
    }

    @Override
    public HouseResources queryHouseResourceById(Long id) {
        return houseResourceMapper.selectById(id);
    }

    @Override
    public boolean updateHouseResource(HouseResources houseResources) {
        return houseResourceMapper.updateById(houseResources)==1;
    }
}
