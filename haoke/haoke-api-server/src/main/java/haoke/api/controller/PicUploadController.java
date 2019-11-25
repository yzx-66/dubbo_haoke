package haoke.api.controller;

import haoke.api.vo.PicUploadResult;
import haoke.api.service.PicUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("pic/upload")
public class PicUploadController {

    @Autowired
    private PicUploadService picUploadService;

    @PostMapping
    public ResponseEntity<PicUploadResult> uploadPic(MultipartFile file){
        if(file==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(picUploadService.uploadPic(file));
    }
}
