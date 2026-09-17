public class ImprovedPasswordManagerLogic {

    /**
     * @return true if the search term appears in the website or the username, ignoring case.
     */
    public static boolean matches(String website, String username, String searchTerm) {
        String term = searchTerm.toLowerCase();
        return website.toLowerCase().contains(term) || username.toLowerCase().contains(term);
    }
}
