/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.delegate;

import java.sql.SQLException;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.service.ViewProfilePictureService;

/**
 *
 * @author Nikhil Metrani
 */
public class ViewProfilePictureDelegate {
    public byte[] processView(String userId) throws NotFoundException, SQLException {
        ViewProfilePictureService viewService = new ViewProfilePictureService();
        return viewService.processView(userId);
    }
}
