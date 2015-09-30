/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import sg.edu.nus.iss.phoenix.authenticate.entity.Profile;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.delegate.UpdateProfileDelegate;

/**
 *
 * @author Nikhil Metrani
 */
@Action("updateprofile")
//@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB 
//                 maxFileSize=1024*1024*50,          // 50 MB
//                 maxRequestSize=1024*1024*100)      // 100 MB
public class UpdateProfileCmd implements Perform {

    /**
     * Directory where uploaded files will be saved, its relative to
     * the web application directory.
     */
    private static final String UPLOAD_DIR = "uploads";
    
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
//                    try {
//                        uploadProdileImage(req, resp);
//                    } catch (IOException ex) {
//                        req.getSession().setAttribute("errorMessage", "Failed to upload profileimage.");
//                    }
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
    
    public void uploadProdileImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // gets absolute path of the web application
        String applicationPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
          
        // creates the save directory if it does not exists
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
         
        String fileName = null;
        //Get all the parts from request and write it to the file on server
        for (Part part : request.getParts()) {
            fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
        }
    }
    
    /**
     * Utility method to get file name from HTTP header content-disposition
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}
