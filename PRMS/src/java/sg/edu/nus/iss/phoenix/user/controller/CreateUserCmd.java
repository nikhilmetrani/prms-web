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
        String errorMessage = "";

       // String action = req.getParameter("action");

            ArrayList<Role> roleList = new ArrayList<Role>();
            String[] roles = req.getParameterValues("role");
            if (roles == null || roles.length == 0) {
                req.setAttribute("errorMessage", "Please enter role details");
            }else{
                for (int i = 0; i < roles.length; i++) {
                    Role r = adel.findRole(roles[i].toString());
                    if (r != null) {
                        roleList.add(r);
                        user.setRoles(roleList);
                    }
                }
            }
//        Role newRole = new Role();
//        newRole.setRole(req.getParameter("role"));
//        newRole.setAccessPrivilege(req.getParameter("access"));
//        roles.add(0, newRole);
//        user.setRoles(roles);
//        user.setRole(req.getParameter("role"));
//        
        User newUser = null;
        try {
            newUser = del.processCreate(user);
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
}
