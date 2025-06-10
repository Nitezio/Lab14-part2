package com.csc3402.security.form_login.service;

import com.csc3402.security.form_login.dto.UserDto;
import com.csc3402.security.form_login.model.User;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
}
