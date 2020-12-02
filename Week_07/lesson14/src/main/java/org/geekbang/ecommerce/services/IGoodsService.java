package org.geekbang.ecommerce.services;

import org.geekbang.ecommerce.vo.GoodsVO;

import java.util.List;

public interface IGoodsService {

    void add(GoodsVO goodsVO);

    void update(GoodsVO goodsVO);

    GoodsVO get(Long pk);

    List<GoodsVO> getList(GoodsVO goodsVO);

}
