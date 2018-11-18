package ru.shipova.simpleChatClient;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleChatClient {

    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;

    public static void main(String[] args) {
        SimpleChatClient client = new SimpleChatClient();
        client.go();
    }

    public void go() {
        JFrame frame = new JFrame("Ludicrously Simple Chat Client");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15, 50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        setUpNetworking();

        //запускаем новый поток, используя вложенный класс в качестве Runnable.
        // Работа потока заключается в чтении данны с сервера через сокет и выводе любых входящих сообщений
        // в прокручиваемую текстовую область.
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }

    private void setUpNetworking(){
        //Используем сокет для получения входящего и иходящего потоков.
        // Исходящий поток уже задействован для отправки данных, но теперь к нему добавися входящий поток,
        // поэтому наш объект Thread может поулчать сообщения от сервера.
        try{
            sock = new Socket("127.0.0.1", 5000);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("networking established");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        //когда пользователь нажимает кнопку "Send", содержимое текстового поля отправляется на сервер.
    public class SendButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            try{
                writer.println(outgoing.getText());
                writer.flush();
            } catch (Exception ex){
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    //Это работа, которую выполняет поток! В методе run() поток входит в цикл ()пока ответ сервера будет равняться null),
    // ситывает за раз одну строкуи добавляет её в прокручиваемую текстовую область (используя в конце символ переноса строки).

    public class IncomingReader implements Runnable{
        public void run() {
            String message;
            try{
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + message);
                    incoming.append(message + "\n");
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
