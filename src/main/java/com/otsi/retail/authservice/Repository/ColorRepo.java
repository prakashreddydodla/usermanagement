package com.otsi.retail.authservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.ColorEntity;
@Repository
public interface ColorRepo extends JpaRepository<ColorEntity, Long> {

	void save(List<ColorEntity> colorcode);

}
