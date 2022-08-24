package com.tunv.ham10000.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@Component
public class CustomURLFilter implements Filter {

    private final ServletContext servletContext;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomURLFilter.class);

    public CustomURLFilter(ServletContext servletContext) {
        this.servletContext = servletContext;

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("########## Initiating CustomURLFilter filter ##########");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = request.getSession();


        Cookie[] cookies = request.getCookies();     // request is an instance of type //HttpServletRequest
        boolean foundCookie = false;

        for (Cookie c : cookies) {
            if (c.getName().equals("tunv")) {
                foundCookie = true;
            }
        }

        if ((!foundCookie)) {
//        if (session.getAttribute("tunv") == null || ((String)session.getAttribute("tunv")).length() < 2 || (!foundCookie)) {
//            req = new HttpServletRequestWrapper((HttpServletRequest) request) {
//                @Override
//                public String getRequestURI() {
//                    return "redirect:/login";
//                }
//            };
            response.sendRedirect(servletContext.getContextPath() + "/login");
            return ;
        }

        filterChain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }
}