package sg.edu.nus.iss.phoenix.authenticate.delegate;

import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.authenticate.entity.UserSearchObject;
import sg.edu.nus.iss.phoenix.authenticate.service.AuthenticateService;

public class AuthenticateDelegate {
	private AuthenticateService service;

    public AuthenticateDelegate() {
        super();
        service = new AuthenticateService();
    }

    public User validateUserIdPassword(User user) {
        return service.validateUserIdPassword(user);
    }

    public User evaluateAccessPreviledge(User user) {
        return service.evaluateAccessPreviledge(user);
    }

    public ArrayList<User> findAllUser() {
        AuthenticateService service = new AuthenticateService();
        return service.findAllUser();
    }

    public boolean checkUser(User user) {
        AuthenticateService service = new AuthenticateService();
        return service.isExist(user.getId());
    }

    public ArrayList<User> searchUsers(UserSearchObject uso) {
        User user = new User(uso.getId());
        user.setName(uso.getName());
        AuthenticateService service = new AuthenticateService();
        return service.searchUsers(user);
    }

    public User findUser(String userId) {
        AuthenticateService service = new AuthenticateService();
        return service.findUser(userId);
    }

    public void insertUser(User user) {
        AuthenticateService service = new AuthenticateService();
        service.insertUser(user);
    }

    public void updateUser(User user) {
        AuthenticateService service = new AuthenticateService();
        service.updateUser(user);
    }

    public void deleteUser(User user) {
        AuthenticateService service = new AuthenticateService();
        service.deleteUser(user);
    }

    public ArrayList<Role> findAllRole() {
        AuthenticateService service = new AuthenticateService();
        return service.findAllRoles();
    }

    public Role findRole(String role) {
        AuthenticateService service = new AuthenticateService();
        return service.findRole(role);
    }
	
}
