import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

class MacAdress
{
	public static void main(String[] args)
	{
		try {         
   				InetAddress address = InetAddress.getLocalHost();  
   				NetworkInterface ni = NetworkInterface.getByInetAddress(address);  
  				byte[] mac = ni.getHardwareAddress();  
  				for (int i = 0; i < mac.length; i++) 
  				{             
    			   System.out.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");  
   				}

			}
		catch (UnknownHostException e) 
		{  
 			  e.printStackTrace();      
		}
		catch (SocketException e) {  
 			  e.printStackTrace();  
		}

	}
}