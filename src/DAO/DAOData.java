package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DATA.Data;
import DATABASE.JDBCUntil;

public class DAOData implements DAOInterface<Data> {
	String tbName = "Score";
	Connection conn = JDBCUntil.getConnection();

	public static DAOData getINTC() {
		return new DAOData();
	}

	@Override
	public boolean insert(Data t) {
		String sql = "INSERT INTO " + tbName + " (name, point, time) " + "VALUES(?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getName());
			ps.setInt(2, t.getPoint());
			ps.setLong(3, t.getTime());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int update(Data t) {
		return 0;
	}

	@Override
	public int delete(Data t) {
		return 0;
	}

	@Override
	public ArrayList<Data> selectAll() {
		ArrayList<Data> list = new ArrayList<>();
		String sql = "SELECT * FROM " + tbName + " Order by point Desc";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Data t = new Data(rs.getString("name"), rs.getInt("point"), rs.getInt("time"));

				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Data> selectByCondition(String condition) {
		return null;
	}

}
