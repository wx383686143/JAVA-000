package org.geekbang.ecommerce.dao;

import org.geekbang.ecommerce.vo.GoodsVO;

public interface IGoodsDao {

    void add(GoodsVO goodsVO);

    GoodsVO get(Long pk);
}
