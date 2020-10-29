import java.util.*;

class user implements Runnable
{											
  private int id;
  private int elementsToAdd;
  private semaphore semaphore, elementsMutex;
  public static Buffer buf;
      
  /*
  Whatever class implements the runnable thread needs to take in the semaphore as a parameter
  and by doing this allows for a limited number of threads to be called.
  So essentially the semaphore will be created in the main method and limit the number of threads of type user that can run, similarly to a lock
  but with more than one thread accessing the buffer.
  */

  public user(int i, int el, Buffer b, semaphore sem, semaphore mutex)	        			//User arguments: User ID, number of elements to add, buffer and semaphore
	{

    id = i; 
    elementsToAdd = el; 
    buf = b;
    semaphore = sem;
    elementsMutex = mutex;
  
  }

  public void run()
  {   
    //Add element to buffer
    int n = 0;
    
    if(buf.elements == buf.buf_size)
    {
      System.out.println("Buffer full, user now sleeping");
    }
    else
    {
      while (elementsToAdd > 0)
      {	
        semaphore.V();
        elementsMutex.V();

        buf.add(n);
        n++;
        System.out.println("User " + id + " adds an element " + buf.elements + "/" + buf.buf_size);
        elementsToAdd--;
        
        elementsMutex.P();
        semaphore.P();
        
        /*n++;
        elementsToAdd--;*/

        //System.out.println("User " + id + " adds an element " + buf.elements + "/" + buf.buf_size);
      }	

      //System.out.println("User " + id + " added " + elementsToAdd + " elements");
    }
  }
}   