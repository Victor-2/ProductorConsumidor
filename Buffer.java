import java.util.concurrent.*;

public class Buffer
{
	public int[] v;
	private int i=0,j=0;
	private Semaphore mutex=new Semaphore(1,true);
	private Semaphore iDate= new Semaphore(0,true);
	private Semaphore iSpace;
	
	public Buffer(int t)
	{
		v=new int[t];
		iSpace=new Semaphore(v.length,true);
	}
	public void Put(int date) throws InterruptedException
	{
		iSpace.acquire();
		mutex.acquire();
		v[i]=date;
		System.out.println("producer produces "+date);
		i=(i+1)%v.length;
		mutex.release();
		iDate.release();	
	}
	public int extract () throws InterruptedException
	{
		iDate.acquire();
		mutex.acquire();
		int aux=j;
		j=(j+1)%v.length;
		System.out.println("consumer consumes "+v[aux]);
		mutex.release();
		iSpace.release();
		return v[aux];
	}
}