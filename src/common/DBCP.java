package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBCP {
	public Connection conn;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public DBCP() {
		try {
			Context initCtx = new InitialContext(); // 초기 Context 생성
			Context ctx = (Context)initCtx.lookup("java:comp/env"); // 현재 웹 어플의 루트 디렉터리
			DataSource source = (DataSource)ctx.lookup("dbcp_myoracle"); // context.xml에 작성한 이름
			conn = source.getConnection();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (psmt != null) psmt.close();
			if (conn != null) conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
