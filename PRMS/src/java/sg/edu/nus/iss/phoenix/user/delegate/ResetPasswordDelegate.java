package sg.edu.nus.iss.phoenix.user.delegate;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.service.ModifyUserService;
import sg.edu.nus.iss.phoenix.user.service.ResetPasswordService;

public class ResetPasswordDelegate {

    public void processModify(User user) {
        ResetPasswordService service = new ResetPasswordService();
        service.processModify(user);
    }
}
