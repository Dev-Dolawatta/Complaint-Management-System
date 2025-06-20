package lk.edu.student.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
    private static BasicDataSource dataSource;

    public static BasicDataSource setupDataSource() {
        if (dataSource == null) {
            try {
                Properties props = new Properties();
                InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream("db.properties");
                props.load(input);

                dataSource = new BasicDataSource();
                dataSource.setDriverClassName(props.getProperty("db.driver"));
                dataSource.setUrl(props.getProperty("db.url"));
                dataSource.setUsername(props.getProperty("db.username"));
                dataSource.setPassword(props.getProperty("db.password"));

                dataSource.setMaxTotal(Integer.parseInt(props.getProperty("db.maxActive")));
                dataSource.setMaxIdle(Integer.parseInt(props.getProperty("db.maxIdle")));
                dataSource.setMinIdle(Integer.parseInt(props.getProperty("db.minIdle")));
                dataSource.setMaxWaitMillis(Long.parseLong(props.getProperty("db.maxWait")));

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load database properties");
            }
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
