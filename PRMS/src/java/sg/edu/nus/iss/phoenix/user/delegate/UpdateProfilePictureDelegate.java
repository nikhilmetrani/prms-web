/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.delegate;

import java.io.InputStream;
import java.sql.SQLException;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.service.UpdateProfilePictureService;

/**
 *
 * @author nick
 */
public class UpdateProfilePictureDelegate {
    
    public void processUpdate(String userId, InputStream profilePicture)  throws NotFoundException, SQLException {
        UpdateProfilePictureService service = new UpdateProfilePictureService();
        service.processUpdate(userId, profilePicture);
    }
}
