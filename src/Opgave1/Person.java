package Opgave1;

public class Person {
    String navn;
    String title;
    boolean senior;

    public Person(String navn, String title, boolean senior) {
        this.navn = navn;
        this.title = title;
        this.senior = senior;
    }

    public String getNavn() {
        return navn;
    }

    public String getTitle() {
        return title;
    }

    public boolean isSenior() {
        return senior;
    }

    @Override
    public String toString() {
        return navn + " - " + title + (senior ? " (Senior)" : "");
    }
}
