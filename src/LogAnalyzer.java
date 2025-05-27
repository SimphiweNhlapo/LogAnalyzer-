import java.io.*;
import java.util.*;


public class LogAnalyzer 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		int choice;
		
		
		do
		{
			System.out.println("\n===== Log Analyzer Menu =====");
			System.out.println("1. View Total Number of Log Entries");
			System.out.println("2. Count Log Entries by Type(INFO ,WARN , ERROR)");
			System.out.println("3.Search Logs By KeyWord");
			System.out.println("4. Exit");
			System.out.print("Enter you choice: ");
			
			choice = scanner.nextInt();
			scanner.nextLine();
			
			
			switch(choice)
			{
				case 1:
					countTotalLogs();
					break;
				case 2:
					countByType();
					break;
					
				case 3:
					System.out.println("Enter keyword to search: ");
					String keyword = scanner.nextLine();
					searchByKeyWord(keyword);
					break;
				case 4:
					System.out.println("Exiting program , Goodbye !");
					break;
					
				default:
					System.out.println("Invalid choice. Try Again !");
				
			}
		}
		while(choice != 4);
		
		scanner.close();
		
		

	}

	public static void searchByKeyWord(String keyword)
	{
		File file = new File("log.txt");
		
		if(!file.exists())
		{
			System.out.println("Sorry no log records are found");
		}
		
		boolean found = false;
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line ;
			
			while((line = reader.readLine()) != null)
			{
				if(line.toLowerCase().contains(keyword.toLowerCase()))
				{
					System.out.println(line);
					found = true;
				}
			}
			
			reader.close();
			
			if(!found)
			{
				System.out.println("No matching log entries are found");
			}
				
			
		}
		catch(IOException e)
		{
			System.out.println("There was a error reading the file: "+ e.getMessage());
			e.printStackTrace();
		}
		
	}

	private static void countTotalLogs()
	{

		File file = new File("log.txt");
		
		if(!file.exists())
		{
			System.out.println("Sorry no log records are found");
		}
		
		int count = 0 ;
		
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line;
			
			while((line = reader.readLine()) != null)
			{
				count++;
			}
			
			reader.close();
			System.out.println("The number of total logs is: " + count);
					
				
					
		}
		catch(IOException e)
		{
			System.out.println("Error reading log file: " + e.getMessage());
			e.printStackTrace();
			
		}
	}

	private static void countByType()
	{
		File file = new File("log.txt");
		
		
		if(!file.exists())
		{
			System.out.println("Sorry no error records are found");
		}
		
		int countInfo = 0;
		int countWarn = 0;
		int countError = 0;
		
		String line;
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			while((line = reader.readLine()) != null)
			{
				if(line.contains("[INFO]"))
				{
					countInfo++;
				}
				else if(line.contains("[ERROR]"))
				{
					countError++;
				}
				else if(line.contains("[WARN]"))
				{
					countWarn++;
				}
			}
			reader.close();
			
			System.out.println("The Number Of [INFO] logs are: " + countInfo);
			System.out.println("The Number Of [ERROR] logs are: " + countError);
			System.out.println("The Number Of [WARN] logs are: " + countWarn);
			
			
		}
		catch(IOException e)
		{
			System.out.println("There was an error reading the file: " + e.getMessage());
		}
		
	}

}
