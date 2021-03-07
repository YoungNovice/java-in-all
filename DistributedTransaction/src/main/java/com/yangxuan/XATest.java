package com.yangxuan;

import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlXAConnection;
import com.mysql.cj.jdbc.MysqlXid;

import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class XATest {

    public static void main(String[] args) throws Exception {
        // true表示打印XA语句， 用于调试
        boolean logXaCommands = true;
        // 获得资源管理器操作接口实例 RM1
        Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_user", "root", "root");
        MysqlXAConnection xaConn1 = new MysqlXAConnection((JdbcConnection) conn1, logXaCommands);
        XAResource rm1 = xaConn1.getXAResource();

        // 获得资源管理器操作接口实例 RM2
        Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_account", "root", "root");
        MysqlXAConnection xaConn2 = new MysqlXAConnection((JdbcConnection) conn2, logXaCommands);
        XAResource rm2 = xaConn2.getXAResource();

        // AP请求TM执行一个分布式事物 TM生成全局事物id
        byte[] gtrid = "g12345".getBytes();
        int formatId = 1;
        try {
            // TM生成rm1上的事物分支id
            byte[] bqual1 = "b0001".getBytes();
            Xid xid1 = new MysqlXid(gtrid, bqual1, formatId);
            rm1.start(xid1, XAResource.TMNOFLAGS);
            PreparedStatement ps1 = conn1.prepareStatement("insert into user(name) values('nihao')");
            ps1.execute();
            rm1.end(xid1, XAResource.TMSUCCESS);

            // TM生成rm2上的事物分支id
            byte[] bqual2 = "b0002".getBytes();
            Xid xid2 = new MysqlXid(gtrid, bqual2, formatId);
            rm1.start(xid2, XAResource.TMNOFLAGS);
            PreparedStatement ps2 = conn1.prepareStatement("insert into account(name) values('haha')");
            ps2.execute();
            rm2.end(xid2, XAResource.TMSUCCESS);

            // -----------两阶段提交
            // phase1 询问所有rm 准备提交事物
            int rm1_prepare = rm1.prepare(xid1);
            int rm2_prepare = rm2.prepare(xid2);

            // phase2 提交所有事物分支
            boolean onePhase = false;
            // 所有分支都成功
            if (rm1_prepare == XAResource.XA_OK && rm2_prepare == XAResource.XA_OK) {
                rm1.commit(xid1, onePhase);
                rm2.commit(xid2, onePhase);
            } else {
                rm1.rollback(xid1);
                rm2.rollback(xid2);
            }
        } catch (Exception e) {
            // 如果异常也要回滚
        }
    }
}
