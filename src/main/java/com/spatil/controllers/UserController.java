package com.spatil.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spatil.dao.UserDao;
import com.spatil.model.User;

@RestController
@RequestMapping("/rest/user")
public class UserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;

    @RequestMapping("/test")
    public String test() {
        log.info("Test");
        return "OK";
    }

    @RequestMapping("/get")  
    public User getUser(@RequestParam("id") long id) {
        log.info("Get user");
        return userDao.getUser(id);
    }

    @RequestMapping("/getByIds")
    public List<User> getUsers(@RequestParam("ids") long[] ids) {
        log.info("Get users");
        return userDao.getUsers(ids);
    }
    
    @RequestMapping("/save")
    public void saveUser(@RequestParam("id") long id, @RequestParam("name")String name, @RequestParam("email")String email) {
    	User user = new User(id, name, email);
    	userDao.saveUser(user);
    }
} 
