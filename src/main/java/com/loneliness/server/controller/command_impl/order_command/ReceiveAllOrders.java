package com.loneliness.server.controller.command_impl.order_command;

import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class ReceiveAllOrders implements Command<Transmission> {
    @Override
    public Object execute(Transmission request) {
        return ServiceFactory.getInstance().getOrderService().receiveAllElem();
    }
}