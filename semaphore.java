import java.util.*;
public class semaphore
{

  //This is an example class for using a primitive synchronization (semaphore, lock). Please note that you
  //can ONLY put the synchronization keyword within these type of classes, and nowhere else within the program.

  private int count = 0;

  public semaphore(int init_val)
  {
    count = init_val;
  }

  public synchronized void P() //wait method
  {
    count = count - 1;
    while (count < 0) 
    {
      try {

        wait(); //why not ‘if’?
          
      } catch (Exception e) {
          
        System.out.println("Error regarding wait() try catch");
      }
    }
  }

  public synchronized void V() //signal method
  {
    count = count + 1; /* if there is one, wake a waiter;
    *why not use ‘notify()’? */
    notifyAll();
  }

}

