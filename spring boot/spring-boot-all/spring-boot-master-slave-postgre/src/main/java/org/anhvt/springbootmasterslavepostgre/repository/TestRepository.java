package org.anhvt.springbootmasterslavepostgre.repository;

import org.anhvt.springbootmasterslavepostgre.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
