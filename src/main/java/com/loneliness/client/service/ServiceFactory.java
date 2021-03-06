package com.loneliness.client.service;




public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final UserServiceImpl userService = new UserServiceImpl();
    private final ProviderServiceImpl providerService = new ProviderServiceImpl();
    private final OrderServiceImpl orderService = new OrderServiceImpl();
    private final ProductInStockServiceImpl productInStockService = new ProductInStockServiceImpl();
    private final CustomerDataServiceImpl customerDataService = new CustomerDataServiceImpl();
    private final ReportLogic reportLogic = new ReportLogic();
    private final ProductServiceImpl productService = new ProductServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public ProviderServiceImpl getProviderService() {
        return providerService;
    }


    public ProductInStockServiceImpl getProductInStockService() {
        return productInStockService;
    }

    public OrderServiceImpl getOrderService() {
        return orderService;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public CustomerDataServiceImpl getCustomerDataService() {
        return customerDataService;
    }

    public ReportLogic getReportLogic() {
        return reportLogic;
    }

    public ProductServiceImpl getProductService() {
        return productService;
    }
}
