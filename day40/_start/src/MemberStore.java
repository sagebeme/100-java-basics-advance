import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

// The club's members, kept in a JSON file and looked up by email (each email joins once).
public class MemberStore {

    private final Path file;
    private final Map<String, Member> members = new LinkedHashMap<>();

    public MemberStore(Path file) throws IOException {
        this.file = file;
        if (Files.exists(file)) {
            JSONArray all = new JSONArray(Files.readString(file));
            for (int i = 0; i < all.length(); i++) {
                JSONObject m = all.getJSONObject(i);
                List<WishItem> wishes = new ArrayList<>();
                JSONArray w = m.getJSONArray("wishes");
                for (int j = 0; j < w.length(); j++) {
                    JSONObject x = w.getJSONObject(j);
                    wishes.add(new WishItem(x.getString("city"), x.getString("code"), x.getInt("maxPriceKes")));
                }
                Member member = new Member(m.getString("name"), m.getString("email"), wishes);
                members.put(member.email(), member);
            }
        }
    }

    // False if that email is already a member.
    public boolean join(Member member) throws IOException {
        if (members.containsKey(member.email())) return false;
        members.put(member.email(), member);
        save();
        return true;
    }

    public boolean leave(String email) throws IOException {
        boolean removed = members.remove(email.trim().toLowerCase()) != null;
        if (removed) save();
        return removed;
    }

    public List<Member> all() {
        return List.copyOf(members.values());
    }

    private void save() throws IOException {
        JSONArray all = new JSONArray();
        for (Member m : members.values()) {
            JSONArray wishes = new JSONArray();
            for (WishItem w : m.wishes()) {
                wishes.put(new JSONObject().put("city", w.city()).put("code", w.code()).put("maxPriceKes", w.maxPriceKes()));
            }
            all.put(new JSONObject().put("name", m.name()).put("email", m.email()).put("wishes", wishes));
        }
        Files.writeString(file, all.toString(2));
    }
}
