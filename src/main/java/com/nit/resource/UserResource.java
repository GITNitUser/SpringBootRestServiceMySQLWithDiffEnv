package com.nit.resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nit.dto.UserDTO;
import com.nit.model.UserVO;
import com.nit.service.UserService;

@RestController
public class UserResource {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createUser(@RequestBody UserVO vo) {
		String result=null;
		UserDTO dto=null;
		
		//create the UserDTO Object
		dto=new UserDTO();
		//copy the data from UserVO to UserDTO
		BeanUtils.copyProperties(vo, dto);
		
		result=userService.createUser(dto);
		return ResponseEntity.ok().body(result);
	}
	@RequestMapping(value="/user/{userId}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserVO> getUserDetails(@PathVariable("userId") String userId) {
		UserDTO userDTO=null;
		UserVO userVO=null;
		
		//create the UserVO object
		userVO=new UserVO();
		
		userDTO=userService.getToken(userId);
		BeanUtils.copyProperties(userDTO, userVO);
		
		return ResponseEntity.ok().body(userVO);
	}
	
	@RequestMapping(value="/user/update", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUser(@RequestBody UserVO vo) {
		String result=null;
		UserDTO dto=null;
		
		//create the UserDTO Object
		dto=new UserDTO();
		//copy the data from UserVO to UserDTO
		BeanUtils.copyProperties(vo, dto);
		
		result=userService.modifyUser(dto);
		return ResponseEntity.ok().body(result);
	}
}
