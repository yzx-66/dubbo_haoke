package haoke.server.commen.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class PageInfo<T> implements Serializable {

    private static final long serialVersionUID = -2105385689859184204L;

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 一页显示的大小
     */
    private Integer pageSize;

    private List<T> records= Collections.emptyList();
}
