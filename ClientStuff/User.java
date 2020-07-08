package ClientStuff;

import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable {
    final static long serialVersionUID = 1L;
    private transient static Scanner scanner = new Scanner(System.in);
    private transient static User UserInstance;
    private transient Client client;
    private String login;
    private String password;

    private User(String login, String password, Client client){
        this.client = client;
        this.login = login;
        this.password = password;
    }

    public static User init(Client client){
        if (UserInstance == null){
            MODE mode = getMode();
            String l = null;
            String  p = null;
            boolean logged = false;

            if (mode == MODE.LOGIN){
                while (!logged) {
                    System.out.println("Enter login:");
                    l = scanner.nextLine();
                    System.out.println("Enter password:");
                    p = scanner.nextLine();
                    String[] params = new String[]{l, p};
                    client.writeCommand(new Command<>("login", params));
                    String msg = client.getRespond().getMsg();
                    if (msg.equals("Logged in successfully")){
                        logged = true;
                        System.out.println("Welcome, " + l);
                    }else {
                        System.out.println(msg);
                    }
                }
            }else {
                while (!logged){
                    System.out.println("Enter login:");
                    l = scanner.nextLine();
                    System.out.println("Enter password:");
                    p = scanner.nextLine();
                    String[] params = new String[]{l, p};
                    client.writeCommand(new Command<>("register", params));
                    String msg = client.getRespond().getMsg();
                    if (msg.equals("Registration is successful")){
                        logged = true;
                        System.out.println("Welcome, " + l);
                    }else {
                        System.out.println(msg);
                    }
                }
            }
            UserInstance = new User(l, p, client);
        }
        return UserInstance;
    }

    private enum MODE{
        REGISTER,
        LOGIN
    }

    private static MODE getMode(){
        MODE mode = null;
        System.out.println("Type \"1\" if you want to login, \"2\" if you want to register");
        while (mode == null) {
            switch (scanner.nextLine().trim()) {
                case "1":
                    mode = MODE.LOGIN;
                    break;
                case "2":
                    mode = MODE.REGISTER;
                    break;
                default:
                    System.out.println("Wrong value entered. Try another one");
                    break;
            }
        }
        return mode;
    }

    public String getLogin() {
        return login;
    }
}
