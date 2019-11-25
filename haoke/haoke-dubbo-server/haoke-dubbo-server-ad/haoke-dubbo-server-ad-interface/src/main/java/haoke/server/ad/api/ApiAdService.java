package haoke.server.ad.api;

import haoke.server.ad.pojo.Ad;
import haoke.server.commen.vo.PageInfo;

public interface ApiAdService {

    PageInfo<Ad> queryAdList(Integer type,Integer page,Integer pageSize);
}
