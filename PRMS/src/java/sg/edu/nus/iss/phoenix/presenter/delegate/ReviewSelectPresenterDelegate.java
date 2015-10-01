package sg.edu.nus.iss.phoenix.presenter.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.presenter.service.ReviewSelectPresenterService;

public class ReviewSelectPresenterDelegate {
    private ReviewSelectPresenterService service;
    
	public ReviewSelectPresenterDelegate() {
		service = new ReviewSelectPresenterService();
	}
	
	public List<User> reviewSelectPresenter(String name) {
		return service.reviewSelectPresenter(name);	
	}

}
