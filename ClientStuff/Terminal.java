package ClientStuff;

import CityStructure.City;
import org.apache.commons.lang3.SerializationException;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Terminal {
    private Client client;
    private Scanner scanner = new Scanner(System.in);
    private String newLine = new String();
    private Command command = new Command();

    public Terminal(Client client, User user) {
        this.client = client;
        command.setUser(user);
    }

    public void start() {
        try {
            while (true) {
                newLine = scanner.nextLine().trim();
                String[] arr = newLine.split(" ", 2);
                if (arr.length == 1) {
                    commandManager(arr[0], null);
                } else {
                    commandManager(arr[0], arr[1]);
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("End of input");
            System.exit(0);
        }
    }


    private void commandManager(String arr1, String arr2) {
        try {
            switch (arr1) {
                case "exit":
                    System.out.println("Client will be terminated now");
                    System.exit(0);
                    break;
                case "add":
                case "add_if_max":
                case "remove_lower":
                    String json = readJSON(arr2);
                    System.out.println("json is: " + json);
                    if (json != null) {
                        City c = client.makeCityFromJSON(json);
                        if (c != null) {
                            command.setEverything(arr1, c);
                        } else {
                            command.setEverything(arr1, null);
                        }
                        client.writeCommand(command);
                    } else {
                        command.setEverything(arr1, null);
                        client.writeCommand(command);
                    }
                    break;
                case "remove_any_by_governor":
                    if (arr2 != null) {
                        command.setEverything(arr1, client.makeHumanFromJSON(arr2));
                        client.writeCommand(command);
                    } else {
                        command.setEverything(arr1, null);
                        client.writeCommand(command);
                    }
                    break;
                case "execute_script":
                    command.setEverything(arr1, arr2);
                    client.writeCommand(command);
                    break;
                case "update":
                    if (arr2 != null) {
                        String args[] = arr2.split(" ", 2);
                        if (args.length == 1){
                            command.setEverything("update", null);
                            boolean numberChanged = false;
                            while (!numberChanged) {
                                try {
                                    command.setAdditional(Long.parseLong(args[0]));
                                    numberChanged = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Entered ID is not a number. Enter new one:");
                                    args[0] = scanner.nextLine();
                                }
                            }
                            System.out.println("Entered city is null. Enter new one:");
                            command.setArgument(client.makeCityFromJSON(readJSON(scanner.nextLine())));
                        }else{
                            command.setEverything("update", null);
                            boolean numberChanged = false;
                            while (!numberChanged) {
                                try {
                                    command.setAdditional(Long.parseLong(args[0]));
                                    numberChanged = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Entered ID is not a number. Enter new one:");
                                    args[0] = scanner.nextLine();
                                }
                            }
                            command.setArgument(null);
                            command.setArgument(client.makeCityFromJSON(args[1]));
                        }
                    }else {
                        command.setEverything("update", null);
                        command.setAdditional(null);
                    }
                    client.writeCommand(command);
                    break;
                default:
                    command.setEverything(arr1, arr2);
                    client.writeCommand(command);
                    break;
            }
            System.out.println(client.getRespond().getMsg());
        }catch (SerializationException e){
            System.out.println("Error occurred while receiving message from the server. Try later");
        }

    }

    private String readJSON(String beginning){
        String toReturn = "";
        int open = 0;
        int close = 0;
        if (beginning != null) {
            if (beginning.contains("{")) {
                open++;
                toReturn = beginning;
                if (beginning.contains("}")) {
                    close++;
                }
                while (open != close) {
                    if (scanner.hasNext()) {
                        String s = scanner.nextLine();
                        if (s.contains("{")) {
                            open++;
                            toReturn = toReturn + s;
                        }
                        if (s.contains("}")) {
                            close++;
                            toReturn = toReturn + s;
                        } else {
                            toReturn = toReturn + s;
                        }
                    } else {
                        System.out.println("End of input");
                        System.exit(0);
                    }
                }
            } else {
                toReturn = beginning;
            }
        }else {
            toReturn = null;
        }

        return toReturn;
    }
}