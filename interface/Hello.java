import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author ${Md Imran Hasan Tuhin 59412}
 * @author ${FAIQAH AYUNI BINTI ABDUL AZIZ 55919}
 * @author ${SHAFIZUL IZZAT BIN ROSIDI 57804}
 * @author ${NAIF LUQMAN BIN ADRIS 58867}
 *
 * ${Group 5}
 */

// Creating Remote interface for our application
public interface Hello extends Remote {
    public void Banner(String $name) throws RemoteException;
	public ArrayList<String> GenerateResult(String $hashList[],int $passLength,int $quantity) throws RemoteException, MalformedURLException, NotBoundException;
    public String[] GenerateResultAtServer(String $hashList[],int passLength,int $quantity) throws RemoteException, MalformedURLException, NotBoundException, InterruptedException;
    public void RegisterSubServer(String $ip,String $name) throws RemoteException;
}
