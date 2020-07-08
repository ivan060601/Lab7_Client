import ClientStuff.Client;
import ClientStuff.Terminal;
import ClientStuff.User;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        for(int i = 0;i<11;i++){

        }

        try {
            Client client = new Client();
            User user = User.init(client);
            Terminal terminal = new Terminal(client, user);
            terminal.start();
        }catch (IllegalArgumentException e){
            System.out.println("Server name you entered is incorrect. Client will be shut down now.");
            System.exit(0);
        }catch (NoSuchElementException e){
            System.out.println("End of input");
            System.exit(0);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Internal error occurred. Client will be shut down now.");
            System.exit(0);
        }
    }


}