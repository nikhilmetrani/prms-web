package sg.edu.nus.iss.phoenix.producer.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.producer.service.ReviewSelectProducerService;

public class ReviewSelectProducerDelegate {
    private ReviewSelectProducerService service;
    
	public ReviewSelectProducerDelegate() {
		service = new ReviewSelectProducerService();
	}
	
	public List<User> reviewSelectRadioProducer() {
		return service.reviewSelectProducer();	
	}

}
