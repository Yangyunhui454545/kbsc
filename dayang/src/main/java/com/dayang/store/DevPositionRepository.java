package com.dayang.store;

import com.dayang.domain.Position;
import com.dayang.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevPositionRepository extends JpaRepository<Position, Long> {
    Position findByStore(Store store);
}
