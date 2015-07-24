package RowingProj;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import RowingProj.RowingDBUtil;

public class testRowingDBUtil {

	static RowingDBUtil myUtil = new RowingDBUtil();
	
	public static void main(String[] args) throws SQLException {
		
		myUtil.openDB();
		
		Scanner keyboard = new Scanner(System.in);
		
		boolean run= true;
		
		while( run ){
			
		int choice;
		System.out.println("\nChoose one of the following\n1) Race Lineup"
				+ "\n2) Regatta Results\n3) Modify Lineup\n"
				+ "4) Delete Rower\n5) Insert Boat\n"
				+ "6) Quit");

		choice = keyboard.nextInt();
		
		switch( choice )
		{
		case 1:
			lineup();
			break;
		case 2:
			results();
			break;
		case 3:
			modifyLineup();
			break;
		case 4:
			deleteRower();
			break;
		case 5:
			insertBoat();
			break;
		case 6:
			run = false;
			break;
		}
		}
		
		keyboard.close();
		myUtil.closeDB();
		}

	
	//Does not work
	private static void insertBoat() {
		// String boatName, String size, String gender, String col
		
			Scanner keyboard = new Scanner(System.in);
			
			System.out.println("Insert boat");
			System.out.println("Enter boat name:");
			String b = keyboard.nextLine();
			System.out.println("Enter boat size:");
			String s = keyboard.nextLine();
			System.out.println("Enter team gender:");
			String g = keyboard.nextLine();
			System.out.println("Enter team college:");
			String c = keyboard.nextLine();
			
			System.out.println( myUtil.insertBoat(b, s, g, c) + "rows updated");
		
	}

	
	//Not Working
	private static void deleteRower() {
		// String email
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Delete Rower");
		System.out.println("Enter rower email:");
		String e = keyboard.nextLine();
		
		System.out.println( myUtil.deleteRower(e) + " rows updated");
	}

	
	
	private static void modifyLineup() {
		// String newEmail, String oldEmail, String boatName,
		//String boatCol, String gender, String raceTime, String regName, String regDate
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Modify Lineup");
		System.out.println("Enter new rower email:");
		String n = keyboard.nextLine();
		System.out.println("Enter old rower email:");
		String o = keyboard.nextLine();
		System.out.println("Enter boat name:");
		String b = keyboard.nextLine();
		System.out.println("Enter team college:");
		String c = keyboard.nextLine();
		System.out.println("Enter team gender:");
		String g = keyboard.nextLine();
		System.out.println("Enter race time:");
		String t = keyboard.nextLine();
		System.out.println("Enter regatta name:");
		String rn = keyboard.nextLine();
		System.out.println("Enter regatta date:");
		String rd = keyboard.nextLine();
		
		System.out.println( myUtil.modifyLineup(n, o, b, c, g, t, rn, rd) + "rows updated");
		
	}

	private static void results() throws SQLException {
		//  String name, String date 
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter regatta name:");
		String rn = keyboard.nextLine();
		System.out.println("Enter regatta date:");
		String rd = keyboard.nextLine();
		
		ResultSet r = myUtil.regResults(rn, rd);
		
		System.out.printf("%-20s %-20s %-20s %-20s\n", "Team_College", "Boat_Name", "First_Name", "Last_Name", "Rower_Pos");

		while(r.next()){
			System.out.printf("%-20s %-20s %-20s %-20s\n", r.getString(1), r.getString(2), r.getString(3), r.getString(4));
		}
		
	}

	private static void lineup() throws SQLException {
		// String time, String regName, String date
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter race time:");
		String t = keyboard.nextLine();
		System.out.println("Enter regatta name:");
		String rn = keyboard.nextLine();
		System.out.println("Enter regatta date:");
		String rd = keyboard.nextLine();
		
		ResultSet r = myUtil.raceLineup(t, rn, rd);
		System.out.printf("%-20s %-20s %-20s %-20s\n", "Team_College", "Boat_Name", "First_Name", "Last_Name", "Rower_Pos");

		while(r.next()){
			System.out.printf("%-20s %-20s %-20s %-20s\n", r.getString(1), r.getString(2), r.getString(3), r.getString(4));
		}
	}
	
	
}
