import java.util.*;

public class Buffer							//Provides data and operations onto the fixed-length buffer
{     									
  private LinkedList<Object> buf_list;		
  public int elements = 0;						//Number of elements currently in the buffer
  public int buf_size;						//Maximum number of elements allowed on queue

  public Buffer(int n)						//Queue creation, with n indicating the maximum capacity
  {
    buf_list = new LinkedList<Object>();
    buf_size = n;
  }

  public void add(int input)//Add element to queue
  {	  
    buf_list.add(input);
    elements++;
  }

  public void remove(int input)//Remove element from queue
  {	  
    buf_list.remove(input);
    elements--;
  }
}	  
