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
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.entity.Profile;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.delegate.UpdateProfileDelegate;
import sg.edu.nus.iss.phoenix.user.service.UpdateProfileService;

/**
 * Command object that allows users to update their profile information
 * @author Nikhil Metrani
 */
@Action("updateprofile")
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, 
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class UpdateProfileCmd implements Perform {

    /**
     * Initiates process to update user profile. The user must have appropriate role to perform this action.
     * @param path Path
     * @param req Http Servlet Request
     * @param resp Httlp Servlet Response
     * @return If the profile was updated, path of page that allows users to view employment details is returned.<br>
     * If the user is not logged in, path of login page is returned.
     * @throws ServletException 
     * @see UpdateProfileService
     */
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        req.removeAttribute("errorMessage");
        User currentUser = (User)req.getSession().getAttribute("user");
        if (null != currentUser) {
            if (currentUser.hasRole("producer") ||
                    currentUser.hasRole("presenter")) {
                currentUser.setName(req.getParameter("name"));
                currentUser.setEmail(req.getParameter("email"));
                currentUser.setPhoneNumber(req.getParameter("phoneNo"));
                Profile profile = new Profile();
                profile.setSiteLink(req.getParameter("siteLink"));
                currentUser.setProfile(profile);
                UpdateProfileDelegate updateDeligate = new UpdateProfileDelegate();
                try {
                    updateDeligate.processUpdate(currentUser);
                    req.getSession().setAttribute("user", currentUser);
                    return "/nocturne/updatepicture";
//                    try {
//                        new UpdateProfilePictureCmd().perform(path, req, resp);
//                    } catch (IOException e) {
//                        req.setAttribute("errorMessage", "Failed to update profile Picture.");
//                    }
                } catch (NotFoundException | SQLException ex) {
                    req.setAttribute("errorMessage", "Failed to update profile.");
                }
            }
            else {
                req.setAttribute("errorMessage", "You do not have access to perform this operation. Please contact your administrator.");
            }
        } else {
            //Let's go back to login page since we cannot validate the user.
            return "/pages/login.jsp";
        }
        return "/nocturne/viewempdetails";
    }
}
