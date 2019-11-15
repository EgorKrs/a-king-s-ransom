package com.loneliness.server.controller;

public enum  CommandName {
     WRONG_REQUEST, AUTHORIZATION_USER, CREATE_USER, DELETE_USER, RECEIVE_USER, RECEIVE_ALL_USERS, UPDATE_USER, SHUT_DOWN,
     FIND_USERS_BY_LOGIN_AND_TYPE, RECEIVE_ALL_USERS_IN_LIMIT,

     RECEIVE_ALL_PROVIDERS_IN_LIMIT, CREATE_PROVIDER, DELETE_PROVIDER, RECEIVE_ALL_PROVIDERS, RECEIVE_PROVIDER_DATA,
     UPDATE_PROVIDER, FIND_PROVIDER_BY_LOCATION_AND_RATING, FIND_PROVIDER_BY_LOCATION_RATING_AND_VALUE,

     CREATE_ORDER, DELETE_ORDER, FIND_ALL_ORDERS_BY_DATE_OF_COMPLETION_AND_STATUS, RECEIVE_ALL_ORDERS,
     RECEIVE_ALL_ORDERS_IN_LIMIT, RECEIVE_ORDER_DATA, UPDATE_ORDER, RECEIVE_ALL_CUSTOMER_ORDER_IN_LIMIT,
     SEARCH_FOR_BURNING_ORDERS,CALCULATE_SUM_OF_ORDER,

     CREATE_PRODUCT_IN_STOCK, DELETE_PRODUCT_IN_STOCK, FIND_ALL_PRODUCT_IN_STOCK_BY_NAME_AND_QUANTITY, RECEIVE_ALL_PRODUCT_IN_STOCK,
     RECEIVE_ALL_PRODUCT_IN_STOCK_IN_LIMIT, RECEIVE_PRODUCT_IN_STOCK, UPDATE_PRODUCT_IN_STOCK,

     UPDATE_CUSTOMER_DATA, CREATE_CUSTOMER_DATA, DELETE_CUSTOMER_DATA, FIND_ALL_CUSTOMERS_DATA_BY_NAME_AND_NUMBER_OF_ORDERS,
     RECEIVE_ALL_CUSTOMERS_DATA, RECEIVE_ALL_CUSTOMERS_DATA_IN_LIMIT, RECEIVE_CUSTOMER_DATA,
}
