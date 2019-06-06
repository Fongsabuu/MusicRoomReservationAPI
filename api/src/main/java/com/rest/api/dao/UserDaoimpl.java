package com.rest.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.rest.api.model.User;

@Repository
public class UserDaoimpl implements UserDao {
	
	@Autowired  
    private JdbcTemplate jdbcTemplate;  


	@Override
	public int addUser(final User user) {
		
		try {
			String sql = "INSERT INTO user (role, email, password, firstname, lastname, gender, birthday, address, tel, img_user, user_status ) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int insert = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, "m");
					ps.setString(2, user.getEmail());
					ps.setString(3, user.getPassword());
					ps.setString(4, user.getFirstname());
					ps.setString(5, user.getLastname());
					ps.setString(6, user.getGender());
					ps.setString(7, user.getBirthday());
					ps.setString(8, user.getAddress());
					ps.setString(9, user.getTel());
					ps.setString(10, user.getImg_user());
					ps.setInt(11, 1);
					return ps;
				}
			}, keyHolder);

			if (insert == 1) {
				//get id ของ user ที่เพิ่งสร้าง
				return keyHolder.getKey().intValue();
			} else {
				return 0;
			}
		} 
		catch (DuplicateKeyException e) {
			System.out.println(e);
			return 0;
		}
		catch (Exception e) {
			System.out.println(e);
			return 0;
		} 
		
	}

	@Override
	public int updateUser(User user) {
		try {
			String sql = "UPDATE user SET email = ?, password = ?, firstname = ?, lastname = ?, birthday = ?, address = ?, tel = ?, img_user = ? WHERE id = ?";
			return jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getFirstname(), user.getLastname(),
					user.getBirthday(), user.getAddress(), user.getTel(), user.getImg_user(), user.getId());
		} catch (DuplicateKeyException e) {
			System.out.println(e);
			return 0;
		}
	}

	@Override
	public List<User> getUserByEmail(User user) {
		
		String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
		List<User> users = new ArrayList<User>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,
				new Object[] { user.getEmail(), user.getPassword() });
		
		for (Map<String, Object> row : rows) {
			User userTemp = new User();
			userTemp.setId((int) row.get("id"));
			userTemp.setRole(((String) row.get("role")).charAt(0));
			userTemp.setEmail((String) row.get("email"));
			userTemp.setPassword((String) row.get("password"));
			userTemp.setFirstname((String) row.get("firstname"));
			userTemp.setLastname((String) row.get("lastname"));
			userTemp.setGender((String) row.get("gender"));
			userTemp.setBirthday((String) row.get("birthday"));
			userTemp.setAddress((String) row.get("address"));
			userTemp.setTel((String) row.get("tel"));
			userTemp.setImg_user((String) row.get("img_user"));
			userTemp.setUser_status(((String) row.get("user_status")).charAt(0));


//			sql = "SELECT id_book FROM favor WHERE id_user = ?";
//			List<Map<String, Object>> favor = jdbcTemplate.queryForList(sql, new Object[] { (int) row.get("id_user") });
//
//			userTemp.setFavor(favor);
			
			users.add(userTemp);
		}

		return users;
	}

	@Override
	public List<User> getAllUser() {
		
		String sql = "SELECT * FROM user";
		List<User> users = new ArrayList<User>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		for (Map<String, Object> row : rows) {
			User user = new User();
			user.setId((int) row.get("id"));
			System.out.println(row.get("role"));
			user.setRole(((String) row.get("role")).charAt(0));
			user.setEmail((String) row.get("email"));
			user.setPassword((String) row.get("password"));
			user.setFirstname((String) row.get("firstname"));
			user.setLastname((String) row.get("lastname"));
			user.setGender((String) row.get("gender"));
			user.setBirthday((String) row.get("birthday"));
			user.setAddress((String) row.get("address"));
			user.setTel((String) row.get("tel"));
			user.setImg_user((String) row.get("img_user"));
			user.setUser_status(((String) row.get("user_status")).charAt(0));
			users.add(user);
		}

		return users;
	}

	@Override
	public int deleteUser(int id) {
		String sql = "Update user SET user_status = 0 WHERE id = ? ";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int UpdateImgUser(int user_id, String img_name) {
		String sql = "UPDATE user SET img_user = ? WHERE id = ?";
		return jdbcTemplate.update(sql, img_name, user_id);
	}

	@Override
	public List<User> getUserById(int user_id) {
		String sql = "SELECT * FROM user WHERE id = ?";
		List<User> users = new ArrayList<User>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,
				new Object[] { user_id });
		
		for (Map<String, Object> row : rows) {
			User userTemp = new User();
			userTemp.setId((int) row.get("id"));
			userTemp.setRole(((String) row.get("role")).charAt(0));
			userTemp.setEmail((String) row.get("email"));
			userTemp.setPassword((String) row.get("password"));
			userTemp.setFirstname((String) row.get("firstname"));
			userTemp.setLastname((String) row.get("lastname"));
			userTemp.setGender((String) row.get("gender"));
			userTemp.setBirthday((String) row.get("birthday"));
			userTemp.setAddress((String) row.get("address"));
			userTemp.setTel((String) row.get("tel"));
			userTemp.setImg_user((String) row.get("img_user"));
			userTemp.setUser_status(((String) row.get("user_status")).charAt(0));


//			sql = "SELECT id_book FROM favor WHERE id_user = ?";
//			List<Map<String, Object>> favor = jdbcTemplate.queryForList(sql, new Object[] { (int) row.get("id_user") });
//
//			userTemp.setFavor(favor);

			users.add(userTemp);
		}

		return users;
	}

}
