package main.java.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.entities.FacturaProducto;

public class FacturaProductoDAO {
    private Connection conn;

    public FacturaProductoDAO(Connection conn) {
        this.conn = conn;
    }

    public void addFacturaProducto(FacturaProducto fp) throws SQLException {
        String insert = "INSERT INTO facturaProducto (idFactura, idProducto, cantidad) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1, fp.getIdFactura());
        ps.setInt(2, fp.getIdProducto());
        ps.setInt(3, fp.getCantidad());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }
}
