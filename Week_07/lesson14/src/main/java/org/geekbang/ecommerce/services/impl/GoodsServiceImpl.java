package org.geekbang.ecommerce.services.impl;

import org.geekbang.ecommerce.common.DynamicDataSource;
import org.geekbang.ecommerce.common.ReadOnly;
import org.geekbang.ecommerce.dao.IGoodsDao;
import org.geekbang.ecommerce.services.IGoodsService;
import org.geekbang.ecommerce.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;

    @Override
    public void add(GoodsVO goodsVO) {
        goodsDao.add(goodsVO);
    }

    @Override
    public void update(GoodsVO goodsVO) {

    }

    @Override
    @ReadOnly
    public GoodsVO get(Long pk) {
        System.out.println(DynamicDataSource.getDataSource());
        return goodsDao.get(pk);
    }

    @Override
    @ReadOnly
    public List<GoodsVO> getList(GoodsVO goodsVO) {
        return null;
    }
}
