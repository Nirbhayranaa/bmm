package com.nirbhay.bmm.usermangement.repository;

import com.nirbhay.bmm.model.usermanagement.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
}
