package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;

import java.util.List;

public interface PromotionAdService {

    //分页查询广告信息
    public PageInfo<PromotionAd> findAllAdByPage(PromotionAdVo promotionAdVo);

    //添加广告
    void savePromotionAd(PromotionAd promotionAd);
    //修改广告
    void updatePromotionAd(PromotionAd promotionAd);
    //根据id回显广告信息
    PromotionAd findPromotionAdById(int id);

    //修改广告状态信息
    void updatePromotionAdStatus(int id, int status);
}
