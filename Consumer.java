import java.util.*;
import java.awt.*;

public class Consumer extends  Thread
{
	private Buffer b;
	private int iter;
	Label lblValor;
    public Consumer(Buffer b, int iter)
    {
    	this.b=b;
    	this.iter=iter;
    }
    
    public Consumer(Buffer b, int iter, Label lbl)
    {
    	this.b=b;
    	this.iter=iter;
    	this.lblValor = lbl;
    }

    public void run()
    {	
    	while(b.v.length>0)
    	{
    		int aux;
    		try
    		{
				aux=b.extract();
				lblValor.setText(String.valueOf(aux));
	    		
	    		lblValor.repaint();
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		//System.out.println(i+": @@Consumer Consumes@@ "+aux);
    		
    	}
    }
	public static void main(String[] J)
	{
		
		Frame ventana = new Frame();
		Label lblTC = new Label("Valor a Consumir");
		Label lblTP = new Label("Valor Producido");
		Label lblVC = new Label("");
		Label lblVP = new Label("");
		
		lblVP.setSize(new Dimension(300,20));
		lblVC.setSize(new Dimension(300,20));		
		Panel pnlConsumidor = new Panel();
		Panel pnlProductor = new Panel();
		
		pnlConsumidor.add(lblTC);
		pnlConsumidor.add(lblVC);
		
		pnlProductor.add(lblTP);
		pnlProductor.add(lblVP);
		
		ventana.setLayout(new BorderLayout());
		ventana.add(pnlProductor, BorderLayout.NORTH);
		ventana.add(pnlConsumidor, BorderLayout.SOUTH);
		
		
		Scanner en=new Scanner(System.in);
		Buffer b=new Buffer(5);
		System.out.println("type number of iterations");
		int it=en.nextInt();
		Producer p=new Producer(b,it,lblVP);
		Consumer c=new Consumer(b,it, lblVC);
		p.start();
		c.start();
		
		
		ventana.setSize(new Dimension(400,100));
		ventana.setVisible(true);
		
		
	}  
}