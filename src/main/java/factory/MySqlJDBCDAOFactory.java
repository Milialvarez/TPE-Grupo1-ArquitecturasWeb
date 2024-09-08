package main.java.factory;

import daos.ClienteDAO;
import daos.FacturaDAO;
import daos.FacturaProductoDAO;
import daos.ProductoDAO;
import main.java.entities.Cliente;
import main.java.factory.DAOFactory;
import main.java.utils.HelperMySQL;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlJDBCDAOFactory extends DAOFactory {

    public MySqlJDBCDAOFactory() throws SQLException {

    }

    @Override
    public ClienteDAO getClienteDAO() throws SQLException {
        return new ClienteDAO(HelperMySQL.getConnection());
    }

    @Override
    public FacturaDAO getFacturaDAO() throws SQLException {
        return new FacturaDAO(HelperMySQL.getConnection());
    }

    @Override
    public FacturaProductoDAO getFacturaProductoDAO() throws SQLException {
        return new FacturaProductoDAO(HelperMySQL.getConnection());
    }

    @Override
    public ProductoDAO getProductoDAO() throws SQLException {
        return new ProductoDAO(HelperMySQL.getConnection());
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};  // Puedes configurar tu encabezado personalizado aquí si es necesario
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void populateDB() throws Exception {
        System.out.println("Populating DB...");
        this.loadClients();
        this.loadProducts();
    }

    private void loadProducts(){

    }

    private void loadClients() throws IOException {
        for(CSVRecord row : getData("clientes.csv")) {
            if(row.size() >= 3) { // Verificar que hay al menos 4 campos en el CSVRecord
                String idString = row.get(0);
                if(!idString.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idString);
                        Cliente cliente = new Cliente(id, row.get(1), row.get(2));
                        getClienteDAO().addCliente(cliente);
                    } catch (NumberFormatException | SQLException e) {
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
        System.out.println("clientes insertados");
    }
}
