package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import utils.LDTconverter;
import utils.ToDoItem;

public class Database {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private final static String CONNECTION = "jdbc:mysql://localhost/todolist?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	private static Connection connectToDB() throws SQLException {
		Connection conn;
		conn = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
		return conn;
	}

	public void insertToDoItem(ToDoItem tditem) {
		try (Connection conn = connectToDB()) {
			int expInt = 0;
			if(tditem.isExpired()) {
				expInt = 1;
			}
			
			String sqlQueryString = "INSERT INTO items (name, description, expiry, expired) "
					+ "VALUES ( '"+ tditem.getName() +"', '"+ tditem.getDescription() 
					+"', '"+ LDTconverter.LocalDateTimeToString(tditem.getExpiry()) +"', "+
					expInt+")";
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlQueryString);
			
			System.out.println("Item inserted!");
		} catch (SQLException e) {
			System.err.print(e);
		}
	}
	
	public void deleteToDoItem(long id) {
		try (Connection conn = connectToDB()) {
			String sqlQueryString = "DELETE FROM items WHERE id = "+id;
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlQueryString);
			System.out.println("Item deleted!");
		} catch (SQLException e) {
			System.err.print(e);
		}
	}

	public List<ToDoItem> getAllToDoItems() {
		List<ToDoItem> items = new ArrayList<>();
		try (Connection conn = connectToDB()) {
			String sqlQueryString = "SELECT * FROM items";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQueryString);
			
			while(rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String expiryString = rs.getString("expiry");
				int expInt = rs.getInt("expired");
				
				boolean expired = false;
				if(expInt == 1) {
					expired = true;
				}
				LocalDateTime expiry = LDTconverter.StringToLocalDateTime(expiryString);
				
				items.add(new ToDoItem(id, name, description, expiry, expired));
			}
		} catch (SQLException e) {
			System.err.print(e);
		}
		return items;
	}
	
	public List<ToDoItem> getAllActiveTasks(){
		List<ToDoItem> items = new ArrayList<>();
		try (Connection conn = connectToDB()) {
			String sqlQueryString = "SELECT * FROM items WHERE expired = 0";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQueryString);
			
			while(rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String expiryString = rs.getString("expiry");
				int expInt = rs.getInt("expired");
				
				boolean expired = false;
				if(expInt == 1) {
					expired = true;
				}
				LocalDateTime expiry = LDTconverter.StringToLocalDateTime(expiryString);
				
				items.add(new ToDoItem(id, name, description, expiry, expired));
			}
		} catch (SQLException e) {
			System.err.print(e);
		}
		return items;
	}
	
	public List<ToDoItem> getAllExpiredTasks(){
		List<ToDoItem> items = new ArrayList<>();
		try (Connection conn = connectToDB()) {
			String sqlQueryString = "SELECT * FROM items WHERE expired = 1";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQueryString);
			
			while(rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String expiryString = rs.getString("expiry");
				int expInt = rs.getInt("expired");
				
				boolean expired = false;
				if(expInt == 1) {
					expired = true;
				}
				LocalDateTime expiry = LDTconverter.StringToLocalDateTime(expiryString);
				
				items.add(new ToDoItem(id, name, description, expiry, expired));
			}
		} catch (SQLException e) {
			System.err.print(e);
		}
		return items;
	}
}
