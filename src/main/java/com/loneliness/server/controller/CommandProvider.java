package com.loneliness.server.controller;


import com.loneliness.server.controller.command_impl.server_command.ShutDown;
import com.loneliness.server.controller.command_impl.WrongRequest;

import com.loneliness.server.controller.command_impl.user_command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();
    private static final CommandProvider commandProvider=new CommandProvider();
    private CommandProvider(){
        repository.put(CommandName.WRONG_REQUEST,new WrongRequest());
        repository.put(CommandName.AUTHORIZATION_USER,new AuthorizationUser());
        repository.put(CommandName.CREATE_USER,new CreateUser());
        repository.put(CommandName.DELETE_USER,new DeleteUser());
        repository.put(CommandName.RECEIVE_ALL_USERS,new ReceiveAllUsers());
        repository.put(CommandName.RECEIVE_USER,new ReceiveUser());
        repository.put(CommandName.UPDATE_USER,new UpdateUser());
        repository.put(CommandName.SHUT_DOWN,new ShutDown());
    }


    public Map<CommandName, Command> getRepository() {
        return repository;
    }

    public static CommandProvider getCommandProvider() {
        return commandProvider;
    }

    public Command getCommand(String name){
        CommandName commandName;
        Command command;
        name=name.replace(" ","_");
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }
        catch (IllegalArgumentException|NullPointerException e){
            command=repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
