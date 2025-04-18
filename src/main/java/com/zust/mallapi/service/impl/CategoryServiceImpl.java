package com.zust.mallapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.mallapi.entity.Property;
import com.zust.mallapi.mapper.CategoryMapper;
import com.zust.mallapi.entity.Category;
import com.zust.mallapi.mapper.PropertyMapper;
import com.zust.mallapi.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zust.mallapi.qo.CategoryQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 类别表 服务实现类
 * </p>
 *
 * @author 国铿
 * @since 2025-04-17
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private PropertyMapper propertyMapper;


    @Override
    public IPage<Category> page(CategoryQo qo) {
        //设置条件
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();

        //模糊查询
        wrapper.like(StringUtils.isNotBlank(qo.getQuery()),Category::getCategoryName,qo.getQuery());

        //设置分页信息,他本身是一个接口，所以他本身就是一个接口。
        IPage<Category> page = new Page<>(qo.getPageNum(), qo.getPageSize());

        //查询
        page = baseMapper.selectPage(page,wrapper);

        return page;
    }

    @Override
    public void save(Map<String, Object> map) {
        String categoryName = (String) map.get("categoryName");
        Category category = new Category();
        category.setCategoryName(categoryName);
        //在代码运行.insert(category)，该category中的id就已经创建好了
        baseMapper.insert(category);

        //保存分类信息 思路：map.get("props") ->  map.get("props").list -> 给List<>指定泛型and强转
        //情景1：如果map中没有这个键，会拿到null，否则会出现空指针异常
        //情景2：mao中可能会传空数组，比如{"categoryName":"phone", "props":[]}
        List<String> props = (List<String>) map.get("props");

        //避免空指针异常，判断
        if (props !=null && props.size() >0 ){
            for (String prop : props) {
                //将属性信息添加到property表中
                Property property = new Property();
                property.setPropertyName(prop);
                property.setPropertyCategoryId(category.getCategoryId());
                propertyMapper.insert(property);
            }
        }

    }
}
