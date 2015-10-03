/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.core.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 * Intercepts the request and finds whether it's valid request from authenticated user, else routes the request to login page
 * 
 * @author Ganapathy Rajan Jaya Vignesh
 */
public class SecurityFilter implements Filter{

    private FilterConfig config; 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hreq = (HttpServletRequest) request;
        HttpServletResponse hres = (HttpServletResponse) response;
        HttpSession session = hreq.getSession();
        String path = hreq.getServletPath();
        
        if( session != null && (path != null 
                && (!path.endsWith("/home.jsp") 
                && !path.endsWith("/login.jsp") 
                && !path.endsWith("/error.jsp") 
                && !path.equals("") 
                && !path.endsWith("/index.jsp"))) )
        {
            User user = (User) session.getAttribute("user");
            if(user == null){
                hres.sendRedirect(hreq.getContextPath()+"/pages/login.jsp");
                return;
            }
        }else if(session == null){
            hres.sendRedirect(hreq.getContextPath()+"/pages/login.jsp");
            return;
        }
        
        chain.doFilter(request, response);
        return;
        
    }

    @Override
    public void destroy() {
        
    }
    
}
