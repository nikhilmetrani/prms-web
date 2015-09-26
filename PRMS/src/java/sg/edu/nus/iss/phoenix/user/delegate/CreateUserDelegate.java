package sg.edu.nus.iss.phoenix.user.delegate;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.service.CreateUserService;

public class CreateUserDelegate {

    public User processCreate(User user) throws NotFoundException {
        CreateUserService service = new CreateUserService();
        User newUser = service.processCreate(user);
        return newUser;
    }
}
