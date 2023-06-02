package com.Web.WebSteward.repositores;

import com.Web.WebSteward.models.TOiR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TOiRRepository extends JpaRepository<TOiR, Long> {
}
