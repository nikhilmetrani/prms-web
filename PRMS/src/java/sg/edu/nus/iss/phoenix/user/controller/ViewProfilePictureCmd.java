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
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.delegate.ViewProfilePictureDelegate;

/**
 *
 * @author Nikhil Metrani
 */
@Action("viewProfileImage")
public class ViewProfilePictureCmd implements Perform {

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        req.removeAttribute("errorMessage");
        User currentUser = (User)req.getSession().getAttribute("user");
        if (null != currentUser) {
            if (currentUser.hasRole("producer") ||
                    currentUser.hasRole("presenter")) {
                ViewProfilePictureDelegate viewDelegate = new ViewProfilePictureDelegate();
                try {
                    byte[] photo = viewDelegate.processView(currentUser.getId());
                    if (null != photo)
                        resp.getOutputStream().write(photo);
                } catch (IOException | NotFoundException | SQLException ex) {
                    return "/img/defaultpi.png";
                }
            }
        }
        return "/img/defaultpi.png";
    }
    
}
