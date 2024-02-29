package com.nirbhay.bmm.usermangement.service;

import com.nirbhay.bmm.model.usermanagement.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Nirbhay Rana
 */
public interface UserService {

    Page<AppUser> fetchAllUsers(Pageable pageable);

    AppUser saveUser(AppUser appUser) throws Exception;

    AppUser fetchUserById(long id) throws Exception;
}
