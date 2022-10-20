package TestCases;

public record Person(String name, int age) {

    public boolean equals(Object o) {
        return o.getClass() == Person.class && ((Person) o).age == age() && ((Person) o).name.equals(name);
    }
}
