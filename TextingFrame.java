import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;

public class TextingFrame extends Frame implements ActionListener, Runnable
{
  	DataInputStream  input;
	DataOutputStream output;


	JTextArea TA              =  new JTextArea();
	JLabel mT                 =  new JLabel("Enter message");
	JTextField  messageTF     = new JTextField();
	JLabel k                  = new JLabel("Enter key");
	JTextField  key           = new JTextField();
	JLabel user               = new JLabel("UserName");
	JTextField  nameTF        = new JTextField();
	
	
	
	String title = "Gotta have my Wheaties 2.0";
	
	Thread t;
	
	
	
	public TextingFrame(int x, int y, Socket socket)
	{
		try
		{
			input  = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
			
		}
		catch(IOException exception){};
   	
		
		setLayout(null);
		
		this.setBackground(new Color(177, 163, 207));
		this.setTitle(title);
		this.setForeground(new Color(71,55,108));
		TA.setEditable(false);
		
	    setBounds(x, y, 700, 500);
   	    setVisible(true);
   	    TA.setFont(new Font("Times New Roman", Font.ITALIC, 20)); 
   
     	
       	add(TA);            TA.setBounds(60, 60, 500, 250);
       	add(mT);            mT.setBounds(30,335,100,20);
       	add(messageTF); 	messageTF.setBounds(60, 360, 420, 20);  messageTF.addActionListener(this);
       	add(user);          user.setBounds(550, 340, 70, 20);
       	add(nameTF);        nameTF.setBounds(550, 360, 70, 20);
       	add(k); 	        k.setBounds(30, 399, 420, 20);
     	add(key);           key.setBounds (60, 420, 420, 20); 
     	
       	
            t = new Thread(this);   t.start();
       	               
	}   
     
     
	 public void actionPerformed(ActionEvent e)
	 {
	 	
		String message   = messageTF.getText();
		String k         = key.getText();
		String name      = nameTF.getText();
		
		try
		{
			if(k.length() >= message.length()) {
		       
		        String enc = otp.encrypt(message, k);
		        
		        for (int i = 0; i < enc.length(); i++)
		        	
		        System.out.print((int)(enc.charAt(i)) + " ");
		        System.out.println();
		        
		             output.writeUTF(name+":" + enc);
			}
			else {
			JOptionPane.showMessageDialog(null, "Key length needs to be equal to or greater than the message");
			}  
		 }
		    catch(IOException x) {};

		    messageTF.setText("");
	  }
	 
	 
	public void run()
	{
		while(true)
		{
			try
			{
				String message  = input.readUTF();
				String k        = key.getText();
				String name;
				String enc;
				
				int colon       = message.indexOf(":");
				name            = message.substring(0, colon);
				enc             = message.substring(colon+1);
		        enc             = otp.encrypt(enc, k);
		        
		          TA.append("\n" + name + ": " + enc);
		        
		        for (int i = 0; i < enc.length(); i++)
		        
		        System.out.print((int)(enc.charAt(i)) + " ");
		        System.out.println();
				
				
			}
			   catch(IOException x) {};
		}	
	}
}

