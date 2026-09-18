public class Exercise1 {
    public static void main(String[] args) {
        Animal[] animals = { new Dog("Rex"), new Cat("Whiskers"), new Bird("Tweety") };
        for (Animal animal : animals) {
            System.out.println(animal.getName() + " says " + animal.makeSound());
        }
    }
}
