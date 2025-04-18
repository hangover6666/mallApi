package com.zust.mallapi.qo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 国铿
 * @Date: 2025/04/17/10:48
 * @Description:封装分页信息
 */
@Getter
@Setter
public class QueryObject {
    private int pageNum;
    private int pageSize;
}
