package com.Web.WebSteward.repositores;

import com.Web.WebSteward.models.Res;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResRepository extends JpaRepository<Res, Integer> {
}
