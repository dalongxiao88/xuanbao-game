package org.come.pay;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;
import javax.servlet.FilterConfig;
import javax.servlet.Filter;

public class AdminFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest q1 = (HttpServletRequest)request;
        String path = q1.getRequestURI();
        if (q1.getSession().getAttribute("manger") == null) {
            if (path.endsWith("/nishishabiwocaosinimade") || path.endsWith("/login.js") || path.contains("/GetTXT/") || path.contains("/js/") || path.contains("media") || path.contains("/META-INF/") || path.contains("/classes/") || path.contains("/lib/")) {
                chain.doFilter(request, response);
            }
            else if (path.endsWith("/GameServer/api/login") || path.endsWith("/GameServer/api/newLogin") || path.endsWith("/GameServer/api/adminUserlogin")) {
                chain.doFilter(request, response);
            }
            return;
        }
        else {
            if (path.endsWith("/nishishabiwocaosinimade")) {
                request.getRequestDispatcher("index").forward(request, response);
                return;
            }
            chain.doFilter(request, response);
            return;
        }
    }
    
    @Override
    public void destroy() {
    }
}
