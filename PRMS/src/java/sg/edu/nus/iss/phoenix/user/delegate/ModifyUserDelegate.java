package sg.edu.nus.iss.phoenix.user.delegate;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.service.ModifyUserService;

public class ModifyUserDelegate {

    public void processModify(User user) {
        ModifyUserService service = new ModifyUserService();
        service.processModify(user);
    }
}
