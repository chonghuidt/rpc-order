package com.lp;

/**
 * @auther lp
 * @date 2020/6/20 0020 16:47
 */
@LpRemoteService
public class TestServiceImpl implements TestService  {
    @Override
    public String say() {

        return "hello,注解调用";
    }
}
