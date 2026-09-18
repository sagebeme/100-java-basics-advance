public class Exercise2 {

    public static boolean isRequiredFieldFilled(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    public static String validate(String name, String email) {
        if (!isRequiredFieldFilled(name)) return "Name is required";
        if (!isValidEmail(email)) return "Enter a valid email";
        return "Valid";
    }

    public static void main(String[] args) {
        System.out.println(validate("Amina", "amina@example.com"));
        System.out.println(validate("", "amina@example.com"));
        System.out.println(validate("Amina", "not-an-email"));
    }
}
