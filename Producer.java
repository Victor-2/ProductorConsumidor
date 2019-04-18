
import java.util.*;

public class  Producer extends Thread 
{
   	private Random r=new Random();
    private Buffer b;
    private int iter;	
    private java.awt.Label lblValor;
    Timer delay = new Timer();
	public Producer(Buffer b, int iter)
	{
		this.b=b;
		this.iter=iter;
	}
	
	public Producer(Buffer b, int iter,  java.awt.Label  lbl)
	{
		this.b=b;
		this.iter=iter;
		this.lblValor = lbl;
	}

	public void run()
	{
		for(int i=1;i<=iter;i++)
		{
			try
			{
				int aux=r.nextInt(100);
				System.out.println(i+" :**Producer Produces**"+aux);	
				Thread.sleep(1000);
				b.Put(aux);
				lblValor.setText(String.valueOf(i)+ " :" + String.valueOf(aux));
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
     
    
