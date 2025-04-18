package com.zust.mallapi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zust.mallapi.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zust.mallapi.qo.CategoryQo;

import java.util.Map;

/**
 * <p>
 * 类别表 服务类
 * </p>
 *
 * @author 国铿
 * @since 2025-04-17
 */
public interface ICategoryService extends IService<Category> {

    //条件分页查询的方法,这个方法除了返回当前查询页的信息以外，还要封装，一共多少页，一页多少数据，一共多少数据。
    //所以这个方法，就把controller
    IPage<Category> page(CategoryQo qo);

    void save(Map<String,Object> map);
}
