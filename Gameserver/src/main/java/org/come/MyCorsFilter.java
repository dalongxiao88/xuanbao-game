package org.come;

import com.gl.controller.UserController;
import com.gl.model.Result;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;
import javax.servlet.FilterConfig;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;

public class MyCorsFilter implements Filter
{
    private final List<String> allowedOrigins;
    
    public MyCorsFilter() {
//        this.allowedOrigins = Arrays.asList(new String[] { "http://localhost:9090", "http://localhost:8083", "http://localhost:18083", "http://localhost:8083" });
        this.allowedOrigins = Arrays.asList(new String[] { "" });
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String origin = request.getHeader("Origin");
//        Result ipCheckResult = UserController.IPstop(request);
//        if (ipCheckResult != null) {
//            PrintWriter pwPrintWriter = response.getWriter();
//            pwPrintWriter.write("caonima");
//            pwPrintWriter.flush();
//            pwPrintWriter.close();
//            return;
//        }
        response.setHeader("Access-Control-Allow-Origin", this.allowedOrigins.contains(origin) ? origin : "");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,manage_token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "manage_token");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }
    
    @Override
    public void destroy() {
    }
}
