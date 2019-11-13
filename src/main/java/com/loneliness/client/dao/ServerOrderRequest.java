package com.loneliness.client.dao;

import com.loneliness.client.launcher.Client;
import com.loneliness.entity.orders.OrderCustomerData;
import com.loneliness.entity.orders.OrderData;

import com.loneliness.entity.transmission.Transmission;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ServerOrderRequest implements CRUD{
    @Override
    public boolean create(Object orderData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("CREATE_ORDER");
            transmission.setOrderData((OrderData)  orderData);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }

    @Override
    public OrderData read(Object  orderData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_ORDER");
            transmission.setOrderData( (OrderData) orderData);
            Client.getOutObject().writeObject(transmission);
            return (OrderData) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }

    @Override
    public boolean update(Object orderData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("UPDATE_ORDER");
            transmission.setOrderData((OrderData) orderData);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Object orderData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("DELETE_ORDER");
            transmission.setOrderData((OrderData) orderData);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }

    }

    public ConcurrentHashMap<Integer,OrderCustomerData> receiveAllCustomerOrderInLimit(Transmission transmission) throws DAOException {
        try {
//            Transmission transmission = new Transmission();
//            transmission.setCommand("RECEIVE_ALL_CUSTOMER_ORDER_IN_LIMIT");
//            transmission.setOrderCustomerData(orderData);
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer,OrderCustomerData>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }

    }

    @Override
    public ConcurrentHashMap<Integer,OrderData> receiveAll() throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_ALL_ORDERS");
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer,OrderData>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }
    @Override
    public ConcurrentHashMap<Integer,OrderData> receiveAllInInterval(Object transmission) throws DAOException {
        try {
            ((Transmission)transmission).setCommand("RECEIVE_ALL_ORDERS_IN_LIMIT");
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, OrderData>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }
    public ConcurrentHashMap<Integer,OrderData> findAllOrdersByDateOfCompletionAndStatus(OrderData orderData) throws DAOException {
        Transmission transmission=new Transmission();
        transmission.setOrderData(orderData);
        transmission.setCommand("FIND_ALL_ORDERS_BY_DATE_OF_COMPLETION_AND_STATUS");
        try {
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, OrderData>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }
}