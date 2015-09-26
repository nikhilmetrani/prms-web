package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

public class CreateUserService {

    private static final Logger logger
            = Logger.getLogger(CreateUserService.class.getName());

    DAOFactoryImpl factory;
    UserDao udao;
    RoleDao rdao;

    public CreateUserService() {
        super();
        // TODO Auto-generated constructor stub
        factory = new DAOFactoryImpl();
        udao = factory.getUserDAO();
        rdao = factory.getRoleDAO();

    }

    public User processCreate(User user) throws NotFoundException {
        try {
            User userFound = udao.searchMatching(user.getId());
            if (userFound != null && userFound.isActiveUserFlag()) {
                logger.log(Level.SEVERE, "user already exists");
                return null;
            } else {
                if(userFound == null){
                    udao.create(user);    
                }else{
                    if(!userFound.isActiveUserFlag()){
                        userFound= (User)user.clone();
                        userFound.setActiveUserFlag(true);
                        try {
                            udao.save(userFound);
                        } catch (NotFoundException ex) {
                            Logger.getLogger(CreateUserService.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
//                Role existingRole = null;
//                existingRole = rdao.getObject(user.getRoleString());

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return user;
    }
}
