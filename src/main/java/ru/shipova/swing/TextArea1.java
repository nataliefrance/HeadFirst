package ru.shipova.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextArea1 implements ActionListener {
    JTextArea text;

    public static void main(String[] args) {
        TextArea1 gui = new TextArea1();
        gui.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton button = new JButton("Just Clock It");
        button.addActionListener(this);
        //10 - это 10 строк, 20 - это 20 столбцов
        text = new JTextArea(10, 20);
        text.setLineWrap(true);

        //создаем объект JScrollPane и присваиваем ему текстовую область, которую он будет прокручивать.
        JScrollPane scroller = new JScrollPane(text);
        //указываем панели прокрутки использовать только вертикальную полосу
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //добавляем область прокрутки на панель
        panel.add(scroller);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane(). add(BorderLayout.SOUTH, button);

        frame.setSize(350, 300);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        text.append("button clicked \n"); //перенос строки, тобы слова переходили на новую строку при каждом нажатии
    }
}
