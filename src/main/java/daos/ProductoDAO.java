package main.java.daos;

import main.java.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {
    private Connection conn;

    public ProductoDAO(Connection conn) {
        this.conn = conn;
    }

    public void addProducto(Producto producto) throws SQLException {
        String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1, producto.getIdProducto());
        ps.setString(2, producto.getNombre());
        ps.setFloat(3, producto.getValor());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    public Producto getHighestIncomeProduct() {
          String select = "SELECT p.idProducto,p.nombre, p.valor FROM mydb.producto p " +
				"LEFT JOIN mydb.facturaProducto fp ON p.idProducto = fp.idProducto " +
				"GROUP BY(p.idProducto) ORDER BY SUM(fp.cantidad)*p.valor desc LIMIT 1";
		try {
			PreparedStatement ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Producto prod = new Producto(rs.getInt(1),rs.getString(2),rs.getInt(3));
				return prod;
			}
			ps.close();
//		conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
    }
}
