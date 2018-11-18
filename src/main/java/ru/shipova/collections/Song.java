package ru.shipova.collections;
//метод compareTo возвращает:
//отрицательное число, если объект (переданный в качестве аргумента) больше объекта, из которого вызвали метод
//ноль, если объекты равны
//положительное число, если объект (переданный в качестве аргумента) больше объекта, из которого вызвали метод

public class Song  implements Comparable<Song>{
    private String title;
    private String artist;
    private String rating;
    private String bpm;

    @Override
    public boolean equals(Object aSong) {
        Song s = (Song) aSong;
        return getTitle().equals(s.getTitle());
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    public int compareTo(Song s){
        return title.compareTo(s.getTitle());
    }

    Song (String title, String artist, String rating, String bpm){
        this.title = title;
        this.artist = artist;
        this.rating = rating;
        this.bpm = bpm;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getRating() {
        return rating;
    }

    public String getBpm() {
        return bpm;
    }

    @Override
    public String toString() {
        return artist + ":" + title;
    }
}
