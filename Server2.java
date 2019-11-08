import java.net.*;
import java.io.*;
import java.awt.*;


public class Server2
{
	ClientHandler[] ch = new ClientHandler[10];
	
    public static void main(String[] args)
    {
   	    Server2 server = new Server2();
    }
   	
   public Server2()
   {
   	    try
   	    {
   		    ServerSocket anotherS = new ServerSocket(8080);

   		    for(int i = 0; i < 10; i++)
   		    {
              	Socket anotherSO  = anotherS.accept();	
          		ch[i] = new ClientHandler(anotherSO);    		
   		    }
         }
   	        catch(IOException x) {};
   	
    }
   
    private class ClientHandler implements Runnable
    {
        DataInputStream input;
        DataOutputStream output;
        Thread t;
        	
        public ClientHandler(Socket anotherSO)
        {
        	try
        	{
              	input = new DataInputStream(anotherSO.getInputStream());
               	output = new DataOutputStream(anotherSO.getOutputStream());
        	}
        	catch(IOException x) {};
        		
        	t = new Thread(this);
         	
            t.start();
        		
        }
       	
        public void run()
        {
          	while(true)
          	{
             	try
             	{
                    String message = input.readUTF();
           	        System.out.print("Received ");
                   	for (int i = 0; i < message.length(); i++)
                   	    System.out.print((int)(message.charAt(i))+" ");
                   	System.out.println();
                   
                   
                   for(int i = 0; i < 10; i++)
                   {
                   	    if(ch[i] != null)
                   	    {
                   	        System.out.print(i + " About to send ");
                   	        for (int j = 0; j < message.length(); j++)
                   	            System.out.print((int)(message.charAt(j))+" ");
                   	        System.out.println();
                   	        
                   	        ch[i].output.writeUTF(message);
                   	    }
                   		    
                   }
             	}
             	catch(IOException x) {};
          	}
        }
    }

}
