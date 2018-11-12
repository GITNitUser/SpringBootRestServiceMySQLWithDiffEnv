package com.nit.dao;

import com.nit.bo.UserBO;

public interface UserRepository {
	public int saveUser(UserBO bo);
	public UserBO retrieveToken(String userId);
	public int updateUser(UserBO bo);
}
