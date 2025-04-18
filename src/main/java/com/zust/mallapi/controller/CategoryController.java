package com.zust.mallapi.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zust.mallapi.entity.Category;
import com.zust.mallapi.common.Result;
import com.zust.mallapi.qo.CategoryQo;
import com.zust.mallapi.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 类别表 前端控制器
 * </p>
 *
 * @author 国铿
 * @since 2025-04-17
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;



    //分类信息列表列表请求
    @GetMapping("/list")
    public Result list(@RequestBody CategoryQo qo){
        IPage<Category> page = categoryService.page(qo);
        return Result.success("分类信息查询成功",page);
    }


    @GetMapping("insert")
    public Result insert(@RequestBody Map<String,Object> map){  //string就相当于实体类的类名，object就相当于实体类里面的属性
        categoryService.save(map);
        return Result.success("分类信息添加成功");
    }
}

