/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author nick
 */
@Action("viewempdetails")
public class ViewEmploymentDetailsCmd implements Perform{

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getSession().setAttribute("errorMessage", "");
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
                req.getSession().setAttribute("errorMessage", "Current user is not a presenter or producer.");
            }
        }
        else {
            req.getSession().setAttribute("errorMessage", "Error finding employment details.");
        }
        return "/pages/maintainuser/viewempdetails.jsp";
    }
    
}
