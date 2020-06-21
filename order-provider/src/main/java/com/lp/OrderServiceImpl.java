package com.lp;

/**
 * @auther lp
 * @date 2020/6/20 0020 13:38
 */
//目的：使用这个注解扫描的方法自动发布
@LpRemoteService
public class OrderServiceImpl implements IOrderService {
    @Override
    public String queryOrderList() {
        return "查询订单列表";
    }

    @Override
    public String queryById(String id) {
        return "通过Id查询订单";
    }
}
