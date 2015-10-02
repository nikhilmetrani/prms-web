/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import static sg.edu.nus.iss.phoenix.core.controller.FCUtilities.validatePassword;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.delegate.CreateUserDelegate;

/**
 *
 * @author deba
 */
@Action("adduser")
public class CreateUserCmd implements Perform {

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        CreateUserDelegate del = new CreateUserDelegate();
        AuthenticateDelegate adel = new AuthenticateDelegate();
        User user = new User();
        user.setId(req.getParameter("id"));
        user.setPassword(req.getParameter("password"));
        user.setName(req.getParameter("name"));
        ArrayList<Role> roleList = new ArrayList<Role>();
        String[] roles = req.getParameterValues("role");
        if (roles != null && roles.length > 0) {
            for (int i = 0; i < roles.length; i++) {
                Role r = adel.findRole(roles[i].toString());
                if (r != null) {
                    roleList.add(r);
                    user.setRoles(roleList);
                }
            }
        }
        ArrayList<String> errorMessageList = new ArrayList<String>();
        errorMessageList = validateNewUser(user);

        User newUser = null;
        try {
            if (errorMessageList.isEmpty()) {
                newUser = del.processCreate(user);
            } else {
                req.setAttribute("errorMessageList", errorMessageList);
                return "/pages/createuser.jsp";
            }
            //req.setAttribute("user", newUser);
        } catch (NotFoundException ex) {
            Logger.getLogger(CreateUserCmd.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (null != newUser) {
            //req.getSession().setAttribute("user", user);
            req.setAttribute("successMessage", "User created successfully");
            //    return "/pages/createuser.jsp";
        } else {
            req.setAttribute("errorMessage", "Error!!!User Alredy Exists");
            //      return "/pages/error.jsp";
        }

        return "/pages/createuser.jsp";
    }

    private ArrayList<String> validateNewUser(User user) {
        String userId = user.getId();
        String userName = user.getName();
        String password = user.getPassword();
        ArrayList<Role> roles = user.getRoles();
        String errorMessage = null;
        String blankValue = "";
        ArrayList<String> errorMessages = new ArrayList<String>();
        if (roles == null || roles.isEmpty()) {
            errorMessages.add("Please enter role details");
        }
        if (userId.equals(blankValue)) {
            errorMessages.add("Please enter Id");
        }
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
