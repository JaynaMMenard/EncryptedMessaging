import java.net.*;
import java.io.*;


public class Client
{
   public static void main(String[] args)
   {
   	try
   	{
     	  	Socket createdSO  = new Socket("localhost", 8080);
     	  	TextingFrame sendingA = new TextingFrame (100,100,createdSO);
      		
   	}
   	catch(IOException x) {};
   	

   }
} 