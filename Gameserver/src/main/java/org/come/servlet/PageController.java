package org.come.servlet;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PageController
{
    @RequestMapping({ "/" })
    private String showIndex() {
        return "exchange";
    }
    
    @RequestMapping({ "/{page}" })
    public String showPage(@PathVariable String page, HttpServletRequest request) {
        if (request.getSession().getAttribute("manger") != null) {
            return page;
        }
        return "login";
    }
}
