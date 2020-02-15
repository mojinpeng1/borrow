package com.mjp.borrow.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>Description：查询参数  分页+条件</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2019/12/24 9:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Query implements Serializable {
    private Paging paging;
    private Map<String, String> condition;
}
