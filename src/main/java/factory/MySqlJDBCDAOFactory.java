package main.java.factory;

import daos.ClienteDAO;
import daos.FacturaDAO;
import daos.FacturaProductoDAO;
import daos.ProductoDAO;
import main.java.factory.DAOFactory;
import main.java.utils.HelperMySQL;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlJDBCDAOFactory extends DAOFactory {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String uri = "jdbc:mysql://localhost:3306/mydb";
    private static MySqlJDBCDAOFactory instancia = null;
    private HelperMySQL helper;

    public MySqlJDBCDAOFactory() throws SQLException {
        helper = new HelperMySQL();
        helper.createTables();
    }

    public static MySqlJDBCDAOFactory getInstance() throws SQLException {
        if(instancia == null){
            return new MySqlJDBCDAOFactory();
        }
        return instancia;
    }


    public static Connection getConnection() throws SQLException {
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            Connection conn = DriverManager.getConnection(uri, "root", "");
            return conn;
        }catch(InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException |
               InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    @Override
    public ClienteDAO getClienteDAO() throws SQLException {
        return new ClienteDAO(getConnection());
    }

    @Override
    public FacturaDAO getFacturaDAO() throws SQLException {
        return new FacturaDAO(getConnection());
    }

    @Override
    public FacturaProductoDAO getFacturaProductoDAO() throws SQLException {
        return new FacturaProductoDAO(getConnection());
    }

    @Override
    public ProductoDAO getProductoDAO() throws SQLException {
        return new ProductoDAO(getConnection());
    }
}
