import java.rmi.Naming;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author ${Md Imran Hasan Tuhin 59412}
 * @author ${FAIQAH AYUNI BINTI ABDUL AZIZ 55919}
 * @author ${SHAFIZUL IZZAT BIN ROSIDI 57804}
 * @author ${NAIF LUQMAN BIN ADRIS 58867}
 *
 * ${Group 5}
 */

public class Client {
    public static void main(String[] args) {
        try {
            String $localhost = args[0];
            Scanner $input = new Scanner(System.in);
            Hello $stub = (Hello) Naming.lookup("rmi://"+$localhost+":5000/Hello");

            String[] two = {"9ab62b5ef34a985438bfdf7ee0102229", "89e6fe1fa168901c395c88258f56222d", "50ff364aba0bc0868a2ac51275f5cf42", "35d0c8147ab42170bc4edfd71c839cb9","045da889809e2c866ebdfc30ed51f240"};
            String[] three = {"7905c7e451a7c63bdd6dcf6efb0d0780", "715fc07c2b5cddb54a512726c1fb9a60", "d8d251ea48c0157e89a596dce431f4b2", "d75ff0bd4fbf57f7d7c37865ad8852b0", "964ee1caddaa2f0a47d341e3840046d7"};
            String[] four = {"11c2a661665daa76b2c8a69f55b0d852", "89969a74a7525edaa8891e866f0ecd72", "448d9d9ce22644d383631f6fd28d6087", "6b9193fbd15a5534b021fb4ebe91194c"};
            String[] five = {"cfb108eb059a909b1131edb93dfae4a9", "fa165a7dcfb581bbb167fa951ea8babf"};
            String[] six = {"68bbd2bcdce0c4b40b3a0a9b408db6fe"};

            //date and time of client terminal
            LocalDateTime $date = LocalDateTime.now();
            DateTimeFormatter $dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String $time = $date.format($dateFormat);
            //starting time of client
            long $startTime = System.currentTimeMillis();
            System.out.println("Hi! I am Client.");
            System.out.println("Client started "+$time);
            //call remote methods of result
            System.out.print("Enter number of thread: ");
            int $numberOfThread = $input.nextInt();

            System.out.print("Enter password Length: ");
            int $passLength = $input.nextInt();

            System.out.println("");
            if ($numberOfThread >= 1 && $passLength ==2){
                for (String $data:$stub.GenerateResult(two,$passLength,$numberOfThread)){
                    if (!$data.isEmpty()){
                        //print result from all servers
                        System.out.println($data);
                    }else {
                        //print error if no result found
                        System.out.println("No result has been found.");
                    }
                }
            }else if ($numberOfThread >= 1 && $passLength ==3){
                for (String $data:$stub.GenerateResult(three,$passLength,$numberOfThread)){
                    if (!$data.isEmpty()){
                        //print result from all servers
                        System.out.println($data);
                    }else {
                        //print error if no result found
                        System.out.println("No result has been found.");
                    }
                }
            }
            else if ($numberOfThread >= 1 && $passLength ==4){
                for (String $data:$stub.GenerateResult(four,$passLength,$numberOfThread)){
                    if (!$data.isEmpty()){
                        //print result from all servers
                        System.out.println($data);
                    }else {
                        //print error if no result found
                        System.out.println("No result has been found.");
                    }
                }
            }
            else if ($numberOfThread >= 1 && $passLength ==5){
                for (String $data:$stub.GenerateResult(five,$passLength,$numberOfThread)){
                    if (!$data.isEmpty()){
                        //print result from all servers
                        System.out.println($data);
                    }else {
                        //print error if no result found
                        System.out.println("No result has been found.");
                    }
                }
            }
            else if ($numberOfThread >= 1 && $passLength ==6){
                for (String $data:$stub.GenerateResult(six,$passLength,$numberOfThread)){
                    if (!$data.isEmpty()){
                        //print result from all servers
                        System.out.println($data);
                    }else {
                        //print error if no result found
                        System.out.println("No result has been found.");
                    }
                }
            }
            else {
                System.out.println("Input not excepted!!!");
                System.exit(0);
            }

            System.out.println("");

            //end date and time of client terminal
            LocalDateTime $dateObj = LocalDateTime.now();
            String $dateAndTimeEnd = $dateObj.format($dateFormat);
            System.out.println("Client close "+$dateAndTimeEnd);
            //end time of client
            long $endTime = System.currentTimeMillis();
            long $totalTime = ($endTime - $startTime)/1000;
            //print total time of client terminal
            System.out.println("Client terminal takes "+$totalTime+" seconds to get result.");
        } catch (Exception exp) {
            System.err.println("Client exception: " + exp.toString()); //print exceptions
            exp.printStackTrace(); //Stack of exceptions
            System.exit(1); //exit client terminal
        }
    }
}
