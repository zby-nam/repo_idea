package com.lagou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVO;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageInfo<Resource> findAllResource(ResourseVO resourseVO) {
        PageHelper.startPage(resourseVO.getCurrentPage(),resourseVO.getPageSize());
        List<Resource> allResource = resourceMapper.findAllResource(resourseVO);
        PageInfo<Resource> resourcePageInfo = new PageInfo<>(allResource);
        return resourcePageInfo;
    }
}
