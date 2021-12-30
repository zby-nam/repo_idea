package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    //分页查询广告信息
    public List<PromotionAd> findAllAdByPage();

    //添加广告
    void savePromotionAd(PromotionAd promotionAd);
    //修改广告
    void updatePromotionAd(PromotionAd promotionAd);

    //根据id查询广告信息
    PromotionAd findPromotionAdById(int id);

    //广告状态上下线
    void updatePromotionAdStatus(PromotionAd promotionAd);
}
