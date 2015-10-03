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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import static sg.edu.nus.iss.phoenix.core.controller.FCUtilities.validatePassword;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.delegate.ResetPasswordDelegate;

/**
 * Command Object that handles the Reset Password Command
 *
 * @author debasish
 */
@Action("resetpassword")
public class ResetPasswordCmd implements Perform {

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = (String) req.getParameter("name");
        String id = (String) req.getParameter("id");
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

                ArrayList<String> errorMessageList = new ArrayList<String>();
                errorMessageList = validateResetPassword(oldPassword, newPassword);

                if (errorMessageList.isEmpty()) {
                    del.processModify(updatedUser);
                    req.setAttribute("updated", "yes");
                } else {
                    req.setAttribute("errorMessageList", errorMessageList);
                    updatedUser.setPassword(oldPassword);
                    req.setAttribute("user", updatedUser);
                    return "/pages/resetpassword_details.jsp";
                }

            } catch (NotFoundException ex) {
                Logger.getLogger(ResetPasswordCmd.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ResetPasswordCmd.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("successMessage", "Password reset successfully.");
            return "/pages/resetpassword.jsp";
        } else if (id != null) {
            UserDaoImpl userDao = new UserDaoImpl();
            User user = null;
            try {
                user = userDao.getObject(id);
            } catch (Exception e) {
                req.setAttribute("errorMessage", "User not found");
                return "/pages/resetpassword.jsp";
            }
            if (user == null) {
                req.setAttribute("Empty", "yes");
                req.setAttribute("errorMessage", "User not found");
                return "/pages/resetpassword.jsp";
            } else if (user != null && !user.isActiveUserFlag()) {
                req.setAttribute("errorMessage", "User is not active");
                return "/pages/resetpassword.jsp";
            } else {
                req.setAttribute("user", user);
                return "/pages/resetpassword_details.jsp";
            }
        } else {
            return "/pages/resetpassword.jsp";

        }
    }

    
    /**
     * This API will validate the old and new password and set all the error messages
     * and return to the caller.
     *
     * @param Old password,New Password
     * @return ArrayList<ErrorMessage>
     *
     * @debasish
     */
    private ArrayList<String> validateResetPassword(String oldPassword, String newPassword) {
        String errorMessage = null;
        String blankValue = "";
        ArrayList<String> errorMessages = new ArrayList<String>();
        if (newPassword.equals(blankValue)) {
            errorMessages.add("Please enter new password");
        } else {
            if (oldPassword != null && newPassword != null) {
                if (oldPassword.equals(newPassword)) {
                    errorMessages.add("Old password and new password cannot be same");
                } else {
                    errorMessage = validatePassword(newPassword);
                    if (errorMessage != null && !errorMessage.equals(blankValue)) {
                        errorMessages.add(errorMessage);
                    }
                }
            }
        }
        return errorMessages;
    }
}
