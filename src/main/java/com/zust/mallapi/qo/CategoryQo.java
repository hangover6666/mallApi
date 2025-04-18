package com.zust.mallapi.qo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 国铿
 * @Date: 2025/04/17/10:57
 * @Description:封装分类的条件数据，分类数据（前端发来的）
 */
@Getter
@Setter
public class CategoryQo extends QueryObject{
    private String query;

}
