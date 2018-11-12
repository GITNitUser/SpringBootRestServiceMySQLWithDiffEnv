package com.nit.service;

import com.nit.dto.UserDTO;

public interface UserService {
    public String createUser(UserDTO dto);
    public UserDTO getToken(String userId);
    public String modifyUser(UserDTO dto);
}
