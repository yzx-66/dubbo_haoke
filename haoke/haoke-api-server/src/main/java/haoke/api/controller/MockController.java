package haoke.api.controller;

import haoke.api.config.MockConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mock")
@CrossOrigin
public class MockController {

    @Autowired
    private MockConfig mockConfig;

    @GetMapping("indexMenu")
    @Cacheable(value="indexMenu",keyGenerator="simpleKeyGenerator")
    public String indexMenu(){
        return this.mockConfig.getIndexMenu();
    }

    /**
     * 首页资讯
     * @return
     */
    @GetMapping("index/info")
    @Cacheable(value="indexInfo",keyGenerator="simpleKeyGenerator")
    public String indexInfo() {
        return this.mockConfig.getIndexInfo();
    }
    /**
     * 首页问答
     * @return
     */
    @GetMapping("index/faq")
    @Cacheable(value="indexFaq",keyGenerator="simpleKeyGenerator")
    public String indexFaq() {
        return this.mockConfig.getIndexFaq();
    }
    /**
     * 首页房源信息
     * @return
     */
    @GetMapping("index/house")
    @Cacheable(value="indexHouse",keyGenerator="simpleKeyGenerator")
    public String indexHouse() {
        return this.mockConfig.getIndexHouse();
    }

    /**
     * 查询资讯
     *
     * @param type
     * @return
     */
    @GetMapping("infos/list")
    @Cacheable(value="infosList",keyGenerator="simpleKeyGenerator")
    public String infosList(@RequestParam("type")Integer type) {
        switch (type){
            case 1:
                return this.mockConfig.getInfosList1();
            case 2:
                return this.mockConfig.getInfosList2();
            case 3:
                return this.mockConfig.getInfosList3();
        }
        return this.mockConfig.getInfosList1();
    }
    /**
     * 我的中心
     * @return
     */
    @GetMapping("my/info")
    @Cacheable(value="myInfo",keyGenerator="simpleKeyGenerator")
    public String myInfo() {
        return this.mockConfig.getMy();
    }



}
