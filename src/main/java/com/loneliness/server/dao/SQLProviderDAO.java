package com.loneliness.server.dao;

import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;

import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SQLProviderDAO implements CRUD<ProviderData> {

    private ProviderData getDataFromResultSet(ResultSet resultSet) throws SQLException {
        ProviderData providerData=new ProviderData();
        providerData.setId(resultSet.getInt("ID"));
        providerData.setName(resultSet.getString("name"));
        providerData.setRating(resultSet.getInt("rating"));
        providerData.setLocation(resultSet.getString("location"));
        providerData.setEmail(resultSet.getString("email"));
        return providerData;
    }

    @Override
    public boolean create(ProviderData provider) {
        String sql = "INSERT providers (ID , name , rating, location, email ) " +
                "VALUES ('" + provider.getId() + "','" + provider.getName() + "','" +
                (provider.getRating() + "','" + provider.getLocation()+ "','" +
                        provider.getEmail() + "');");
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Object read(ProviderData provider) {
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            ResultSet resultSet;
            Statement statement;
            String sql;
            sql = "SELECT * FROM providers WHERE id = '" + provider.getId() + "';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            ProviderData providerData;
            if (resultSet.next()) {
                return getDataFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(ProviderData provider) {
        ResultSet resultSet=null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {

            statement =connection.createStatement();

            String sql = "SELECT * FROM providers WHERE id = " + provider.getId() + ";";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE providers SET " +
                        "name='" + provider.getName() + "'," +
                        "rating='" + provider.getRating() + "'," +
                        "location='" +provider.getLocation() + "'," +
                        "email='" +provider.getEmail() + "' " +
                        "WHERE ID = " + provider.getId() + " ;";
                try {
                    return statement.executeUpdate(sql) == 1;

                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            } else {
                return false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(ProviderData provider) {
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            String sql="DELETE FROM providers WHERE id = '"+provider.getId()+"';";
            Statement statement = connection.createStatement();
            if(statement.executeUpdate(sql) == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public Object receiveAll() {
        ConcurrentHashMap<Integer,ProviderData> data=new ConcurrentHashMap<>();
        try(Connection connection= DataBaseConnection.getInstance().getConnection())  {
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM providers ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            ProviderData providerData;
            while ( resultSet.next()){
                providerData=getDataFromResultSet(resultSet);
                data.put(providerData.getId(),providerData);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Object receiveAllInLimit(Transmission transmission) {
        ConcurrentHashMap<Integer, ProviderData> data = new ConcurrentHashMap<>();
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM providers LIMIT " + transmission.getFirstIndex() + ", " + transmission.getLastIndex() + " ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            ProviderData providerData;
            while (resultSet.next()) {
                providerData=getDataFromResultSet(resultSet);
                data.put(providerData.getId(),providerData);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public Map<Integer,ProviderData> findAllByLocationAndRating(ProviderData providerDataToFind){
        ConcurrentHashMap<Integer,ProviderData> data=new ConcurrentHashMap<>();
        try(Connection connection= DataBaseConnection.getInstance().getConnection())  {
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM providers ";
            boolean whereIsNotUsed=true;
            if(providerDataToFind.getLocation()!=null){
                sql+="WHERE location = '" + providerDataToFind.getLocation() + "' ";
                whereIsNotUsed=false;
            }
            if(providerDataToFind.getRating()!=-1){
                if(whereIsNotUsed) {
                    sql+="WHERE rating = " + providerDataToFind.getRating() + " ";
                }
                else {
                    sql += "AND rating = " +providerDataToFind.getRating() + " ";
                }

            }
            sql+=";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            ProviderData providerData;
            while ( resultSet.next()){
                providerData=getDataFromResultSet(resultSet);
                data.put(providerData.getId(),providerData);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}