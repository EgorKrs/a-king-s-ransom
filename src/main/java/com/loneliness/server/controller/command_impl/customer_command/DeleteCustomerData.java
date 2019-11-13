package com.loneliness.server.controller.command_impl.customer_command;

import com.loneliness.entity.CustomerData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class DeleteCustomerData implements Command<CustomerData,String> {
    @Override
    public String execute(CustomerData request) {
        return ServiceFactory.getInstance().getCustomerDataService().delete(request);
    }
}
