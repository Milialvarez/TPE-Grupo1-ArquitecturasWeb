package daos;

import java.sql.Connection;

public class FacturaProductoDAO {
    private Connection conn;

    public FacturaProductoDAO(Connection conn) {
        this.conn = conn;
    }
}
