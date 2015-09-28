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
import sg.edu.nus.iss.phoenix.authenticate.entity.Profile;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.delegate.UpdateProfileDelegate;

/**
 *
 * @author Nikhil Metrani
 */
@Action("updateprofile")
public class UpdateProfileCmd implements Perform {

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getSession().setAttribute("errorMessage", "");
        User currentUser = (User)req.getSession().getAttribute("user");
        if (null != currentUser) {
            if (currentUser.hasRole("producer") ||
                    currentUser.hasRole("presenter")) {
                currentUser.setName(req.getParameter("name"));
                currentUser.setEmail(req.getParameter("email"));
                currentUser.setPhoneNumber(req.getParameter("phoneNo"));
                Profile profile = new Profile();
                profile.setSiteLink(req.getParameter("siteLink"));
                profile.setImage(req.getParameter("profileImage"));
                currentUser.setProfile(profile);
                UpdateProfileDelegate upd = new UpdateProfileDelegate();
                try {
                    upd.processUpdate(currentUser);
                } catch (NotFoundException | SQLException ex) {
                    req.getSession().setAttribute("errorMessage", "Failed to update.");
                }
                req.getSession().setAttribute("user", currentUser);
            }
            else {
                req.getSession().setAttribute("errorMessage", "Current user is not a presenter or producer.");
            }
        }
        else {
            req.getSession().setAttribute("errorMessage", "Error finding employment details.");
        }
        return "/nocturne/viewempdetails";
    }
    
}
