package pages;

public class Cat extends Pet {

    // static POLYMORPHISM (same method, different arguments)
    public Cat() {
        name = "nameless one";
    }
    // static POLYMORPHISM (same method, different arguments)
    public Cat(String value) {
        name = value;
    }

    public void speak() {
        System.out.println(name + " is meowing!");
    }
}