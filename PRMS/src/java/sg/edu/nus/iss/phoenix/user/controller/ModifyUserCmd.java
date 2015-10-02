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
import sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import static sg.edu.nus.iss.phoenix.core.controller.FCUtilities.validatePassword;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.delegate.ModifyUserDelegate;

/**
 *
 * @author User
 */
@Action("modifyuser")
public class ModifyUserCmd implements Perform {

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = (String) req.getParameter("name");
        String id = (String) req.getParameter("id");
        if (name != null && id != null) {
            ModifyUserDelegate del = new ModifyUserDelegate();
            AuthenticateDelegate adel = new AuthenticateDelegate();
            UserDaoImpl userDao = new UserDaoImpl();
            User updatedUser;
            try {
                updatedUser = userDao.getObject(id);
                updatedUser.setName(name);
                updatedUser.setPassword(req.getParameter("password"));

                ArrayList<Role> roleList = new ArrayList<Role>();
                String[] roles = req.getParameterValues("role");

                if (roles != null && roles.length > 0) {
                    for (int i = 0; i < roles.length; i++) {
                        Role r = adel.findRole(roles[i].toString());
                        if (r != null) {
                            roleList.add(r);
                            updatedUser.setRoles(roleList);
                        }
                    }
                }
                ArrayList<String> errorMessageList = new ArrayList<String>();
                errorMessageList = validateModifyUser(updatedUser);

                if (errorMessageList.isEmpty()) {
                    del.processModify(updatedUser);
                    req.setAttribute("updated", "yes");
                } else {
                    req.setAttribute("errorMessageList", errorMessageList);
                    req.setAttribute("user", updatedUser);
                    return "/pages/modifyuser_details.jsp";
                }

                //}
            } catch (NotFoundException ex) {
                Logger.getLogger(ModifyUserCmd.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ModifyUserCmd.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("successMessage", "User modified successfully.");
            return "/pages/modifyuser.jsp";
        } else if (id != null) {
            UserDaoImpl userDao = new UserDaoImpl();
            User user = null;
            try {
                user = userDao.getObject(id);
            } catch (Exception e) {
                req.setAttribute("errorMessage", "User not found");
                return "/pages/modifyuser.jsp";
            }
            if (user == null) {
                req.setAttribute("Empty", "yes");
                req.setAttribute("errorMessage", "User not found");
                return "/pages/modifyuser.jsp";
            } else if (user != null && !user.isActiveUserFlag()) {
                req.setAttribute("errorMessage", "User is not active");
                return "/pages/modifyuser.jsp";
            } else {
                req.setAttribute("user", user);
                return "/pages/modifyuser_details.jsp";
            }
        } else {
            return "/pages/modifyuser.jsp";

        }
    }

    private ArrayList<String> validateModifyUser(User user) {
        String userName = user.getName();
        String password = user.getPassword();
        String errorMessage = null;
        String blankValue = "";
        ArrayList<String> errorMessages = new ArrayList<String>();

//        if (roles == null || roles.isEmpty()) {
//            //req.setAttribute("errorMessage", "Please enter role details");
//            //errorMessage += "Please enter role details"+newLine;
//            errorMessages.add("Please enter role details");
//        }
        if (userName.equals(blankValue)) {
            errorMessages.add("Please enter User name");
        }
        if (password.equals(blankValue)) {
            errorMessages.add("Please enter password");
        } else {
            errorMessage = validatePassword(password);
            if (errorMessage != null && !errorMessage.equals(blankValue)) {
                errorMessages.add(errorMessage);
            }
        }

        return errorMessages;
    }
}
