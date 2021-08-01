package com.sample.dal;

/**
 * Created by OmiD.HaghighatgoO on 8/21/2019.
 */
public interface UserDao {

    public Boolean saveUser(User user) ;
    public User findByName(String name) ;


}
