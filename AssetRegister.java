package com.nissan.lib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssetRegister {

	public static Scanner input=new Scanner(System.in);
	//storing data : array---->name and balance
	static String[] computerName=new String[5];
	static String[] brandName=new String[5];
	static Date manufactureDate[]=new Date[5];
	static Boolean[] isAvailable =new Boolean[5];
	static int[] quantity=new int[5];
	
	public static void main(String[] args)
	{
		getInput();
	}
	private static void getInput()
	{
		char choice='n';
		int i=0;
		try{
			do{
				//Taking input of computer name
				System.out.print("Enter Computer name: ");
				//String temp=input.nextLine();
				computerName[i]=setValidComputerName(input.nextLine());
				
				//Taking input of Brand name
				System.out.println("Enter Brand name: ");
				brandName[i]=setValidBrandName(input.nextLine());
				
				//Taking input of Manufacturer name
				System.out.println("Enter Manufacturing date in format of (dd/MM/yyy): ");
				String manufacturingDate=setValidDate(input.nextLine());
				try {
					manufactureDate[i]=convertStringToUtilDate(manufacturingDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				//Taking input that computer is available or not
				System.out.println("Enter the computer is available or not(true/false): ");
				String available=setValidAvailableOrNot(input.nextLine());
				if(available.equals("true")){
					isAvailable[i]=true;
				}
				else
				{
					isAvailable[i]=false;
				}
				//Taking input of quantity
				System.out.println("Enter the quantity of computers ");
				String quan=setValidQuantity(input.nextLine());
				quantity[i]=Integer.parseInt(quan);
				
				
				System.out.println("Do you want to enter more(y/n)");
				choice=input.nextLine().charAt(0);
				
				i++;
			}
			while(choice=='Y' || choice=='y');
		}	
		finally{
			displayOutput();
		}
	}
	private static Date convertStringToUtilDate(String manufacturingDate) throws ParseException {
		Date utilDate=new SimpleDateFormat("dd/MM/yyyy").parse(manufacturingDate);
		java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
	private static void displayOutput() {
		try{
			System.out.println("Press x key to see customer info....");
			try{
				char pressKey=input.nextLine().charAt(0);
				if(pressKey=='x')
				{
					input.close();
					System.out.print(String.format("%-20s%20s%20s%20s%20s%n","Computer Name","Brand","Manufacture Date","isAvailable","Quantity"));
					System.out.println("--------------------------------------------------");
					
					for(int j=0;j<computerName.length;j++)
					{
						System.out.print(String.format("%-20s%20s%20s%20s%20s%n",computerName[j],brandName[j],manufactureDate[j],isAvailable[j],quantity[j]));
					}
				}
				
			}
			catch(StringIndexOutOfBoundsException s)
			{
				System.out.println("Application is existing...");
				System.exit(0);
			}
		}
		catch(Exception e)
		{
			System.out.println("Application is existing...");
			System.exit(0);
		}
		
	}
	private static String setValidQuantity(String quan) {
		try{
			//creating object for bufferedReader
			//InputStreamReader isRead=new InputStreamReader(System.in);
			BufferedReader brRead=new BufferedReader(new InputStreamReader(System.in));
			
			//creating pattern using regular expression
			Pattern objPat=Pattern.compile("[^0-9]");
			do{
				//Matches
				Matcher matcher=objPat.matcher(quan);
				boolean finder=matcher.find();
				
				if(finder)
				{
					System.out.println("Name must contain only Numbers.Enter again: ");
					quan=brRead.readLine();
				}
				else
				{
					break;
				}
			}while(true);
			
		}
		catch(Exception e)
		{
			System.out.println("Invalid entry in quantity name");
			
			
		}
		return quan;
	}
	private static String setValidAvailableOrNot(String available) {
		try{
			//creating object for bufferedReader
			//InputStreamReader isRead=new InputStreamReader(System.in);
			BufferedReader brRead=new BufferedReader(new InputStreamReader(System.in));
			
			//creating pattern using regular expression
			Pattern objPat=Pattern.compile("[^truefalse]");
			do{
				//Matches
				Matcher matcher=objPat.matcher(available);
				boolean finder=matcher.find();
				
				if(finder)
				{
					System.out.println("Name must contain only Alphabets.Enter again: ");
					available=brRead.readLine();
				}
				else
				{
					break;
				}
			}while(true);
			
		}
		catch(Exception e)
		{
			System.out.println("Invalid entry in brand name");
			
			
		}
		return available;
	}
	private static String setValidDate(String manufacturingDate) {
		try{
			//creating object for bufferedReader
			//InputStreamReader isRead=new InputStreamReader(System.in);
			BufferedReader brRead=new BufferedReader(new InputStreamReader(System.in));
			
			//creating pattern using regular expression
			Pattern objPat=Pattern.compile("[^0-9/]");
			do{
				//Matches
				Matcher matcher=objPat.matcher(manufacturingDate);
				boolean finder=matcher.find();
				
				if(finder)
				{
					System.out.println("Name must contain only Alphabets.Enter again: ");
					manufacturingDate=brRead.readLine();
				}
				else if(manufacturingDate.length()!=10){
					System.out.println("Date should be entered with 10 charcters only");
					manufacturingDate=brRead.readLine();
				}
				else
				{
					break;
				}
			}while(true);
			
		}
		catch(Exception e)
		{
			System.out.println("Invalid entry in Manufacturing Date");
			
			
		}
		return manufacturingDate;
	}
	private static String setValidBrandName(String brand) {
		try{
			//creating object for bufferedReader
			//InputStreamReader isRead=new InputStreamReader(System.in);
			BufferedReader brRead=new BufferedReader(new InputStreamReader(System.in));
			
			//creating pattern using regular expression
			Pattern objPat=Pattern.compile("[^A-Za-z ]");
			do{
				//Matches
				Matcher matcher=objPat.matcher(brand);
				boolean finder=matcher.find();
				
				if(finder)
				{
					System.out.println("Name must contain only Alphabets.Enter again: ");
					brand=brRead.readLine();
				}
				else if(brand.length()>=20){
					System.out.println("brand Name should be under 20 or equal to 20 characters.");
					brand=brRead.readLine();
				}
				else
				{
					break;
				}
			}while(true);
			
		}
		catch(Exception e)
		{
			System.out.println("Invalid entry in brand name");
			
			
		}
		return brand;
	}
	private static String setValidComputerName(String computerNamei) {
		try{
			//creating object for bufferedReader
			//InputStreamReader isRead=new InputStreamReader(System.in);
			BufferedReader brRead=new BufferedReader(new InputStreamReader(System.in));
			
			//creating pattern using regular expression
			Pattern objPat=Pattern.compile("[^A-Za-z ]");
			do{
				//Matches
				Matcher matcher=objPat.matcher(computerNamei);
				boolean finder=matcher.find();
				
				if(finder)
				{
					System.out.println("Name must contain only Alphabets.Enter again: ");
					computerNamei=brRead.readLine();
				}
				else if(computerNamei.length()>30){
					System.out.println("Name should be under 30 characters.");
					computerNamei=brRead.readLine();
				}
				else
				{
					break;
				}
			}while(true);
			
		}
		catch(Exception e)
		{
			System.out.println("Invalid entry in computer name");
			
			
		}
		return computerNamei;
	}
}
