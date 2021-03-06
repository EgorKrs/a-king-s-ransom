package com.loneliness.client.dao;

import com.loneliness.client.launcher.Client;
import com.loneliness.entity.CustomerData;

import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.transmission.Transmission;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class ServerCustomerDataRequest implements CRUD<CustomerData,Transmission,String,ConcurrentHashMap<Integer,CustomerData>>{
    @Override
    public String create(CustomerData customerData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("CREATE_CUSTOMER_DATA");
            transmission.setCustomerData(customerData);
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }

    @Override
    public CustomerData read(CustomerData  customerData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_CUSTOMER_DATA");
            transmission.setCustomerData(customerData);
            Client.getOutObject().writeObject(transmission);
            return (CustomerData) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }

    @Override
    public String update(CustomerData customerData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("UPDATE_CUSTOMER_DATA");
            transmission.setCustomerData(customerData);
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }

    @Override
    public String delete(CustomerData customerData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("DELETE_CUSTOMER_DATA");
            transmission.setCustomerData(customerData);
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }

    }

    @Override
    public ConcurrentHashMap<Integer,CustomerData> receiveAll() throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_ALL_CUSTOMERS_DATA");
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer,CustomerData>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }
    @Override
    public ConcurrentHashMap<Integer,CustomerData> receiveAllInInterval(Transmission transmission) throws DAOException {
        try {
            transmission.setCommand("RECEIVE_ALL_CUSTOMERS_DATA_IN_LIMIT");
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, CustomerData>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }
    public ConcurrentHashMap<Integer,CustomerData> findAllCustomersDataByNameAndNumberOfOrders(CustomerData customerData) throws DAOException {
        Transmission transmission=new Transmission();
        transmission.setCustomerData(customerData);
        transmission.setCommand("FIND_ALL_CUSTOMERS_DATA_BY_NAME_AND_NUMBER_OF_ORDERS");
        try {
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer,CustomerData>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }

}
