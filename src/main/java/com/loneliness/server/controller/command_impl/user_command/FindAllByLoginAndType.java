package com.loneliness.server.controller.command_impl.user_command;

import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class FindAllByLoginAndType implements Command {
    @Override
    public Object execute(Object request) {
        return ServiceFactory.getInstance().getUserService().findAllByLoginAndType(request);
    }
}
