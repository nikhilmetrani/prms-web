package sg.edu.nus.iss.phoenix.user.delegate;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.service.DeleteUserService;

public class DeleteUserDelegate {

    public void processDelete(User user) {
        DeleteUserService service = new DeleteUserService();
        service.processDelete(user);
    }
}
