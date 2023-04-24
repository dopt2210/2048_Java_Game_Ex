package DATABASE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUntil {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			//Đăng ký server
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			String dtbName = "Info2048";//Tên database
			String svName = "localhost";//host
			//Đường dẫn gồm server, tên host, port, tên database, chứng nhận
			String url = "jdbc:sqlserver://"+ svName + ":1433;DatabaseName=" + dtbName +
					";encrypt=true;trustServerCertificate=true;";
			String username = "sa";//Tên người dùng server
			String password = "123456789";//Mật khẩu
			//Tạo liên kết
			conn = DriverManager.getConnection(url,username,password);		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
