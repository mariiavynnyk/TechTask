package org.test.genericTask;

//Create a generic class called Box<T> that can store an object of any type T. Include methods to
//set and get the value of the object.

public class Box<T> {
    private T value;

    public Box() {
        this.value = null;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>();
        integerBox.setValue(5);
        System.out.println("Integer value is: " + integerBox.getValue());

        Box<String> stringBox = new Box<>();
        stringBox.setValue("Hello, Carbyne!");
        System.out.println("String value is: " + stringBox.getValue());
    }
}
