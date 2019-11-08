package com.loneliness.server.controller;

public enum  CommandName {
     WRONG_REQUEST, AUTHORIZATION_USER, CREATE_USER, DELETE_USER, RECEIVE_USER, RECEIVE_ALL_USERS, UPDATE_USER, SHUT_DOWN,
     FIND_USERS_BY_LOGIN_AND_TYPE, RECEIVE_ALL_USERS_IN_LIMIT,

     RECEIVE_ALL_PROVIDERS_IN_LIMIT, CREATE_PROVIDER, DELETE_PROVIDER, RECEIVE_ALL_PROVIDERS, RECEIVE_PROVIDER_DATA,
     UPDATE_PROVIDER, FIND_PROVIDER_BY_LOCATION_AND_RATING
}
