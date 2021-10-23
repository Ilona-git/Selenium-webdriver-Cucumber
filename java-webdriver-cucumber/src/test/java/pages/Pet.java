package pages;

public   class Pet {
    // fields
    // set private access-modifier, using ENCAPSULATION
    protected String name; //protected means only cat and dog cn use it

    public Pet() {
        name = "nameless one";
    }

    public void setName(String value) {
        name = value;
    }

    public String getName() {
        return name;
    }

    // methods
    public void walk() {
        System.out.println(name + " is walking!");
    }
    public void eat(String what) {
        System.out.println(name + " is eating " + what);
    }

    //here abstract because it implemented in dog and cat but not here in pet.
    // If we have abstract method in pets-class should be also abstract
    public void speak() {

    }

    public void move() {

    }
}