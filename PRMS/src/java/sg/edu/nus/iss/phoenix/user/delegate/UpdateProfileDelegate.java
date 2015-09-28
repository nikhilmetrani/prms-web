/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.delegate;

import java.sql.SQLException;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.service.UpdateProfileService;

/**
 *
 * @author Nikhil Metrani
 */
public class UpdateProfileDelegate {
    public void processUpdate(User user)  throws NotFoundException, SQLException {
        UpdateProfileService service = new UpdateProfileService();
        service.processUpdate(user);
    }
}
