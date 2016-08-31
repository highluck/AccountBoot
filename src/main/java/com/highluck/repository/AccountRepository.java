package com.highluck.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.highluck.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
	
	@Override
	Account save(Account account);
	
	Account findByEmail(String email);
	

}
