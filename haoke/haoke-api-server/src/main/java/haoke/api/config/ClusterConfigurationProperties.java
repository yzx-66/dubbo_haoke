package haoke.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Data
public class ClusterConfigurationProperties {

    private List<String> nodes;

    private Integer maxRedirects;

}
