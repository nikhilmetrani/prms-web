package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

public class DeleteUserService {

    private static final Logger logger
            = Logger.getLogger(DeleteUserService.class.getName());

    DAOFactoryImpl factory;
    UserDao udao;

    public DeleteUserService() {
        super();
        // TODO Auto-generated constructor stub
        factory = new DAOFactoryImpl();
        udao = factory.getUserDAO();
    }

    public void processDelete(User user) {

        try {
            udao.delete(user);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
