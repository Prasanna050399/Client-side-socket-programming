//package CLI;
import java.net.*;
import java.util.Scanner;
import java.io.*;

class Threader implements Runnable
{
	private Thread t1,t2;


	public Threader()
	{
		t1=new Thread(this,"t1");
		t2=new Thread(this,"t2");
		t1.start();
		t2.start();
	}
	public void run() 
	{
		Socket s1=null;
		try {
			s1 = new Socket("localhost",65050);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String st1,st2;
		while(true)
		{			
			if(Thread.currentThread()==t1)
			{
				DataOutputStream dout;
				try {
					dout = new DataOutputStream(s1.getOutputStream());
					Scanner scan=new Scanner(System.in);
					st1=scan.next();
					if(st1.equalsIgnoreCase("stop"))
					{
						s1.close();
						System.exit(0);
					}
					dout.writeUTF(st1);
					dout.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
			if(Thread.currentThread()==t2)
			{
				DataInputStream din;
				try {
					din = new DataInputStream(s1.getInputStream());
					st2=din.readUTF();
				System.out.println("Server says :"+st2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
class Client
{
	public static void main(String arg[])throws Exception
	{		
		new Threader();
	}
}
