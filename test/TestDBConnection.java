import activeRecord.DBConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnection {

    Connection c;
    Connection c2;
    Connection c3;
    @BeforeEach
    public void setup() throws SQLException {
        DBConnection.setNomDB("testpersonne");
        c = DBConnection.getConnection();
        c2 = DBConnection.getConnection();
        c3 = DBConnection.getConnection();
    }

    @Test
    public void TestSingleton() throws SQLException {
        Assertions.assertEquals(c2,c,"erreur Singleton");
        Assertions.assertEquals(c3,c,"erreur Singleton");
        Assertions.assertEquals(c2,c3,"erreur Singleton");
        DBConnection.setNomDB("td1");
        Assertions.assertEquals(c2,c,"erreur Singleton");
        Assertions.assertEquals(c3,c,"erreur Singleton");
        Assertions.assertEquals(c2,c3,"erreur Singleton");
    }

    @Test
    public void TestSetNomDB() throws SQLException {
        DBConnection.setNomDB("td1");
        c = DBConnection.getConnection();
        Assertions.assertEquals("td1", DBConnection.getConnection().getCatalog(), "Probleme changement nom de database");
    }
}
