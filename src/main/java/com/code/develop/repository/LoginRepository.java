package com.code.develop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.code.develop.data.LoginTable;



public interface LoginRepository extends CrudRepository<LoginTable, Long> {
	
	@Query("Select id FROM LoginTable a WHERE a.email = ?1 and a.password=?2")
	public Long findByEmailAndPassword(String email,String password);

	
	@Query("Select * FROM LoginTable  WHERE email = ?1 and password=?2")
	public LoginTable findByEmailAndOldPassword(String email, String oldPassword);
	
	
	@Query("Select password FROM LoginTable a WHERE a.email = ?1")
	public String findPasswordByEmail(String email);
//	@Query("Insert into LoginTable a (email, password) values (a.email = ?1 and a.password = ?2")
//	public boolean saveEmailandPassword(String email, String password);
	
	
	
}
