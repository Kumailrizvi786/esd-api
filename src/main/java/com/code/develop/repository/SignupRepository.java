package com.code.develop.repository;

import org.springframework.data.repository.CrudRepository;
import com.code.develop.data.SignupTable;

public interface SignupRepository extends CrudRepository<SignupTable, Long> {
	
	

}
