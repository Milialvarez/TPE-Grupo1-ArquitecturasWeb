package main.java;

import main.java.factory.DAOFactory;
import main.java.utils.HelperMySQL;

import java.sql.Connection;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        DAOFactory dao = DAOFactory.getDAOFactoryOnlyInstance(1);
        assert dao != null;
        //ej 1
        Connection c = HelperMySQL.getConnection();
        if (c != null) HelperMySQL.createTables();

        //ej 2
        try {
            dao.populateDB();
           // h.populateDB();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
