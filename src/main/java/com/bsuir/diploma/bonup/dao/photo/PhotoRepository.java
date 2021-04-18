package com.bsuir.diploma.bonup.dao.photo;

import com.bsuir.diploma.bonup.model.photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
