package main.java;

import main.java.factory.DAOFactory;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        DAOFactory dao = DAOFactory.getDAOFactory(1);
    }
}
