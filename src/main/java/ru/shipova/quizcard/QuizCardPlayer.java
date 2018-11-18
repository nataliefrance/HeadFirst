package ru.shipova.quizcard;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class QuizCardPlayer {

    private JTextArea display;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;
    private QuizCard currentCard;
    private int currentCardIndex;
    private JFrame frame;
    private JButton nextButton;
    private boolean isShowAnswer;

    public static void main (String [] args){
        QuizCardPlayer reader = new QuizCardPlayer();
        reader.go();
    }

    public void go(){
//формируем gui

        frame = new JFrame("Quiz Card Player");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sansserif", Font.BOLD, 24);

        display = new JTextArea(10, 20);
        display.setFont(bigFont);

        display.setLineWrap(true); //перенос текста на другую строку включён
        display.setEditable(false);

        JScrollPane qScroller = new JScrollPane(display);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        nextButton = new JButton ("Show Question");
        mainPanel.add(qScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("file");
        JMenuItem loadMenuItem = new JMenuItem("Load card set");
        loadMenuItem.addActionListener(new OpenMenuListener());
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(640, 500);
        frame.setVisible(true);
    }

    public class NextCardListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
//проверяем значение флага isShowAnswer, чтобы узнать, что сейчас отображается - вопрос или ответ, и в зависимости от результата выполняем соответствующие действия.
            if (isShowAnswer){
//показываем ответ, т.к. вопрос уже был увиден
                display.setText(currentCard.getAnswer());
                nextButton.setText("Next Card");
                isShowAnswer = false;
            } else {
//показываем следующий вопрос
                if (currentCardIndex < cardList.size()) {
                    showNextCard();
                } else {
//Больше карточек нет!
                    display.setText("That was last card");
                    nextButton.setEnabled(false);
                }
            }
        }
    }
    public class OpenMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent ev){
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());//Вызываем файловое окно, позволяющее выбирать файл для открытия
        }
    }

    private void loadFile(File file){

        cardList = new ArrayList<QuizCard>();
        try{
//Создаём BufferedReader, связанный с новым FileReader. Предоставляем объекту FileReader объект File, выбранный пользователем в окне открытия файла
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
//Читаем по одной строке за один раз, передавая результат в метод makeCard(), который разделяет его и преобразует в настоящий объект QuizCard, а затем добавляет в ArrayList.
            while ((line = reader.readLine()) != null){
                makeCard(line);
            }
            reader.close();
        } catch (Exception ex){
            System.out.println("Couldn’t read the card file");
            ex.printStackTrace();
        }

//Пришло время показать первую карточку
        showNextCard();
    }

    private void makeCard(String lineToParse){
//Каждая строка текста соответствует одной флешкарте, но нам нужно разделить вопрос и ответ. Для этого мы используем split() из класса String, который разбивает строку на две лексемы (одна для вопроса, одна для ответа)
        String[] result = lineToParse.split("/");
        QuizCard card = new QuizCard(result[0], result[1]);
        cardList.add(card);
        System.out.println("made a card");
    }

    private void showNextCard(){
        currentCard = cardList.get(currentCardIndex);
        currentCardIndex++;
        display.setText(currentCard.getQuestion());
        nextButton.setText("Show Answer");
        isShowAnswer = true;
    }
}
