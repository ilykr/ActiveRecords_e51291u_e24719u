package activeRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private String userName = "root";
    private String password = "root";
    private String serverName = "127.0.0.1";
    //String portNumber = "3306";
    private String portNumber = "3306"; // Port par défaut sur docker
    private String tableName = "personne";

    // il faut une base nommee testPersonne !
    private static String dbName = "testpersonne";

    private static Connection instance;

    private DBConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        String urlDB = "jdbc:mysql://" + serverName + ":";
        urlDB += portNumber + "/" + dbName;
        instance = DriverManager.getConnection(urlDB, connectionProps);

    }

    public static synchronized Connection getConnection() throws SQLException {
        if (instance == null) {
            new DBConnection();
        }
        return instance;
    }

    public static synchronized void setNomDB(String nomDB){
        if (nomDB!=null && !nomDB.equals(dbName)){
            DBConnection.dbName = nomDB;
            DBConnection.instance=null;
        }
    }
}
