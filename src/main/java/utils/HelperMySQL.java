package main.java.utils;

import main.java.entities.Cliente;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelperMySQL {
    private Connection conn = null;

    public HelperMySQL() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/mydb";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "root");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dropTables() throws SQLException {
        String dropPersona = "DROP TABLE IF EXISTS Persona";
        this.conn.prepareStatement(dropPersona).execute();
        this.conn.commit();

        String dropDireccion = "DROP TABLE IF EXISTS Direccion";
        this.conn.prepareStatement(dropDireccion).execute();
        this.conn.commit();
    }

    public void createTables() throws SQLException {
        String tablaCliente = "CREATE TABLE IF NOT EXISTS cliente (" +
                                "idCliente int PRIMARY KEY, " +
                                "nombre Varchar(500)," +
                                "email Varchar(150))";
        this.conn.prepareStatement(tablaCliente).execute();

        String tablaFactura = "CREATE TABLE IF NOT EXISTS factura("+
                                "idFactura int PRIMARY KEY,"+
                                "idCliente int)";
        this.conn.prepareStatement(tablaFactura).execute();

        String tablaProducto = "CREATE TABLE IF NOT EXISTS producto(" +
                "idProducto int PRIMARY KEY," +
                "nombre Varchar(45)," +
                "valor Float)";

        this.conn.prepareStatement(tablaProducto).execute();

        String tablaFacturaProducto = "CREATE TABLE IF NOT EXISTS facturaProducto("+
                                        "idFactura int,"+
                                        "idProducto int,"+
                                        "cantidad int," +
                                        "PRIMARY KEY (idFactura, idProducto)," +
                                        "FOREIGN KEY (idFactura) REFERENCES factura(idFactura)," +
                                        "FOREIGN KEY(idProducto) REFERENCES producto(idProducto))";
        this.conn.prepareStatement(tablaFacturaProducto).execute();

        this.conn.commit();
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
                        insertCliente(cliente);
                    } catch (NumberFormatException | SQLException e) {
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
        System.out.println("clientes insertados");
    }

    private void insertCliente(Cliente cliente) throws SQLException {
        String insert = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1, cliente.getId());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getEmail());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }


    private void closePsAndCommit(Connection conn, PreparedStatement ps) {
        if (conn != null){
            try {
                ps.close();
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


