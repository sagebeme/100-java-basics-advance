public class Exercise1 {
    public static void main(String[] args) {
        UserRegistry registry = new UserRegistry();
        registry.register("amina@example.com", "Amina");
        System.out.println("Users: " + registry.size());
        System.out.println("Found: " + registry.find("amina@example.com").getName());
    }
}
