package main.java.factory;

import daos.ClienteDAO;
import daos.FacturaDAO;
import daos.FacturaProductoDAO;
import daos.ProductoDAO;
import main.java.utils.HelperMySQL;

import java.sql.SQLException;

public abstract class DAOFactory {
    public static final int MYSQL_JDBC = 1;
    private static MySqlJDBCDAOFactory mySQLDAOFactory;

    public abstract ClienteDAO getClienteDAO() throws SQLException;
    public abstract FacturaDAO getFacturaDAO() throws SQLException;
    public abstract FacturaProductoDAO getFacturaProductoDAO() throws SQLException;
    public abstract ProductoDAO getProductoDAO() throws SQLException;
    public abstract HelperMySQL getSQLHelper() throws  SQLException;


    public static DAOFactory getDAOFactoryOnlyInstance(int whichFactory) throws SQLException {
        switch (whichFactory) {
            case MYSQL_JDBC : {
                if (mySQLDAOFactory == null) {
                    mySQLDAOFactory = new MySqlJDBCDAOFactory();
                    return mySQLDAOFactory;
                }
            }
            default: return null;
        }
    }

}
