package com.highluck.repository;

import java.util.List;

import javax.persistence.OrderBy;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.highluck.domain.Account;
import com.highluck.domain.AccountLog;

@Repository
public interface AccountLogRepository extends CrudRepository<AccountLog, Long>{

	AccountLog save(AccountLog log);
	
	List<AccountLog> findByEmailOrderByNoDesc(String email, Pageable pageable);
}
