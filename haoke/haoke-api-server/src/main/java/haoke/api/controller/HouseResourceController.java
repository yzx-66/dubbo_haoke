package haoke.api.controller;

import haoke.api.service.HouseResourceService;
import haoke.api.vo.TableResult;
import haoke.server.houseResources.pojo.HouseResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("house/resources")
public class HouseResourceController {

    @Autowired
    private HouseResourceService houseResourceService;

    @PostMapping
    public ResponseEntity<Void> insertHouseResource(@RequestBody HouseResources houseResources){
        boolean res=houseResourceService.insertHouseResource(houseResources);
        if(res){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<TableResult<HouseResources>> queryHouseResourceList(@RequestParam(name = "currentPage",defaultValue = "1") int pageNum,
                                                                              @RequestParam(name = "pageSize",defaultValue = "10") int pageSize,
                                                                              HouseResources queryCondition){
        return ResponseEntity.ok(houseResourceService.queryHouseResourceList(pageNum,pageSize,queryCondition));
    }

    @PutMapping
    public ResponseEntity<Void> updateHouseResource(@RequestBody HouseResources houseResources){
        boolean res=houseResourceService.updateHouseResource(houseResources);
        if(res){
            return ResponseEntity.ok(null);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
