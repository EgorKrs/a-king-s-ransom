package com.loneliness.client.dao;

import com.loneliness.entity.user.UserData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.client.launcher.Client;


import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ServerUserRequest implements CRUD {
    @Override
    public boolean create(Object user) throws DAOException {
        try {

            Transmission transmission = new Transmission();
            transmission.setCommand("CREATE_USER");
            transmission.setUserData((UserData)user);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }

    @Override
    public Object read(Object user) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_USER");
            transmission.setUserData((UserData)user);
            Client.getOutObject().writeObject(transmission);
            return (UserData) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }

    @Override
    public boolean update(Object user) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("UPDATE_USER");
            transmission.setUserData((UserData)user);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Object user) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("DELETE_USER");
            transmission.setUserData((UserData)user);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }

    }

    public UserData.Type authorize(UserData userData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("AUTHORIZATION_USER");
            transmission.setUserData(userData);
            Client.getOutObject().writeObject(transmission);
            return (UserData.Type) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }

    @Override
    public ConcurrentHashMap<Integer,UserData> receiveAll() throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_ALL_USERS");
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, UserData>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }
    @Override
    public ConcurrentHashMap<Integer,UserData> receiveAllInInterval(Object transmission) throws DAOException {
        try {
            ((Transmission)transmission).setCommand("RECEIVE_ALL_USERS_IN_LIMIT");
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, UserData>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }
    public ConcurrentHashMap<Integer,UserData> findByLoginAndType(UserData userData) throws DAOException {
        Transmission transmission=new Transmission();
        transmission.setUserData(userData);
        transmission.setCommand("FIND_USERS_BY_LOGIN_AND_TYPE");
        try {
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, UserData>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }
}
