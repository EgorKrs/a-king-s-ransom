package com.loneliness.server.controller.command_impl.product_command;

import com.loneliness.entity.Product;
import com.loneliness.entity.ProductInStock;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

public class ReceiveProduct implements Command<Product, Product> {
    @Override
    public Product execute(Product request) {
        return ServiceFactory.getInstance().getProductService().receive(request);
    }
}
