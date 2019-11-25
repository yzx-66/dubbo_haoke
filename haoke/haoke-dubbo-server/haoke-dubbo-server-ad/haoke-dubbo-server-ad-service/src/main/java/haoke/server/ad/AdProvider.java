package haoke.server.ad;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("haoke.server.ad.mapper")
public class AdProvider {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AdProvider.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
