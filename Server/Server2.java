import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author ${Md Imran Hasan Tuhin 59412}
 * @author ${FAIQAH AYUNI BINTI ABDUL AZIZ 55919}
 * @author ${SHAFIZUL IZZAT BIN ROSIDI 57804}
 * @author ${NAIF LUQMAN BIN ADRIS 58867}
 *
 * ${Group 5}
 */

// Implementing the remote interface
public class Server2 extends UnicastRemoteObject implements Hello {

    public static long startTime;
    public static long averageTime = 0;
    public static long totalTime = 0;

    public Server2() throws RemoteException
    {
        super();
    }
    //method to init the server with name
    public synchronized void Banner(String $name) {
        LocalDateTime $date = LocalDateTime.now();
        DateTimeFormatter $dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String $now = $date.format($dateFormat);
        System.out.println("Hi! I am a Server....");
        System.out.println("Server name "+$name+" started "+$now);
        System.out.println($name+" started....");
    }

    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n",threadName,message);
    }

    public static class MessageLoop
            implements Runnable {
        int passLength; String hashTest;
        long startTime = System.currentTimeMillis();//get the starting time to find prime numbers in each threads


        MessageLoop(String hashText, int passLength){
            this.hashTest = hashText;
            this.passLength = passLength;

        }

        public void run() {
            String result = BruteForce.StringDecode(hashTest,passLength);

            threadMessage(result);

            String threadName2 = Thread.currentThread().getName();//get current thread number
            long timeTakenByThread = ((System.currentTimeMillis() - startTime)/1000);//get the time taken to find the prime numbers for each thread
            String timeTakenByThread2 =  Long.toString(timeTakenByThread );//convert to long
            System.out.println("");
            System.out.format("Time taken by %s: %s seconds", threadName2, timeTakenByThread2);//print time taken to find the prime numbers for each thread
            System.out.println("");
            System.out.println("");

            totalTime = totalTime + timeTakenByThread;//calculate the total time taken to find prime numbers by all threads
        }

    }

    //implement interface method server registration
    public synchronized void RegisterSubServer(String $ip,String $name){}

    //implement interface method to return result to client
    public synchronized ArrayList<String> GenerateResult(String[] $hashList,int $passLength,int $quantity) { return null;}

    //method to generate number of threads
    public synchronized String[] GenerateResultAtServer(String[] $hashList,int $passLength,int $quantity) throws InterruptedException{
        int hashLength = $hashList.length;
        String[] $output = new String[hashLength];
        long patience = 2000 * 60 * 60;

        System.out.println("");
        System.out.println("");
        threadMessage("No of Threads : " + $quantity);//main not start thread yet
        threadMessage("Start searching password for:");
        for (String $hashText: $hashList){
            System.out.println($hashText);
        }
        System.out.println("");
        System.out.println("");

        Thread t[] = new Thread[hashLength];

        for(int d=0; d<hashLength; d++) {
            t[d] = new Thread(new MessageLoop($hashList[d],$passLength));
            t[d].start();//start using thread


            // loop until MessageLoop
            // thread exits
            while (t[d].isAlive()) {
                // threadMessage("Still waiting...");
                // Wait maximum of 1 second
                // for MessageLoop thread
                // to finish.
                t[d].join(1000);
                if (((System.currentTimeMillis() - startTime) > patience)
                        && t[d].isAlive()) {
                    threadMessage("!Tired of waiting....");
                    t[d].interrupt();
                    // Shouldn't be long now
                    // -- wait indefinitely
                    t[d].join();
                }
            }
            $output[d] = "From "+String.valueOf(t[d])+" String is : "+BruteForce.StringDecode($hashList[d],$passLength);
        }

        System.out.println("");

        threadMessage("End service.");
        System.out.println("");
        System.out.println("Total time taken by server : "+totalTime+" seconds");//print the average time

        averageTime = totalTime / $quantity;//get the computational average time to find the prime numbers by all threads
        System.out.println("");
        System.out.println("Average computational time : "+averageTime+" seconds");//print the average time
        System.out.println("");
        return $output;
    }

    //look up loadBalancer and rebind the server with specific name
    public static void main(String[] args){
        try {
            String $localhost = args[0];
            String $LoadIp = args[1];
            String $serverName = "Server"+ new Random().nextInt(10000);
            System.out.println(java.net.InetAddress.getLocalHost());
            //look up remote LoadBalancer to connect
            Hello $obj = (Hello)Naming.lookup("rmi://"+$LoadIp+":5000/Hello");
            $obj.RegisterSubServer($localhost,$serverName);
            Server2 $server = new Server2();
            $server.Banner($serverName);
            //rebind the servers with specific name
            Naming.rebind("rmi://"+ $localhost +":5000/"+ $serverName,$server);
        }catch (Exception exp){
            //show exceptions if occur any exceptions
            System.err.println("Server exception: " + exp.toString());
            exp.printStackTrace();
            System.exit(1);
        }
    }

}

