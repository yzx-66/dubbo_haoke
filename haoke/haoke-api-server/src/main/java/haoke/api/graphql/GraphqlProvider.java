package haoke.api.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Component
public class GraphqlProvider {

    private GraphQL graphQL;

    @Autowired
    private List<MyDataFetcher> myDataFetchers;

    @Bean
    private GraphQL graphQL(){
        return this.graphQL;
    }

    //实现对GraphQL对象的初始化
    @PostConstruct
    public void init() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:haoke.graphqls");
        this.graphQL = GraphQL.newGraphQL(buildGraphQLSchema(file)).build();

    }

    private GraphQLSchema buildGraphQLSchema(File file) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(file);
        return new SchemaGenerator().makeExecutableSchema(typeRegistry, buildWiring());
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("HaokeQuery", builder -> {
                            for (MyDataFetcher myDataFetcher : myDataFetchers) {
                                builder.dataFetcher(myDataFetcher.fieldName(),
                                        environment -> myDataFetcher.dataFetcher(environment));
                            }
                            return builder;
                        }

                )
                .build();
    }


}
