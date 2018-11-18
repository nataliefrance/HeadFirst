package ru.shipova.DailyAdviceServer;

import java.net.*;
import java.io.*;

public class DailyAdviceServer {
    String[] adviceList = {"Ешьте меньшими порциями", "Два слова: не годится", "Вы прекрасны сегодня!", "Съеште что-нибудь полезное", "Пора отдохнуть", "Пора подстричься?"};

    public void go(){
        try {
            //Благодаря ServerSocket приложение отслеживает клиентские запросы на порту 4242 на том же компьютере,
            // где выполняется данный код
            ServerSocket serverSocket = new ServerSocket(4242);
            //Сервер входит в постонный цикл, ожидая клиентский подключений (и обслуживая их)
            while(true){
                //Метод accept() блокирует приложение до тех пор, пока не поступит запрос,
                // после чего возвращает сокет (на анонимном порту) для взаимодействия с клиентом
                Socket socket = serverSocket.accept();

                //Мы используем соединение объекта Socket с клиентом для создания экземпляра PrintWriter,
                //после чего отправляем с его помощью (println()) строку с советом.
                // Затем мы закрываем сокет, т.к. работа с клиентом закончена.
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    private String getAdvice(){
        int random = (int) (Math.random()*adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) {
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }
}
