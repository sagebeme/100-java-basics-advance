package com.learning;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

// Already written. Three members, each at /members/{id}.
@Controller
public class MemberController {

    private final Map<Integer, Member> members = Map.of(
            1, new Member(1, "Grace Wambui", "CHAIR", 84000, 30, List.of(
                    new Entry("2026-09-30", "Monthly saving", 3000),
                    new Entry("2026-10-12", "Loan repayment", -4500),
                    new Entry("2026-10-31", "Monthly saving", 3000))),
            2, new Member(2, "Peter Ochieng", "TREASURER", 12000, 4, List.of(
                    new Entry("2026-10-31", "Monthly saving", 3000))),
            3, new Member(3, "Faith Njeri", "MEMBER", 0, 0, List.of()));

    @GetMapping("/members/{id}")
    public String member(@PathVariable int id, Model model) {
        Member member = members.get(id);
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("member", member);
        return "member";
    }
}
