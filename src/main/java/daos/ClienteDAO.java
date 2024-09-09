package main.java.daos;

import main.java.entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public void addCliente(Cliente cliente) throws SQLException {
        String insert = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1, cliente.getId());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getEmail());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }
    public ArrayList<Cliente> mostFacturedClients(){
        String select =  "SELECT * FROM mydb.cliente c "+
                        "LEFT JOIN mydb.factura f on c.idCliente = f.idCliente "+
                        "GROUP BY c.idCliente " +
                        "ORDER BY COUNT(f.idCliente) desc";
        try {
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            ArrayList<Cliente> result = new ArrayList<>();

            while(rs.next()){
                Cliente client = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
                result.add(client);
            }
            ps.close();
            conn.commit();
            return result;
        } catch(SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }


}
