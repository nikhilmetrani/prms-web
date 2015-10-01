/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.presenter.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.presenter.delegate.ReviewSelectPresenterDelegate;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author jingdong
 */
@Action("searchpresenter")
public class SearchPresenterCmd implements Perform {
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {   
        String name="";
        if(req.getParameter("presentername") != null)
        {
            name = req.getParameter("presentername");
        }
        ReviewSelectPresenterDelegate del = new ReviewSelectPresenterDelegate();
        List<User> data = del.reviewSelectPresenter(name);
        req.setAttribute("searchpresenterlist", data);
        return "/pages/searchpresenter.jsp";
    }
}
