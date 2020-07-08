package ClientStuff;

import CityStructure.City;
import CityStructure.Human;
import ClientStuff.Checkers.CheckParameter;
import ClientStuff.Checkers.NullPointerChecker;
import ClientStuff.Checkers.WrongFieldChecker;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, реализующий все клиентские методы
 */
public class Client {
    private SocketAddress inetSocket;
    private Scanner scanner = new Scanner(System.in);
    private DatagramSocket datagramSocket;
    private DatagramPacket input;
    private DatagramPacket output;
    private byte[] buffer = new byte[64000];
    private Gson gson = new Gson();
    private NullPointerChecker np = new NullPointerChecker();
    private WrongFieldChecker wf = new WrongFieldChecker();

    public Client(){
        System.out.println("Enter a server address");
        String add = "";

        try {

            while (add == "") {
                String a = scanner.nextLine();
                //if (isRussian(a)){
                //    System.out.println("Entered server name contains inappropriate symbols");
                //}else {
                    add = a;
                    System.out.println("Server address is now: " + add);
                //}
            }

            System.out.println("Enter a port");
            int port = -1;

            while (port == -1) {
                try {
                    int p = Integer.valueOf(scanner.nextLine().trim());
                    if (p < 0 || p > 65535) {
                        System.out.println("Wrong port was entered");
                    } else {
                        port = p;
                        System.out.println("Port is now: " + port);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entered value is not a number");
                }
            }

            inetSocket = new InetSocketAddress(add, port);
            datagramSocket = new DatagramSocket();
            datagramSocket.setSoTimeout(3000);
            System.out.println("You can start working");

        }catch (NoSuchElementException e){
            System.out.println("End of input");
            System.exit(0);
        } catch (SocketException e) {
            System.out.println("Socket unreachable");
            System.exit(0);
        }
    }

    /**
     * Метод для определения русских букв в строке
     * @param str Строка, для анализа
     * @return Результат (True - содержит русские буквы, False - нет)
     */
    public boolean isRussian(String str)
    {
        char[] chr = str.toCharArray();
        for (int i = 0; i < chr.length; i++)
        {
            if (chr[i] >= 'А' && chr[i] <= 'я')
                return true;
        }
        return false;
    }

    /**
     * Метод для получения СТРОКИ
     * @return строка, полученная с сервера
     */
    public String getLine(){
        Arrays.fill(buffer, (byte) 0);
        input = new DatagramPacket(buffer, buffer.length);
        try {
            datagramSocket.receive(input);
        } catch (IOException e) {
            System.out.println("Error while receiving message from server");
        }
        byte[] b = input.getData();
        return new String(b);
    }

    /**
     * Метод для записи СТРОКИ на сервер
     * @param s строка, которую надо записать
     */
    public void writeLine(String s) {
        output = new DatagramPacket(s.getBytes(), s.getBytes().length, inetSocket);
        try {
            datagramSocket.send(output);
        } catch (IOException e) {
            System.out.println("Error while sending message to server");
        }
    }

    /**
     * Метод для записи МАССИВА ТИПА byte[] на сервер
     * @param array массив, который надо записать
     */
    public void writeBytes(byte[] array){
        output = new DatagramPacket(array, array.length, inetSocket);
        try {
            datagramSocket.send(output);
        } catch (IOException e) {
            System.out.println("Error while sending message to server");
        }
    }

    /**
     * Метод для получения МАССИВА ТИПА byte[] с сервера
     * @return массив с сервера
     */
    public byte[] getBytes(){
        Arrays.fill(buffer, (byte) 0);
        input = new DatagramPacket(buffer, buffer.length);
        try {
            datagramSocket.receive(input);
        } catch (IOException e) {
            System.out.println("Error while receiving message from server");
        }
        return input.getData();
    }

    /**
     * Метод для получения ГОРОДА с сервера
     * @return город с сервера
     */
    public City getCity(){
        return byteArrayToCity(this.getLine().getBytes());
    }

    /**
     * Метод для записи ГОРОДА на сервер
     * @param city город с сервера
     */
    public void writeCity(City city){
        this.writeLine(cityToByteArray(city).toString());
    }

    /**
     * Метод для записи ГОРОДА на сервер из JSON-строки
     * @param s строка в формате JSON
     */
    public void writeCityFromJSON(String s){
        try {
            City c = gson.fromJson(s, City.class);
            np.checkEverything(c, CheckParameter.WITH_ASKING);
            wf.checkEverything(c, CheckParameter.WITH_ASKING);
            this.writeCity(c);
        }catch (JsonSyntaxException e){
            System.out.print("JSON Syntax error. ");
        }catch (NumberFormatException e){
            System.out.print("Some number-fields have incorrect values. ");
        }
    }

    /**
     * Метод для конвертации JSON-строки в City
     * @param s строка в формате JSON
     * @return город
     */
    public City makeCityFromJSON(String s){
        try {
            City c = gson.fromJson(s, City.class);
            np.checkEverything(c, CheckParameter.WITH_ASKING);
            wf.checkEverything(c, CheckParameter.WITH_ASKING);
            return c;
        }catch (JsonSyntaxException e){
            System.out.print("JSON Syntax error. ");
        }catch (NumberFormatException e){
            System.out.print("Some number-fields have incorrect values. ");
        }
        return null;
    }

    /**
     * Метод для конвертации JSON-строки в Human
     * @param s строка в формате JSON
     * @return Human
     */
    public Human makeHumanFromJSON(String s){
        try {
            Human human = gson.fromJson(s, Human.class);
            if (human.getBirthday() == null){
                System.out.println("Looks like governors birthday is null. Enter new one:");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                boolean checker = false;
                while (!checker){
                    try {
                        human.setBirthday(format.parse(scanner.nextLine()));
                        checker = true;
                    } catch (ParseException e) {
                        System.out.println("Wrong date format. Try yyyy-MM-dd");
                    }
                }
            }
            return human;
        }catch (JsonSyntaxException e){
            System.out.print("JSON Syntax error. ");
        }catch (NumberFormatException e){
            System.out.print("Some number-fields have incorrect values. ");
        }
        return null;
    }

    /**
     * Метод для записи КОМАНДЫ на сервер
     * @param command команда для записи
     */
    public void writeCommand(Command command){
        this.writeBytes(commandToByteArray(command));
    }

    /**
     * Команда для получения ОТВЕТА с сервера
     * @return ответ с сервера
     */
    public Respond getRespond(){
        return byteArrayToRespond(getBytes());
    }

    private byte[] cityToByteArray(City city) {
        return SerializationUtils.serialize(city);
    }

    private City byteArrayToCity(byte[] array) {
        return SerializationUtils.deserialize(array);
    }

    private byte[] commandToByteArray(Command command){
        return SerializationUtils.serialize(command);
    }

    private Command byteArrayToCommand(byte[] array){
        return SerializationUtils.deserialize(array);
    }

    private byte[] respondToByteArray(Respond respond){
        return SerializationUtils.serialize(respond);
    }

    private Respond byteArrayToRespond(byte[] array){
        return SerializationUtils.deserialize(array);
    }
}
