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
import sg.edu.nus.iss.phoenix.user.delegate.ModifyUserDelegate;
import sg.edu.nus.iss.phoenix.user.delegate.ResetPasswordDelegate;

/**
 *
 * @author User
 */
@Action("resetpassword")
public class ResetPasswordCmd implements Perform {

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = (String) req.getParameter("name");
        String id = (String) req.getParameter("id");
        //String action = req.getParameter("delete");
        if (name != null && id != null) {
            ResetPasswordDelegate del = new ResetPasswordDelegate();
            UserDaoImpl userDao = new UserDaoImpl();
            User updatedUser;
            String oldPassword = req.getParameter("old_password");
            String newPassword = req.getParameter("new_password");
            try {
                updatedUser = userDao.getObject(id);
                updatedUser.setName(name);
                updatedUser.setPassword(newPassword);
                del.processModify(updatedUser);
                req.setAttribute("updated", "yes");
            } catch (NotFoundException ex) {
                Logger.getLogger(ResetPasswordCmd.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ResetPasswordCmd.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("successMessage", "Password reset successfully.");
            return "/pages/resetpassword.jsp";
        } else if (id != null) {
            UserDaoImpl userDao = new UserDaoImpl();
            User user = new User();
            try {
                user = userDao.getObject(id);
            } catch (Exception e) {
                req.setAttribute("errorMessage", "User not found");
                return "/pages/resetpassword_empty.jsp";
            }
            if (user == null) {
                req.setAttribute("Empty", "yes");
                req.setAttribute("errorMessage", "User is not active");
                return "/pages/resetpassword_empty.jsp";
            }else if (user != null && !user.isActiveUserFlag()) {
                req.setAttribute("errorMessage", "User is not active");
                return "/pages/resetpassword_empty.jsp";
            } else {
                req.setAttribute("user", user);
                return "/pages/resetpassword_details.jsp";
            }
        } else {
            return "/pages/resetpassword.jsp";

        }
    }

}
