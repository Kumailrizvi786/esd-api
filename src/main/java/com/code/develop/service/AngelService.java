package com.code.develop.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.develop.data.AngelTable;
import com.code.develop.model.AngelUser;
import com.code.develop.repository.AngelRepository;


@Service
public class AngelService {

	@Autowired
	private AngelRepository repository;
	
	@Autowired
	private AngelUser angelUser;	
	
	
	public String deleteAngelUserById(Long ahId) {
		try {
		repository.deleteById(ahId);
		} catch(Exception e) {
			return " Invalid Id " + ahId;
		}
		return " Record Deleted " + ahId;
		
	}
	
	
	//Does not work properly
	public AngelUser updateAngelUserById(AngelUser angelUser) {
		return  saveAngelUserDetail(angelUser);
	}
	
	
	public  AngelUser saveAngelUserDetail( AngelUser angelUser) {
		AngelTable angelTable = new AngelTable();
		//before save we need the to transfer the values from angelUser pojo  to angleTable pojo
		
		angelTable.setAhAddress(angelUser.getAhAddress());
		angelTable.setAhContactNumber(angelUser.getAhContactNumber());
		angelTable.setAhEmail(angelUser.getAhEmail());
		angelTable.setAhName(angelUser.getAhName());
		angelTable.setAhGSTNumber_id(angelUser.getAhGSTNumber_id());
		angelTable.setAhPincode(angelUser.getAhPincode());
		angelTable.setAhType(angelUser.getAhType());
		angelTable.setAhProfession(angelUser.getAhProfession());
		angelTable.setAhQualification(angelUser.getAhQualification());
		angelTable.setAhContactPersonName(angelUser.getAhContactNumber());
		angelTable.setAhStatus(angelUser.getAhStatus());
		angelTable.setAhInterestId(angelUser.getAhInterestId());
		angelTable.setAhCreated_on(angelUser.getAhCreated_on());
		angelTable.setAhLastUpdated_on(angelUser.getAhLastUpdated_on());
		//angelTable.setData(angelUser.getData());
		angelTable.setFileName(angelUser.getFileName());
		angelTable.setFileType(angelUser.getFileType());

		AngelTable ref =  repository.save(angelTable);
		return angelUser;
	}
	
	
	public AngelUser findById(long id) {
		AngelTable doc = repository.findById(id).get();
		//angelUser.setData(doc.getData());
		return angelUser;
	}
	
	public Iterable<AngelUser> getAllAngelUser() {
		Iterable<AngelTable> aTable = repository.findAll();
		
		//convert angeltable object into angelUser object;
		
		List  <AngelUser> lstAngel = new ArrayList<>();
		AngelUser ref;
		AngelTable aTab;
	    Iterator<AngelTable> it =	 aTable.iterator();
	    
	  while(it.hasNext()) {
		  aTab = (AngelTable) it.next();
		  ref = new AngelUser();
		  ref.setAhAddress(aTab.getAhAddress());
		  ref.setAhContactNumber(aTab.getAhContactNumber());
		  ref.setAhEmail(aTab.getAhEmail());
		  ref.setAhId(aTab.getAhId());
		  ref.setAhName(aTab.getAhName());
		  ref.setAhGSTNumber_id(aTab.getAhGSTNumber_id());
		  ref.setAhContactPersonName(aTab.getAhContactPersonName());
		  ref.setAhPincode(aTab.getAhPincode());
		  ref.setAhStatus(aTab.getAhStatus());
		  ref.setAhProfession(aTab.getAhProfession());
		  ref.setAhQualification(aTab.getAhQualification());
		  ref.setAhType(aTab.getAhType());
		  ref.setAhInterestId(aTab.getAhInterestId());
		  ref.setFileName(aTab.getFileName());
		  ref.setFileType(aTab.getFileType());
		  ref.setAhCreated_on(aTab.getAhCreated_on());
		  ref.setAhLastUpdated_on(aTab.getAhLastUpdated_on());
		 // ref.setData(aTab.getData());
		  
		  lstAngel.add(ref);
	  }
		return lstAngel;
	}
	
	
}
