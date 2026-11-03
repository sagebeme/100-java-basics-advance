package com.learning.controller;

import com.learning.exception.DuplicatePhoneException;
import com.learning.exception.MemberNotFoundException;
import com.learning.model.Member;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final Map<Long, Member> members = new ConcurrentSkipListMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    // GET /api/members, or GET /api/members?name=wanj for names containing "wanj", ignoring case.
    @GetMapping
    public List<Member> list(@RequestParam(required = false) String name) {
        return members.values().stream()
                .filter(m -> name == null || m.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @GetMapping("/{id}")
    public Member one(@PathVariable long id) {
        return find(id);
    }

    @PostMapping
    public ResponseEntity<Member> join(@Valid @RequestBody Member member) {
        refuseDuplicatePhone(member.getPhone(), null);
        member.setId(nextId.getAndIncrement());
        members.put(member.getId(), member);
        return ResponseEntity.created(URI.create("/api/members/" + member.getId())).body(member);
    }

    @PutMapping("/{id}")
    public Member update(@PathVariable long id, @Valid @RequestBody Member changes) {
        Member member = find(id);
        refuseDuplicatePhone(changes.getPhone(), id);
        member.setName(changes.getName());
        member.setPhone(changes.getPhone());
        member.setMonthlyShillings(changes.getMonthlyShillings());
        return member;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> leave(@PathVariable long id) {
        find(id);
        members.remove(id);
        return ResponseEntity.noContent().build();
    }

    private Member find(long id) {
        Member member = members.get(id);
        if (member == null) {
            throw new MemberNotFoundException(id);
        }
        return member;
    }

    // Another member (not the one with id `self`) already has this phone.
    private void refuseDuplicatePhone(String phone, Long self) {
        for (Member other : members.values()) {
            if (other.getPhone().equals(phone) && !other.getId().equals(self)) {
                throw new DuplicatePhoneException(phone);
            }
        }
    }
}
