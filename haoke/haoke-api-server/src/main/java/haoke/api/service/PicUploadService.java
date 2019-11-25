package haoke.api.service;

import com.aliyun.oss.OSSClient;
import haoke.api.config.AliyunConfig;
import haoke.api.vo.PicUploadResult;
import org.apache.commons.lang3.ArrayUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PicUploadService {

    @Autowired
    private OSSClient oSSClient;

    @Autowired
    private AliyunConfig aliyunConfig;

    private final String[] subffixs=new String[]{"png","jpg","jpeg","gif","bmp"};

    public PicUploadResult uploadPic(MultipartFile file){
        PicUploadResult result=new PicUploadResult();
        String filename = file.getOriginalFilename();
        String fileSubbfix=filename.substring(filename.lastIndexOf(".")+1);

        if(!ArrayUtils.contains(subffixs,fileSubbfix)){
            result.setStatus("error");
            return result;
        }

        DateTime dateTime = new DateTime();
        String path=dateTime.toString("yyyy")+"/"+dateTime.toString("MM")+"/"+dateTime.toString("dd")+"/"+file.getName()+"_"+System.currentTimeMillis()+"."+fileSubbfix;
        try {
            oSSClient.putObject(aliyunConfig.getBucketName(),path,file.getInputStream());
        } catch (IOException e) {
            result.setStatus("error");
            return result;
        }

        result.setStatus("done");
        result.setName(aliyunConfig.getUrlPrefix()+path);
        result.setUid(String.valueOf(System.currentTimeMillis()));

        return result;
    }

}
