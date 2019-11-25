package haoke.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TableResult<T> {

    private List<T> list;
    private Pagination pagination;

}