package com.lp;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
     IOrderService iOrderService = new OrderServiceImpl();
     RpcProxyServer rpcProxyServer = new RpcProxyServer();
     rpcProxyServer.publish(iOrderService,8080);
    }
}
