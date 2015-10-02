/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 * Command object that allows users to view their profile. <br>
 * Users must have producer or presenter role to be able to perform this action.
 * @author Nikhil Metrani
 */
@Action("viewempdetails")
public class ViewEmploymentDetailsCmd implements Perform{

    /**
     * Allows users with producer or presenter role to view their profile information.
     * @param path Path
     * @param req Http Servlet Request
     * @param resp Httlp Servlet Response
     * @return Path of page that allows users to view employment details.<br>
     * If the user is not logged in, path of login page is returned.
     * @throws ServletException 
     */
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        req.removeAttribute("errorMessage");
        User currentUser = (User)req.getSession().getAttribute("user");
        if (null != currentUser) {
            if (currentUser.hasRole("producer") ||
                    currentUser.hasRole("presenter")) {
                req.getSession().setAttribute("name", currentUser.getName());
                req.getSession().setAttribute("email", currentUser.getEmail());
                req.getSession().setAttribute("phoneNo", currentUser.getPhoneNumber());
                req.getSession().setAttribute("siteLink", currentUser.getProfile().getSiteLink());
                req.getSession().setAttribute("profileImage", currentUser.getProfile().getImage());
            }
            else {
                req.setAttribute("errorMessage", "You do not have the appropriate access to view this page. Please contact your administrator.");
            }
        }
        else {
            //Let's go back to login page since we cannot validate the user.
            return "/pages/login.jsp";
        }
        return "/pages/maintainuser/viewempdetails.jsp";
    }
    
}
