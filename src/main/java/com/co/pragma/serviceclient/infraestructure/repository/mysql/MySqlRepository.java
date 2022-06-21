package com.co.pragma.serviceclient.infraestructure.repository.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.pragma.serviceclient.infraestructure.repository.mysql.entity.ClientEntity;

@Repository
public interface MySqlRepository extends JpaRepository<ClientEntity, Long>{
	
	@Transactional(readOnly = true)
	List<ClientEntity> findByStateIsTrue();
	
	@Transactional(readOnly = true)
	ClientEntity findByTypeDocumentAndNumberDocumentAndStateIsTrue(String typeDocument, String numberDocument);
	
	
}
