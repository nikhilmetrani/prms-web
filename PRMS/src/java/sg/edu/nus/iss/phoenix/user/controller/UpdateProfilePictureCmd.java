/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import javax.servlet.http.HttpServlet;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.delegate.UpdateProfilePictureDelegate;

/**
 *
 * @author Nikhil Metrani
 */
@Action("updatepicture")
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, 
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class UpdateProfilePictureCmd extends HttpServlet implements Perform {

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.removeAttribute("errorMessage");
        User currentUser = (User)req.getSession().getAttribute("user");
        if (null != currentUser) {
            if (currentUser.hasRole("producer") ||
                    currentUser.hasRole("presenter")) {
                InputStream inStream = null;
                String filePath = req.getParameter("profilePicture");
                try {
                Part filePart=req.getPart("profilePicture");
                if(filePart!=null){
                    inStream= new FileInputStream(filePath); //filePart.getInputStream();
                    UpdateProfilePictureDelegate updateDelegate = new UpdateProfilePictureDelegate();
                    try {
                    updateDelegate.processUpdate(currentUser.getId(), inStream);
                    } catch (NotFoundException | SQLException ex) {
                        req.setAttribute("errorMessage", "Failed to update profile picture");
                    }
                }
                }catch (IOException e) {
                    req.setAttribute("errorMessage", "Failed to update profile picture");
                }
            }
        } else {
            //Let's go back to login page since we cannot validate the user.
            return "/pages/login.jsp";
        }
        return "/nocturne/viewempdetails";
    }
    
}
