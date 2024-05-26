package com.statsservice.stats.service.repository;
import com.statsservice.stats.service.model.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<Stat, Long> {
    List<Stat> findByUserId(Long userId);
}
