package sample.Dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class DataAccess {
    private SQLServerDataSource dataSource;

    public DataAccess()
    {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setUser("CSe20B_3");
        dataSource.setPassword("CSe20B_3");
        dataSource.setDatabaseName("MovieStoringSystem");
    }

    public Connection getConnection() throws SQLServerException
    {
        return dataSource.getConnection();
    }
}