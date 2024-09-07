package main.java;

import main.java.factory.DAOFactory;
import main.java.utils.HelperMySQL;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        DAOFactory dao = DAOFactory.getDAOFactoryOnlyInstance(1);
        assert dao != null;
        HelperMySQL h = dao.getSQLHelper();
        //ej 1
        h.createTables();

        //ej 2
        try {
            h.populateDB();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
