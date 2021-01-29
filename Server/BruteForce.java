import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class BruteForce {

    public static String StringDecode(String hashText, int passLength){

        String password;
        String noResult = "Error!! no result has been found";
        char h,g,k,j,i,c;

        if(passLength==2){
            for (j = ' '; j<='~'; j++){
                for (i =' '; i<='~'; i++){

                        StringBuilder sbObj = new StringBuilder();
                        sbObj.append(j);
                        sbObj.append(i);
                        password = sbObj.toString();

                        if (BruteForce.getMd5(password).equals(hashText)){
                            return password;
                        }//end if
                }
            }

        }//end if

        else if(passLength==3){
            for (j = ' '; j<='~'; j++){
                for (i =' '; i<='~'; i++){
                    for (c =' '; c<='~'; c++){

                        StringBuilder sbObj = new StringBuilder();
                        sbObj.append(j);
                        sbObj.append(i);
                        sbObj.append(c);
                        password = sbObj.toString();

                        if (BruteForce.getMd5(password).equals(hashText)){
                            return password;
                        }//end if
                    }
                }
            }

        }//end if

        else if(passLength==4){
            for (k= ' '; k<='~'; k++){
                for (j = ' '; j<='~'; j++){
                    for (i =' '; i<='~'; i++){
                        for (c =' '; c<='~'; c++){

                            StringBuilder sbObj = new StringBuilder();
                            sbObj.append(k);
                            sbObj.append(j);
                            sbObj.append(i);
                            sbObj.append(c);
                            password = sbObj.toString();

                            if (BruteForce.getMd5(password).equals(hashText)){
                                return password;
                            }//end if
                        }
                    }
                }
            }
        }//end else if

        else if(passLength==5){
            for(g=' '; g<='~'; g++){
                for (k= ' '; k<='~'; k++){
                    for (j = ' '; j<='~'; j++){
                        for (i =' '; i<='~'; i++){
                            for (c =' '; c<='~'; c++){

                                StringBuilder sbObj = new StringBuilder();
                                //sbObj.append(g);
                                sbObj.append(k);
                                sbObj.append(j);
                                sbObj.append(i);
                                sbObj.append(c);
                                password = sbObj.toString();

                                if (BruteForce.getMd5(password).equals(hashText)){
                                    return password;
                                }//end if

                            }
                        }
                    }
                }
            }
        }//end else if

        else if(passLength==6){
            for(h=' '; h<='~'; h++){
                for(g=' '; g<='~'; g++){
                    for (k= ' '; k<='~'; k++){
                        for (j = ' '; j<='~'; j++){
                            for (i =' '; i<='~'; i++){
                                for (c =' '; c<='~'; c++){

                                    StringBuilder sbObj = new StringBuilder();
                                    sbObj.append(k);
                                    sbObj.append(j);
                                    sbObj.append(i);
                                    sbObj.append(c);
                                    password = sbObj.toString();

                                    if (BruteForce.getMd5(password).equals(hashText)){
                                        return password;
                                    }//end if

                                }
                            }
                        }
                    }
                }
            }

        }//end else if

        else{
            System.out.println("Invalid!! password length or hashtext");
        }
        return noResult;
    }

    public static String getMd5(String input){
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}