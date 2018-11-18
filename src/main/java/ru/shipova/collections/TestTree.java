package ru.shipova.collections;

import java.util.*;

public class TestTree {
    public static void main(String[] args) {
        new TestTree().go();
        new Test().go();
    }

    public void go(){
        Book b1 = new Book("Alice");
        Book b2 = new Book("Harry Potter");
        Book b3 = new Book("Lord of the Ring");

        TreeSet<Book> tree = new TreeSet<>();
        tree.add(b1);
        tree.add(b2);
        tree.add(b3);
        System.out.println(tree);
    }
    //Элементы в списке TreeSet обязаны иметь тип, который реализует интерфейс Comparable
    static class Book implements Comparable {
        String title;
        public Book(String title){
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }

        @Override
        public int compareTo(Object o) {
            Book book = (Book) o;
            return (title.compareTo(book.title));
        }
    }

    //Или при создании TreeSet нужно использовать перегруженный конструктор, который принимает Comparator
    public static class BookCompare implements Comparator<Book>{
        @Override
        public int compare(Book o1, Book o2) {
            return (o1.title.compareTo(o2.title));
        }
    }

    static class Test{
        public void go(){
            Book b1 = new Book("Alice");
            Book b2 = new Book("Harry Potter");
            Book b3 = new Book("Lord of the Ring");

            BookCompare bCompare = new BookCompare();
            TreeSet<Book> treeSet= new TreeSet<>();
            treeSet.add(b1);
            treeSet.add(b2);
            treeSet.add(b3);
            System.out.println(treeSet);
        }

    }

}
