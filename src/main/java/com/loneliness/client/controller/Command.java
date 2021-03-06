package com.loneliness.client.controller;

public interface Command<Type,ReturnType> {
    ReturnType execute(Type request) throws ControllerException;
}
