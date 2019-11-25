package haoke.server.houseResources;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan(basePackages = "haoke.server.houseResources.mapper")
public class HouseResourceProvider {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HouseResourceProvider.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
