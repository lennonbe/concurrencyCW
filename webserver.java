import java.util.*;

class webserver implements Runnable
{	
  
  //Web server removes elements from the buffer
  private int id, n;
  private int elementsToRemove;
  private semaphore semaphore, elementsMutex;
  public static Buffer buf;

  public webserver(int i, int el, Buffer b, semaphore sem, semaphore mutex)			
  {
    id = i;
    elementsToRemove = el;
    buf = b;
    semaphore = sem;
    elementsMutex = mutex;
  }

  public void run()
  {    
    n = buf.buf_size;
    if(buf.elements == 0)
    {
      System.out.println("Buffer empty, web server wait");
    }
    else
    {
      while (elementsToRemove > 0)
      {	
        semaphore.P();
        elementsMutex.P();	
        			
        buf.remove(n);
        n--;
        elementsToRemove--;
        System.out.println("Server " + id + " removes an element " + buf.elements + "/" + buf.buf_size);

        elementsMutex.V();
        semaphore.V();		

        /*n--;
        elementsToRemove--;*/

        //System.out.println("Server " + id + " removes an element " + buf.elements + "/" + buf.buf_size);
      }	

      //System.out.println("User " + id + " removed " + elementsToRemove + " elements");
    }		
  }
  
}   