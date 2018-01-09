package com.ccim.servlet.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {

	private static DataSource dataSource = new ComboPooledDataSource();

	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	// 鐩存帴鍙互鑾峰彇涓�涓繛鎺ユ睜
	public static DataSource getDataSource() {
		return dataSource;
	}

	// 鑾峰彇杩炴帴瀵硅薄
	public static Connection getConnection() throws SQLException {

		Connection con = tl.get();
		if (con == null) {
			con = dataSource.getConnection();
			tl.set(con);
		}
		
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession("root", "39.106.120.166", 22);
			session.setPassword("Jj769091349");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            session.sendKeepAliveMsg();
//			int assinged_port = session.setPortForwardingL("192.168.0.101", 5555, "192.168.0.101", 3306);// 端口映射 转发
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	// 寮�鍚簨鍔�
	public static void startTransaction() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.setAutoCommit(false);
		}
	}

	// 浜嬪姟鍥炴粴
	public static void rollback() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.rollback();
		}
	}

	// 鎻愪氦骞朵笖 鍏抽棴璧勬簮鍙婁粠ThreadLocall涓噴鏀�
	public static void commitAndRelease() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.commit(); // 浜嬪姟鎻愪氦
			con.close();// 鍏抽棴璧勬簮
			tl.remove();// 浠庣嚎绋嬬粦瀹氫腑绉婚櫎
		}
	}

	// 鍏抽棴璧勬簮鏂规硶
	public static void closeConnection() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.close();
		}
	}

	public static void closeStatement(Statement st) throws SQLException {
		if (st != null) {
			st.close();
		}
	}

	public static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

}
