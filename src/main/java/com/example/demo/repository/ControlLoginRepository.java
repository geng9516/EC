package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ControlLogin;

@Repository
public interface ControlLoginRepository extends JpaRepository<ControlLogin,Integer> {

	@Modifying
	@Query("delete from ControlLogin c where c.id=?1")
	void deleteControlLoginByControlId(Integer controlName);

	@Query("select c from ControlLogin c where c.id=?1")
	ControlLogin findControlLoginByControlName(Integer name);

	@Modifying
	@Query("update ControlLogin c set c.pass=?2 where c.id=?1")
	void updateCotrolByid(Integer controlLoginId,String password);
}
