package com.learning.repository;

import com.learning.model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeRepository extends JpaRepository<Cafe, Long> {

    List<Cafe> findByAddressContainingIgnoreCaseOrNameContainingIgnoreCase(String address, String name);
}
