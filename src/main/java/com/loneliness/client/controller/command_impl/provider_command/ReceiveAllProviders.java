package com.loneliness.client.controller.command_impl.provider_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiveAllProviders implements Command<Transmission,ConcurrentHashMap<Integer, ProviderData>  > {
    @Override
    public ConcurrentHashMap<Integer,ProviderData>  execute(Transmission request) throws  ControllerException {
        try {
            return ServiceFactory.getInstance().getProviderService().receiveAllElem(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
