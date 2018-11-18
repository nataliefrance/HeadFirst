package ru.shipova.collections;
import java.util.*;

public class SortMountains {
    LinkedList<Mountain> mountains = new LinkedList<>();
    class NameCompare implements Comparator<Mountain>{
        @Override
        public int compare(Mountain o1, Mountain o2) {
            return o1.name.compareTo(o2.name);
        }
    }
    class HeightCompare implements Comparator<Mountain>{
        @Override
        public int compare(Mountain o1, Mountain o2) {
            return o2.height - o1.height;
        }
    }

    public static void main(String[] args) {
        new SortMountains().go();
    }
    public void go(){
        mountains.add(new Mountain("Лонг-Рейндж", 14255));
        mountains.add(new Mountain("Эльберт", 14433));
        mountains.add(new Mountain("Марун", 14156));
        mountains.add(new Mountain("Касл", 14265));

        System.out.println("В порядке добавления: \n" + mountains);

        NameCompare nc = new NameCompare();
        Collections.sort(mountains, nc);
        System.out.println("По названию: \n" + mountains);

        HeightCompare hc = new HeightCompare();
        Collections.sort(mountains, hc);
        System.out.println("По высоте: \n" + mountains);
    }
}

class Mountain{
    String name;
    int height;

    Mountain(String name, int height){
        this.name = name;
        this.height = height;
    }

    @Override
    public String toString() {
        return name + " " + height;
    }
}
