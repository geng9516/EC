package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Control;
import com.example.demo.repository.ControlRepository;


@Service
@Transactional
public class ControlService {

	@Autowired
	ControlRepository controlRepository;

	public List<Control> findAllControls(){

		return controlRepository.findAll();
	}
}
