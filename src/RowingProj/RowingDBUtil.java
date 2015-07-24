package RowingProj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;

public class RowingDBUtil implements RowingDBUtilInterface {

	
	public Connection conn=null;
	
	
	
	@Override
	public void openDB() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e){
			System.out.println("problem with the Driver: " + e.getMessage());
		}
		
		String url="jdbc:mysql://zoe.cs.plu.edu:3306/pctk367_2014";
		String username = "pctk367";
		String password = "pctk367";
		
		try{
			conn = DriverManager.getConnection(url, username, password);
		}catch(SQLException e){
			System.out.println("problem connecting to mySQL: " + e.getMessage());
		}	
	}

	@Override
	public void openDB(String user, String pass, String dbName) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("problem with Driver ");
			
		}

		
		String url="jdbc:mysql://zoe.cs.plu.edu:3306/" + dbName;
		String username = user;
		String password = pass;
		
		try{
			conn = DriverManager.getConnection(url, username, password);
		}catch(SQLException e){
			System.out.println("problem connecting to mySQL: " + e.getMessage());
		}
	}
	
	
	@Override
	public void closeDB() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet raceLineup(String time, String regName,
			String date) {
		ResultSet r = null;
		String sql = "SELECT DISTINCT R1.Team_College, R1.Boat_Name, CONCAT('Pos: ', "
			+"R2.Rower_Position, ' ', User.First_name, ' ', User.Last_name)"

			+"FROM RACE_IN R1, Row_In R2, User, RACE R0"

			+"WHERE User.Primary_Email = R2.Rower_Email AND R2.Boat_name ="
			+"R1.Boat_name AND R0.Race_Time = R2.Race_Time AND R2.Reg_date = R0.Reg_date"
			+"AND R0.Race_Time = " + time + " AND R0.Reg_Name = " + regName + " AND R0.Reg_Date = " + date 

			+"ORDER BY R1.Team_College, R1.Boat_name, R2.Rower_Position";
		
		try{
			Statement stmt = conn.createStatement();
			r = stmt.executeQuery(sql);
		}
		catch(SQLException e){
			System.out.println("Problem with conn statement creation: " + sql);
			e.printStackTrace();
		}
		return r;
	}

	
	
	@Override
	public ResultSet regResults(String name, String date) {
		ResultSet r = null;
		String sql = 

				"select distinct R.Gender, RI.Team_College AS 'Team', R.Type AS Class,"
				+ " R.Race_Level AS Level, U.Last_Name AS Coxswain, RI.Boat_Time AS Finish_Time"
				+ " from RACE R, RACE_IN RI, ROWER RW, Row_In RWI, User U "  
				+ "where R.Race_Time = RI.Race_time AND R.Reg_Name = RI.Reg_name AND R.Reg_Date = RI.Reg_Date AND" 
						+ "R.Race_Time = RWI.Race_time AND R.Reg_Name = RWI.Reg_name AND "
						+ "R.Reg_Date = RWI.Reg_Date AND RWI.Rower_Email=RW.Primary_Email AND "
						+ "U.Primary_Email = RW.Primary_Email AND RWI.Rower_Position = 'C' AND "
						+ "RWI.Boat_name = RI.Boat_name AND RI.Team_College = RWI.Boat_College AND "
				+ "RI.Team_Gender = RWI.Boat_Gender AND R.Reg_Name = '?' AND R.Reg_Date = '?' "
				+ "ORDER BY R.Gender, R.Type, R.Race_Level, RI.Boat_time";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.clearParameters();
			pstm.setString(1, name);
			pstm.setString(2, date);
			
			r = pstm.executeQuery(); 

		} catch (SQLException e) {
			System.out.println("Error: "+ e.getMessage());
		}
		
		
		return r;
	}

	@Override
	public int modifyLineup(String newEmail, String oldEmail, String boatName,
			String boatCol, String gender, String raceTime, String regName, String regDate) {
		int count = 0;
		String sql = "UPDATE Row_In SET Rower_email = '" + newEmail
				+"' WHERE Rower_email = '" + oldEmail + "' AND Boat_name = '" + boatName 
						+ "' AND Boat_College = '" + boatCol 
						+ "' AND Boat_Gender = '"+ gender  
						+ "' AND Race_Time = '"+ raceTime 
						+ "' AND Reg_Name = '"+ regName 
						+ "' AND Reg_date = '"+ regDate +"'";
		
		try {
			Statement stmt = conn.createStatement();
			count = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Error: "+ e.getMessage());
		}
		
		return count;
	}

	
	
	@Override
	public int deleteRower(String email) {
		int count = 0;
		String sql = "DELETE FROM User WHERE Primary_Email='?'";
		try{
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1,  email);
			count += p.executeUpdate();
		}catch(SQLException e){
			System.out.println("Problem with SQL: " + sql);
			System.out.println("error: " + e.getMessage());
		}
		
		return count;
	}

	
	
	@Override
	public int insertBoat(String boatName, String size, String gender,
			String col) {
		int count = 0;
		String sql = "insert into boat(Name, Team_Gender, Team_College, Size) "
				+ "values ('?', '?', '?', ?)";
		
		try{
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.clearParameters();
			pstm.setString(1, boatName);
			pstm.setString(2, gender);
			pstm.setString(3, col);
			pstm.setString(4, size);
			
			count += pstm.executeUpdate();
		}
		catch(SQLException e){
			System.out.println("Problem with insert into insertBoat() " + sql);
			System.out.println(e.getMessage());
		}
		
		
		return count;
	}
	
	
	
}