package com.example.testkb.repository;

import com.example.testkb.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
