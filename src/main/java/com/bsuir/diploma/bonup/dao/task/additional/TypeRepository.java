package com.bsuir.diploma.bonup.dao.task.additional;

import com.bsuir.diploma.bonup.model.task.additional.Type;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByKey(String key);
}
