package com.yangxuan;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.AtomikosDataSourceBean;

import javax.transaction.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class AtomikosJTATest {

    private static AtomikosDataSourceBean create(String dbName) {
        Properties properties = new Properties();
        properties.setProperty("url", "jdbc:mysql://localhost:3306" + dbName);
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");

        // 使用AtomikosDataSourceBean封装com.msql.jdbc.jdbc2.optional.MysqlXADataSource
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName(dbName);
        ds.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        ds.setXaProperties(properties);
        return ds;
    }

    public static void main(String[] args) throws SystemException, SQLException {
        AtomikosDataSourceBean ds1 = create("db_user");
        AtomikosDataSourceBean ds2 = create("db_name");

        Connection conn1 = null;
        Connection conn2 = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        UserTransactionImp userTransaction = new UserTransactionImp();

        try {
            // 开启事物
            userTransaction.begin();
            // 执行db1
            conn1 = ds1.getConnection();
            ps1 = conn1.prepareStatement("insert into user(name) values(?)", Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, "yangxuan");
            ps1.executeUpdate();

            // 模拟异常
            // int i = 2 / 0;

            // 执行db2
            conn2 = ds2.getConnection();
            ps2 = conn2.prepareStatement("insert into account(name) values(?)");
            ps2.setString(1, "666");
            ps2.executeUpdate();

            // 两阶段提交
            userTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            userTransaction.rollback();
        } finally {
            if (ps1 != null) {
                ps1.close();
            }
            if (ps2 != null) {
                ps2.close();
            }
            if (conn1 != null) {
                conn1.close();
            }
            if (conn2 != null) {
                conn2.close();
            }
        }

    }
}
