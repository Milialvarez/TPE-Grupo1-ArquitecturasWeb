package main.java.factory;

import daos.ClienteDAO;
import daos.FacturaDAO;
import daos.FacturaProductoDAO;
import daos.ProductoDAO;

import java.sql.SQLException;

public abstract class DAOFactory {
    public static final int MYSQL_JDBC = 1;

    public abstract ClienteDAO getClienteDAO() throws SQLException;
    public abstract FacturaDAO getFacturaDAO() throws SQLException;
    public abstract FacturaProductoDAO getFacturaProductoDAO() throws SQLException;
    public abstract ProductoDAO getProductoDAO() throws SQLException;


    public static DAOFactory getDAOFactory(int whichFactory) throws SQLException {
        switch (whichFactory) {
            case MYSQL_JDBC : return MySqlJDBCDAOFactory.getInstance();
            default: return null;
        }
    }

}
