package org.geekbang.ecommerce.dao;

import org.geekbang.ecommerce.vo.UserVO;

import java.util.List;

public interface IUserDao {

    public void batchAdd(List<UserVO> list);
}
