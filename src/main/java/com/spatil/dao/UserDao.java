package com.spatil.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.spatil.model.User;

@Repository("userDao")
public class UserDao {
    protected final Logger log = LoggerFactory.getLogger(getClass());
  
    @Autowired
    protected JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource dataSource) {
    	jdbc = new JdbcTemplate(dataSource);
    }
    
    public User getUser(long id) {
        return jdbc.queryForObject("SELECT * FROM user WHERE id=?", userMapper, id);
    }

    public List<User> getUsers(long[] ids) {
        String inIds = StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(ids));
        log.info("IDs:"+inIds);
        return jdbc.query("SELECT * FROM user WHERE id IN (" + inIds + ")", userMapper);
    }
    
    public void saveUser(User user) {
    	String query = "insert into user values(?,?,?)";
    	jdbc.update(query, new Object[] {user.getId(),user.getEmail(),user.getName()});
    	log.info("User is saved."+user);
    }

    private static final RowMapper<User> userMapper = new RowMapper<User>() {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getLong("id"), rs.getString("name"),rs.getString("email"));
            //user.alias = rs.getString("alias");
            return user;
        }
    };

} 
