import java.util.*;
public class startServer
{
	Buffer b;										//Creation of buffer object
	user [] users;
	webserver [] webServers;
	Thread [] userThreads;  
	Thread [] serverThreads;
	int totalElements, temp, avg, rem;

	public startServer()
	{												//Creates execution scenario between user and webservers on buffer

		System.out.println("Enter buffer capacity: ");
		Scanner sc1 = new Scanner(System.in);

		b = new Buffer(sc1.nextInt()); //buf_size is set to what user wants
		
		System.out.println("Enter number of users: ");
		Scanner sc2 = new Scanner(System.in);
		temp = sc2.nextInt();

		users = new user[temp];
		userThreads = new Thread[temp];
		
		System.out.println("Enter number of servers: ");
		Scanner sc3 = new Scanner(System.in);
		temp = sc3.nextInt();

		webServers = new webserver[temp];
		serverThreads = new Thread[temp];
		
		System.out.println("Enter total number of elements: ");
		Scanner sc4 = new Scanner(System.in);

		totalElements = sc4.nextInt();
		
		//Insert user inputted values for program execution
        
        long startTime = System.currentTimeMillis();		
																
		//Instantiate all objects (webserver, users, buffer)

		semaphore smUsers = new semaphore(users.length);
		semaphore smServers = new semaphore(webServers.length);
		semaphore mutex = new semaphore(0);

		avg = totalElements / webServers.length;
		rem = totalElements % webServers.length;

		for(int i = 0; i < webServers.length; i++)
		{
			webServers[i] = new webserver(i, avg + (i + 1 <= rem ? 1 : 0), b, smServers, mutex);
			serverThreads[i] = new Thread(webServers[i]);
			serverThreads[i].start();
		}

		avg = totalElements / users.length;
		rem = totalElements % users.length;

		for(int i = 0; i < users.length; i++)
		{
			users[i] = new user(i, avg + (i + 1 <= rem ? 1 : 0), b, smUsers, mutex);
			userThreads[i] = new Thread(users[i]);
			userThreads[i].start();
		}

		for(int i = 0; i < userThreads.length; i++)
		{
			try{

				userThreads[i].join();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for(int i = 0; i < serverThreads.length; i++)
		{
			try{

				serverThreads[i].join();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//Equally subdivide user inputted elements across all user objects

		System.out.println("-----------------------");

		//Outputs the total number of elements added/removed from user and webserver		

		System.out.println("-----------------------");

		//System.out.println("Buffer has " + X + " elements remaining");			
		//Check to see buffer if all elements produced from users have been successfully removed by webservers
		
		System.out.println("-----------------------");

		//Checks if all users and web servers successfully finished
					
		long endTime = System.currentTimeMillis();
		System.out.println("-----------------------");
		System.out.println("Program took " + (endTime - startTime) + " milliseconds to complete");		
	
	}
	
	public static void main(String[] args)
	{
		startServer start = new startServer();
	}
}
