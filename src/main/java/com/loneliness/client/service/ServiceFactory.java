package com.loneliness.client.service;




public class ServiceFactory {
    private static final ServiceFactory instance=new ServiceFactory();
    private final UserServiceImpl userService=new UserServiceImpl();
    private final ProviderServiceImpl providerService=new ProviderServiceImpl();
    private final OrderServiceImpl orderService=new OrderServiceImpl();
    public static ServiceFactory getInstance() {
        return instance;
    }
    public ProviderServiceImpl getProviderService(){
        return providerService;
    }

    public OrderServiceImpl getOrderService() {
        return orderService;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }
}
