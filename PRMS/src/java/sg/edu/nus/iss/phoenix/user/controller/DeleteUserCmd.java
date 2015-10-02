/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.delegate.DeleteUserDelegate;

/**
 *
 * @author User
 */
@Action("deleteuser")
public class DeleteUserCmd implements Perform {

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = (String) req.getParameter("name");
            String id = (String) req.getParameter("id");
        //String action = req.getParameter("delete");
        if (name != null && id != null) {
            DeleteUserDelegate del = new DeleteUserDelegate();
            UserDaoImpl userDao = new UserDaoImpl();
            User deletedUser;
            try {
                deletedUser = userDao.getObject(id);
                deletedUser.setName(name);
                del.processDelete(deletedUser);
                req.setAttribute("deleted", "yes");
                //}
            } catch (NotFoundException ex) {
                Logger.getLogger(DeleteUserCmd.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DeleteUserCmd.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("successMessage", "User deleted successfully.");
            return "/pages/deleteuser.jsp";
        } else if (id != null) {
            UserDaoImpl userDao = new UserDaoImpl();
            User user = new User();
            try {
                user = userDao.getObject(id);
            } catch (Exception e) {
                req.setAttribute("errorMessage", "User not found");
                return "/pages/deleteuser.jsp";
            }
            if (user == null) {
                req.setAttribute("Empty", "yes");
                req.setAttribute("errorMessage", "User not found");
                return "/pages/deleteuser.jsp";
            } else if(user != null && !user.isActiveUserFlag()){
                req.setAttribute("errorMessage", "User is not active");
                return "/pages/deleteuser.jsp";
            }else{
                req.setAttribute("user", user);
                return "/pages/deleteuser_details.jsp";
            }
        } else {
            return "/pages/deleteuser.jsp";
        }
    }

}
