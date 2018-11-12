package com.nit.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.bo.UserBO;
import com.nit.dao.UserRepository;
import com.nit.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public String createUser(UserDTO dto) {
		String msg = null;
		UserBO bo = null;
		int result = 0;

		// create UserBO object
		bo = new UserBO();
		// copy data from UserDTO to UserBO
		BeanUtils.copyProperties(dto, bo);

		result = userRepo.saveUser(bo);

		if (result != 0)
			msg = "User created Successfully";
		else
			msg = "User is not Created";

		return msg;
	}

	@Override
	public UserDTO getToken(String userId) {
		UserBO userBO = null;
		UserDTO userDTO = null;

		// create the UserDTO object
		userDTO = new UserDTO();

		userBO = userRepo.retrieveToken(userId);
		// copy the data from UserBO to UserDTO
		BeanUtils.copyProperties(userBO, userDTO);

		return userDTO;
	}

	@Override
	public String modifyUser(UserDTO dto) {
		String msg = null;
		UserBO bo = null;
		int result = 0;

		// create UserBO object
		bo = new UserBO();
		// copy data from UserDTO to UserBO
		BeanUtils.copyProperties(dto, bo);

		result = userRepo.updateUser(bo);

		if (result != 0)
			msg = "User updated Successfully";
		else
			msg = "User is not updated";

		return msg;
	}

}
