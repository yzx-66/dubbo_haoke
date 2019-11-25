package haoke.server.ad.service;

import haoke.server.ad.pojo.Ad;
import haoke.server.commen.vo.PageInfo;

public interface AdService {
    PageInfo<Ad> queryAdList(Ad record, Integer page, Integer pageSize);
}
