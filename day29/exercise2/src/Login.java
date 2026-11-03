// One saved login. Already written.
public class Login {

    private final String site;
    private final String username;
    private final String password;

    public Login(String site, String username, String password) {
        this.site = site;
        this.username = username;
        this.password = password;
    }

    public String getSite() {
        return site;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Login)) {
            return false;
        }
        Login that = (Login) other;
        return site.equals(that.site) && username.equals(that.username) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(site, username, password);
    }

    @Override
    public String toString() {
        return site + " (" + username + ")";
    }
}
