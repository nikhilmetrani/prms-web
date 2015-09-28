/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author Nikhil Metrani
 */
public class UpdateProfileService {
    public void processUpdate(User user)  throws NotFoundException, SQLException {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.save(user);
    }
}
