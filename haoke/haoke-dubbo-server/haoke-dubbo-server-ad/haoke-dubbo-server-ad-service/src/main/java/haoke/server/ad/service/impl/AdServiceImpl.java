package haoke.server.ad.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import haoke.server.ad.mapper.AdMapper;
import haoke.server.ad.pojo.Ad;
import haoke.server.ad.service.AdService;
import haoke.server.commen.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdMapper adMapper;

    @Override
    public PageInfo<Ad> queryAdList(Ad record, Integer page, Integer pageSize) {
        IPage<Ad> adIPage=new Page<Ad>(page,pageSize);
        QueryWrapper<Ad> adWrapper=new QueryWrapper<Ad>(record);
        IPage<Ad> selectPage = adMapper.selectPage(adIPage, adWrapper);
        return new PageInfo<Ad>((int) selectPage.getTotal(),page,pageSize,selectPage.getRecords());
    }
}
