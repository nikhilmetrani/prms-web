package sg.edu.nus.iss.phoenix.producer.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

public class ReviewSelectProducerService {
	DAOFactoryImpl factory;
	UserDao userdao;

	public ReviewSelectProducerService() {
		super();
		// TODO Auto-generated constructor stub
		factory = new DAOFactoryImpl();
		userdao = factory.getUserDAO();
	}

	public List<User> reviewSelectProducer(String name) {
            List<User> data = null;
            try {
                data = userdao.loadProducer(name);
            } catch (SQLException ex) {
                Logger.getLogger(ReviewSelectProducerService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return data; 
	}

}
