package ru.shipova.DailyAdviceClient;
import java.io.*;
import java.net.*;

public class DailyAdviceClient {

public void go(){
    try{
        //Создаём соединение через сокет к приложению, работающему на порту 5050 на компьютере, где выполняется код
        Socket socket = new Socket("192.168.1.30",5000);
        InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());

        //Подключаем BufferedReader  к InputStreamReader (который соединён с исходящим потоком сокета)
        BufferedReader reader = new BufferedReader(streamReader);
        String advice = reader.readLine();
        System.out.println("Сегодня ты должен: " + advice);
        reader.close();;

    } catch (IOException ex){
        ex.printStackTrace();
    }
}

    public static void main(String[] args) {
        DailyAdviceClient client = new DailyAdviceClient();
        client.go();
        //выскочит исключение java.net.ConnectException: Connection refused: connect
    }
}
