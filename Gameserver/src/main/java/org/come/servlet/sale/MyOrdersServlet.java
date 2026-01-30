package org.come.servlet.sale;

import java.io.PrintWriter;
import org.come.entity.Roleorder;
import java.util.List;
import org.come.until.GsonUtil;
import org.come.bean.SearchOrderResultBean;
import com.github.pagehelper.PageInfo;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import com.github.pagehelper.BasePageHelper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class MyOrdersServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String pageNum = request.getParameter("page");
        String roleid = request.getParameter("Role_Id");
        BasePageHelper.startPage(Integer.parseInt(pageNum), 15);
        List<Roleorder> list = AllServiceUtil.getRoleorderService().selectRoleOrders(new BigDecimal(roleid));
        PageInfo<Roleorder> pageInfo = new PageInfo(list);
        SearchOrderResultBean resultBean = new SearchOrderResultBean();
        resultBean.setRoleorders(pageInfo.getList());
        resultBean.setTotal(Integer.valueOf(pageInfo.getPages()));
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(resultBean));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
