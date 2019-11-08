public class otp {
        
        
        public static String encrypt(String message, String key) {
               
        	    char[] eMessage = new char[message.length()];
                char[] messageA = message.toCharArray();
                char[] keyA = key.toCharArray();
                int result = 0;
                
                for (int i = 0; i < message.length(); i++) {
                	
                	if(key.length() > 0){
                    result = (int) messageA[i] ^ (int) keyA[i % key.length()];
                    eMessage[i] = (char) (result);
                }
                }
                
                return new String(eMessage);
        } 
        
      } 