/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author Nikhil Metrani
 */
public class ViewProfilePictureService {
    public byte[] processView(String userId) throws NotFoundException, SQLException {
        UserDao userDao = new UserDaoImpl();
        return userDao.getProfilePicture(userId);
    }
}
