package com.kukharev.CRUD;

import java.io.Serializable;

public class Record implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private char firstChar;
    private char secondChar;
    private int someNumber;

    // Constructor
    public Record(int id, char firstChar, char secondChar, int someNumber) {
        this.id = id;
        this.firstChar = firstChar;
        this.secondChar = secondChar;
        this.someNumber = someNumber;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public char getFirstChar() { return firstChar; }
    public void setFirstChar(char firstChar) { this.firstChar = firstChar; }

    public char getSecondChar() { return secondChar; }
    public void setSecondChar(char secondChar) { this.secondChar = secondChar; }

    public int getSomeNumber() { return someNumber; }
    public void setSomeNumber(int someNumber) { this.someNumber = someNumber; }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", firstChar=" + firstChar +
                ", secondChar=" + secondChar +
                ", someNumber=" + someNumber +
                '}';
    }
}
