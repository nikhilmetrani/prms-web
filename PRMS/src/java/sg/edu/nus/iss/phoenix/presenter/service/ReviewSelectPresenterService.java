package sg.edu.nus.iss.phoenix.presenter.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

public class ReviewSelectPresenterService {
	DAOFactoryImpl factory;
	UserDao userdao;

	public ReviewSelectPresenterService() {
		super();
		// TODO Auto-generated constructor stub
		factory = new DAOFactoryImpl();
		userdao = factory.getUserDAO();
	}

	public List<User> reviewSelectPresenter(String name) {
            List<User> data = null;
            try {
                data = userdao.loadPresenter(name);
            } catch (SQLException ex) {
                Logger.getLogger(ReviewSelectPresenterService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return data; 
	}

}
