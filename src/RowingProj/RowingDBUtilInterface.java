package RowingProj;

import java.sql.ResultSet; 

public interface RowingDBUtilInterface {
	
	
	/**
	 * Open the DB connection where user name and password are predefined.
	 */
	public void openDB();
	
	/***
	 * Overload the open method that opens
	 * the DB with the user name, password, and dbName given as input.
	 * 
	 * @param username is a String that is the DB account username
	 * @param password is a String that is the password the account
	 * @param dbName is name of the database that will be the active db    
	 */
	public void openDB(String user, String pass, String dbName);
	
	/**
	 * Close the connection to the DB
	 */
	public void closeDB();
	
	/**
	 * Return a ResultSet that has Boat_Name, Team_College, Rower_Pos, First_Name, and Last_Name, of every 
	 * Rower in a boat in a race, ordered by college, then by last name.
	 * 
	 * @param time - Start time of the race.
	 * @param level - The race level, varsity, novice, or jv.
	 * @param regName - The name of the regatta
	 * @param date - The date of the regatta
	 * @return ResultSet with Team_College, Boat_Name, First_Name, Last_Name, and Rower_Pos.
	 */
	public ResultSet raceLineup( String time, String regName, String date );
	
	/**
	 * Return a ResultSet that has the Gender, Type, Level, Team_College, and Coxswain Name 
	 * 		for each race int he regatta.
	 * 
	 * @param name - The name of the regatta
	 * @param date - The date of the regatta
	 * @return ResultSet with Team_College, Boat_Name, First_Name, Last_Name, and Rower_Pos.
	 */
	public ResultSet regResults( String name, String date );
	
	/**
	 * Be able to update the lineup of a boat for a particular race.
	 * 
	 * @param newEmail - Rower to replace a rower on the boat
	 * @param oldEmail - Rower to be replaced on the boat
	 * @param boatName - the name of the boat
	 * @param boatCol - The college that owns the boat
	 * @param gen - The gender of the team
	 * @param raceTime - The time of the race
	 * @param regName - The name of the regatta
	 * @param regDate - The date of the regatta
	 * @return int - indicates the number of rows updated.
	 */
	public int modifyLineup(String newEmail, String oldEmail, String boatName,
			String boatCol, String gender, String raceTime, String regName, String regDate);
	
	/**
	 * Remove a Rower from a school's roster.
	 * 
	 * @param email - Email of the Rower
	 * @return int - indicates the number of rows updated.
	 */
	public int deleteRower(	String email );
	
	/**
	 * Remove a Rower from a school's roster.
	 * 
	 * @param boatName - the name of the boat
	 * @param size - The size of the boat(2, 4, 8, etc.)
	 * @param gen - The gender of the team
	 * @param col - The college that owns the boat
	 * @return int - indicates the number of rows updated.
	 */
	public int insertBoat( String boatName, String size, String gender, String col );	
	
	
}
