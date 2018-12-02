package com.nit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nit.bo.UserBO;
import com.nit.exception.UserDataException;

@Repository
public class UserRepositoryImpl implements UserRepository {
	private static final String SAVE_USER_QUERY = "INSERT INTO USER(FST_NM,LST_NM,FITBIT_USER_ID,ACCESS_TOKEN,REFRESH_TOKEN,MBR_ID)VALUES(?,?,?,?,?,?)";
	private static final String RETRIEVE_TOKEN_QUERY = "SELECT FST_NM,LST_NM,ACCESS_TOKEN,REFRESH_TOKEN,MBR_ID FROM USER WHERE FITBIT_USER_ID=?";
	private static final String UPDATE_USER_QUERY = "UPDATE USER SET ACCESS_TOKEN=?,REFRESH_TOKEN=? WHERE FITBIT_USER_ID=?";

	@Autowired
	private JdbcTemplate jt;

	@Override
	public int saveUser(UserBO bo) {
		int result = 0;
		try {
			result = jt.update(SAVE_USER_QUERY, bo.getFirstName(), bo.getLastName(), bo.getUserId(),
					bo.getAccessToken(), bo.getRefershToken(), bo.getMemberId());

		} catch (DataAccessException dae) {
			throw new UserDataException(dae.getMessage());
		} catch (Exception e) {
			throw new UserDataException(e.getMessage());
		}
		return result;
	}

	@Override
	public UserBO retrieveToken(String userId) {
		UserBO userBO = null;
		try {
			userBO = jt.queryForObject(RETRIEVE_TOKEN_QUERY, (rs, index) -> {
				UserBO bo = new UserBO();

				bo.setUserId(userId);
				bo.setFirstName(rs.getString(1));
				bo.setLastName(rs.getString(2));
				bo.setAccessToken(rs.getString(3));
				bo.setRefershToken(rs.getString(4));
				bo.setMemberId(rs.getString(5));

				return bo;
			}, userId);

		} catch (DataAccessException dae) {
			throw new UserDataException(dae.getMessage());
		} catch (Exception e) {
			throw new UserDataException(e.getMessage());
		}

		return userBO;
	}

	@Override
	public int updateUser(UserBO bo) {
		int result = 0;
		try {
			result = jt.update(UPDATE_USER_QUERY, bo.getAccessToken(), bo.getRefershToken(), bo.getUserId());
		} catch (DataAccessException dae) {
			throw new UserDataException(dae.getMessage());
		} catch (Exception e) {
			throw new UserDataException(e.getMessage());
		}

		return result;
	}

}
