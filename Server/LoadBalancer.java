import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.rmi.registry.*;

/**
 * @author ${Md Imran Hasan Tuhin 59412}
 * @author ${FAIQAH AYUNI BINTI ABDUL AZIZ 55919}
 * @author ${SHAFIZUL IZZAT BIN ROSIDI 57804}
 * @author ${NAIF LUQMAN BIN ADRIS 58867}
 *
 * ${Group 5}
 */

public class LoadBalancer extends UnicastRemoteObject implements Hello{
    //declare the class for server
    public static class server { public String $ipAddress; public String $serverName; }
    //arraylist of sever list
    public List< server > $listOfServer = new ArrayList< server >();
    public LoadBalancer() throws RemoteException { super(); }
    //method to init the loadBalancer
    public synchronized void Banner(String name) {
        LocalDateTime $date = LocalDateTime.now();
        DateTimeFormatter $dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String $dateAndTime = $date.format($dateFormat);
        System.out.println("Hi! I am LoadBalancer....");
        //print date and time of LoadBalancer Started
        System.out.println(name+" started "+$dateAndTime);
        System.out.println(name+" started....");
    }
    //method to register the server with parameter ip and name
    public synchronized void RegisterSubServer(String $ip, String $name) {
        server $serverObj = new server();
        $serverObj.$ipAddress = $ip;
        $serverObj.$serverName = $name;
        //add new server to server list and print it
        $listOfServer.add($serverObj);
        System.out.println();
        System.out.println("Server with ip " + $ip + " and binding " + $name + " has connected....");
        System.out.println();
        System.out.println("               *****Available server list*****                      ");
        //print all server added to LoadBalancer
        int i = 0;
        while (i < $listOfServer.size()) {
            System.out.println("server with ip " + $listOfServer.get(i).$ipAddress
                    + " and Name " + $listOfServer.get(i).$serverName);
            i++;
        }

    }

    //implement method of server from interface Hello
    public synchronized String[] GenerateResultAtServer(String[] $hashList,int $passLength,int $quantity){return null;}

    //collect result form servers and send back to client
    public synchronized ArrayList<String> GenerateResult(String[] $hashList,int $passLength,int $quantity) {
        ArrayList<String> $result = new ArrayList<String>();

        int $numberOfServer = $listOfServer.size();
        int $hashListLength = $hashList.length;

        String[] serverOne = new String[($hashListLength+1)/2];
        String[] serverTwo = new String[$hashListLength-serverOne.length];

        for (int i = 0; i < $hashListLength; i++)
        {
            if (i < serverOne.length)
                serverOne[i] = $hashList[i];
            else
                serverTwo[i - serverOne.length] = $hashList[i];
        }

        try{

            if ($numberOfServer>0) {
                Hello $server1 = (Hello) Naming.lookup("rmi://" + $listOfServer.get(0).$ipAddress + ":5000/" + $listOfServer.get(0).$serverName);
                String[] $data = $server1.GenerateResultAtServer(serverOne,$passLength,$quantity);
                Hello $server2 = (Hello) Naming.lookup("rmi://" + $listOfServer.get(1).$ipAddress + ":5000/" + $listOfServer.get(1).$serverName);
                String[] $data2 = $server2.GenerateResultAtServer(serverTwo,$passLength,$quantity);

                System.out.println();
                System.out.println("              ****Result form " + $listOfServer.get(0).$serverName +"****            ");
                String $response = "Result form " + $listOfServer.get(0).$serverName;
                $result.add($response);
                System.out.println($response);

                for (String $output : $data) {
                    System.out.println($output);
                    $result.add($output);
                }

                System.out.println();
                System.out.println("              ****Result form " + $listOfServer.get(1).$serverName +"****            ");
                String $response2 = "Result form " + $listOfServer.get(1).$serverName;
                $result.add($response2);
                System.out.println($response2);

                for (String $output : $data2) {
                    System.out.println($output);
                    $result.add($output);
                }

                return $result;
            }
            else{
                System.out.println("!!Error!!No available server...");
            }
        }catch (Exception exp){
            System.err.println("Server exception: " + exp.toString());
            exp.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    //rebind the clients with LoadBalancer with registry 5000 and localhost
    public static void main(String args[]) {
        try {
            String $localhost = args[0];
            System.out.println(java.net.InetAddress.getLocalHost());
            LoadBalancer obj = new LoadBalancer();
            obj.Banner("LoadBalancer");
            LocateRegistry.createRegistry(5000);
            Naming.rebind("rmi://"+$localhost+":5000/Hello",obj);
        } catch (Exception exp) {
            System.err.println("Server exception: " + exp.toString());
            exp.printStackTrace();
            System.exit(1);
        }
    }
}

