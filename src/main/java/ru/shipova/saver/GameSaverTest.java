package ru.shipova.saver;

import java.io.*;

public class GameSaverTest {
    public static void main(String[] args) {
        GameCharacter one = new GameCharacter(50, "Elf", new String[]{"bow", "sword"});
        GameCharacter two = new GameCharacter(200, "Troll", new String[]{"hands", "big axe"});
        GameCharacter three = new GameCharacter(120, "Magician", new String[]{"incantations", "invisibility"});

        //представляем код, который может изменить значения состояния персонажей

        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Game.txt"));
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);
            os.close();

        } catch (IOException ex){
            ex.printStackTrace();
        }
        //присвоим персонажам значения null, чтобы мы не смогли обратиться к объектам в "куче"
        one = null;
        two = null;
        three = null;

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("Game.txt"));
            GameCharacter oneRestore = (GameCharacter) is.readObject();
            GameCharacter twoRestore = (GameCharacter) is.readObject();
            GameCharacter threeRestore = (GameCharacter) is.readObject();

            System.out.println("Тип первого: " + oneRestore.getType());
            System.out.println("Тип второго: " + twoRestore.getType());
            System.out.println("Тип третьего: " + threeRestore.getType());

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
