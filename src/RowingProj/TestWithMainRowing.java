/**
 * PCTK
 * Main Testing File
 */
//package dbHW6;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TestWithMainRowing {
	static RowingDBUtil myUtil = new RowingDBUtil();
	static Scanner keyboard = new Scanner(System.in);
	public static void main(String[] args) {
		
		
		myUtil.openDB();
		System.out.println(myUtil.conn);
		//Menu
		boolean exitRequest = false;
		while(exitRequest = false){
			for(int i  = 0; i < 20; i++){
				System.out.print("*");
			}
			System.out.println("Options: ");
			System.out.println("0: EXIT");
			System.out.println("1: CLOSE");
			System.out.println("2: OPEN");
			System.out.println("3: View Race Lineup");
			System.out.println("4: View Regatta Results");
			System.out.println("5: Modify Lineup");
			System.out.println("6: Delete Rower");
			System.out.println("7: Insert Boat");
			
			int select = -1;
			System.out.println("Selection number: ");
			select = keyboard.nextInt();
			if(select == 0)
				exitRequest = true;
			switch (select){
			case 1: closeDB();
			case 2: openDB();
			case 3:	raceLineup();
			case 4:	regResults();
			case 5: modifyLineup();
			case 6: deleteRower();
			case 7: insertBoat();
			}
		}
		
		
		
		
		
		
	}
	/**
	 * Close the connection to the DB
	 */
	private static void closeDB(){
		myUtil.closeDB();
	}
	/***
	 * 1 Write and Test
	 * Overload the open method that opens
	 * the DB with the user name, password, and dbName given as input.
	 * 
	 * @param username is a String that is the DB account username
	 * @param password is a String that is the password the account
	 * @param dbName is name of the database that will be the active db    
	 */
	private static void openDB(){
		String user; 
		String pass; 
		String dbName;
		System.out.println("Enter user: ");
		user = keyboard.nextLine();
		System.out.println("Enter pass: ");
		pass = keyboard.nextLine();
		System.out.println("Enter dbName: ");
		dbName = keyboard.nextLine();
		myUtil.openDB(user, pass, dbName);
	}
	/**
	 * Display rowers in the boat by their race
	 */
	private static void raceLineup(){
		ResultSet r = null;
		System.out.println("Enter race time: ");
		String time = keyboard.nextLine();
		System.out.println("Enter regatta name: ");
		String regName = keyboard.nextLine();
		System.out.println("Enter regatta date: ");
		String date = keyboard.nextLine();
		
		r = myUtil.raceLineup(time, regName, date);
		
		
		System.out.printf("%=20s %-20s %-20s \n", "Boat_Name", "Team_College", "Rower_Pos");
		try{
			while(r.next()){
				//Cursor operations and the ResultSet Class
				//columns start from 1-X
				System.out.printf("%=20s %-20s %-20s \n", r.getString(1), r.getString(2), r.getString(3));
			}
		}
		catch(SQLException e){
			System.out.println("Apologies, the programmer failed to prevent the error. Shoot the programmer: Y/N?");
		}
	}
	
	/**
	 * A table is displayed. Containing relevant results data for each race in the regatta, as well 
	 * as the name of the coxswain who piloted the boat.
	 */
	private static void regResults(){
		ResultSet r = null;
	
		System.out.println("Enter regatta name: ");
		String regName = keyboard.nextLine();
		System.out.println("Enter regatta date: ");
		String date = keyboard.nextLine();
		
		r = myUtil.regResults(regName, date);
		//OUTPUT: Gender, Type, Level, Team_College, Coxswain Name
		System.out.printf("%=20s %-20s %-20s %-20s %-20s \n", "Gender", "Type", "Level", "Team_College", "Coxswain Name");
		try{
			while(r.next()){
				//Cursor operations and the ResultSet Class
				//columns start from 1-X
				System.out.printf("%=20s %-20s %-20s \n", r.getString(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5));
			}
		}
		catch(SQLException e){
			System.out.println("Apologies, the programmer failed to prevent the error. Shoot the programmer: Y/N?");
		}
	}
	
	/**
	 * The relationship is updated within the database and the result is echoed to the user to
	 * denote a successful insertion. If the operation is unsuccessful, an error message will be displayed 
	 * detailing the cause of the error, and the user is given the option to re-submit input.
	 */
	private static void modifyLineup(){
		System.out.println("Enter First Name: ");
		String fname = keyboard.nextLine();
		System.out.println("Enter Last Name: ");
		String lname = keyboard.nextLine();
		System.out.println("Enter Boat Name: ");
		String boatName = keyboard.nextLine();
		System.out.println("Enter Boat Column: ");
		String boatCol = keyboard.nextLine();
		System.out.println(myUtil.modifyLineup(fname, lname, boatName, boatCol));
	}
	
	/**
	 * The specified athlete is removed from the database, with a message displayed denoting
	 * successful deletion. If the operation is unsuccessful, an error message will be displayed detailing 
	 * the cause of the error, and the user is given the option to re-submit input.
	 */
	private static void deleteRower(){
		System.out.println("Enter First Name: ");
		String fname = keyboard.nextLine();
		System.out.println("Enter Last Name: ");
		String lname = keyboard.nextLine();
		System.out.println(myUtil.deleteRower(fname, lname));
	}
	
	/**
	 * A boat weak-entity with the specified name and type is added to the database, with an
	 * identifying relationship to the team of the coach who added it. If the operation is unsuccessful, an 
	 * error message will be displayed detailing the cause of the error, and the user is given the option 
	 * to re-submit input.
	 * 
	 * INPUT: entries for BOAT(Name, Team_Gender,Team_College, Size)
	 * RESUlT: tuple BOAT(Name, Team_Gender,Team_College, Size)
	 */
	private static void insertBoat(){
		System.out.println("Enter Boat Name: ");
		String boatname = keyboard.nextLine();
		System.out.println("Enter Team Gender (W/M):");
		String team_gender = keyboard.nextLine();
		System.out.println("Enter Team College: ");
		String team_college = keyboard.nextLine();
		System.out.println("Enter Boat Size: ");
		String size = keyboard.nextLine();
		
		System.out.println(myUtil.insertBoat(boatname, team_gender, team_college, size));
	}
	
	
	
	//----------------------------------------------------------------------------------------------------------------
	
	
	


}
