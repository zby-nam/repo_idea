package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVO;

import java.util.List;

public interface ResourceMapper {

    //资源分页&多条件查询
    public List<Resource> findAllResource(ResourseVO resourseVO);
}
