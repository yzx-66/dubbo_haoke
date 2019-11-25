package haoke.api.graphql;

import graphql.schema.DataFetchingEnvironment;

public interface MyDataFetcher {
    String fieldName();

    Object dataFetcher(DataFetchingEnvironment environment);
}
