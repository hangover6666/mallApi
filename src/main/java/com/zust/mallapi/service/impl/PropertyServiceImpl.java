package com.zust.mallapi.service.impl;

import com.zust.mallapi.entity.Property;
import com.zust.mallapi.mapper.PropertyMapper;
import com.zust.mallapi.service.IPropertyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 类别属性表 服务实现类
 * </p>
 *
 * @author 国铿
 * @since 2025-04-18
 */
@Service
public class PropertyServiceImpl extends ServiceImpl<PropertyMapper, Property> implements IPropertyService {

}
