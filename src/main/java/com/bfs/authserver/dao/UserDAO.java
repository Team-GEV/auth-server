package com.bfs.authserver.dao;


import com.bfs.authserver.pojo.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository("userDao")
public class UserDAO extends AbstractHibernateDAO<User> {
    public UserDAO() {
        setClazz(User.class);
    }

    public User getUserById(Integer id) {
        return findById(id);
    }

    public User getUserByName(String name) {

        String hql = "from User where username = ?1";

        Session s = getCurrentSession();
        Query query = s.createQuery(hql);
        query.setParameter(1, name);
        List<User> list = query.getResultList();
        if(list == null || list.size() == 0)
            return null;
        System.out.println("Size is " + list.size());
        return list.get(0);
    }

    public User validateUser(String name, String pass) {

        User res = null;
        res = getUserByName(name);
        if(res != null){
            if(res.getPassword().equals(pass)) {
                return res;
            }
            else
                return null;
        }
        return res;
    }


    public void createUser(User user){

        getCurrentSession().save("User", user);

    }




}
