package decorator;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    private static DBConnection dbConnection;
    private final Connection connection;
    @SneakyThrows
    private DBConnection() {
    connection = DriverManager.getConnection("jdbc:sqlite:db.sqlite3");
    }
    public static DBConnection getInstance() {
        if(dbConnection == null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    @SneakyThrows
    public String getContent(String gcsPath){
        Statement statement = connection.createStatement();
        String query = String.format("select content from document where gcsPath == '%1$s';", gcsPath);
        ResultSet rs = statement.executeQuery(query);
        String content = rs.getString("content");
        rs.close();
        statement.close();
        return content;
    }
    @SneakyThrows
    public void addContent(String gcsPath, String content) {
        Statement statement = connection.createStatement();
        String query = String.format("insert into document (gcsPath, content) values ('%1$s', '%2$s');", gcsPath, content);
        statement.executeUpdate(query);
        statement.close();
    }
}
