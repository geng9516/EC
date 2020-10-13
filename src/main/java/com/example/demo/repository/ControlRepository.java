package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Control;

@Repository
public interface ControlRepository extends JpaRepository<Control,Integer>{

	@Modifying
	@Query("delete from Control c where c.id=?1")
	void deleteControlbyControlName(Integer controlId);

	@Query("select c from Control c where c.id=?1")
	Control findControlByControlId(Integer controlId);

	@Modifying
	@Query("update Control c set c.statusbycontrol=?2,c.sex=?3,c.tel=?4 where c.id=?1")
	void updateCotrolByid(Integer controlId,String status,Character sex,String tel);

	@Query("select c.character_name from Control c where c.control_name=?1")
	String findByControlName(String loginId);
}
