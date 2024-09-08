package main.java.daos;

import main.java.entities.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturaDAO {
    private Connection conn;

    public FacturaDAO(Connection conn) {
        this.conn = conn;
    }

    public void addFactura(Factura fac) throws SQLException {
        String insert = "INSERT INTO factura (idFactura, idCliente) VALUES (?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1, fac.getIdFactura());
        ps.setInt(2, fac.getIdCliente());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

}
