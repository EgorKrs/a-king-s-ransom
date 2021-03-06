package com.loneliness.client.controller.command_impl.product_in_stock_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.ProductInStock;
import com.loneliness.entity.transmission.Transmission;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiveAllProductInStockInLimit implements Command<Transmission, ConcurrentHashMap<Integer, ProductInStock>> {
    @Override
    public ConcurrentHashMap<Integer, ProductInStock>  execute(Transmission request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getProductInStockService().receiveAllElemInLimit(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
