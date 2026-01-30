package org.come.servlet.sale;

import java.io.PrintWriter;
import org.come.entity.Salegoods;
import org.come.entity.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import org.come.until.GsonUtil;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.bean.GoodsDetailsBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class GoodsDetailServlet extends HttpServlet
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
        String saleid = request.getParameter("saleid");
        String otherid = request.getParameter("otherid");
        String saletype = request.getParameter("saletype");
        GoodsDetailsBean bean = new GoodsDetailsBean();
        if ("3".equals(saletype) || "5".equals(saletype)) {
            Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(otherid));
            bean.setGoodstable(goodstable);
        }
        else if ("4".equals(saletype)) {
            RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(otherid));
            bean.setRoleSummoning(pet);
        }
        else if ("6".equals(saletype)) {
            Lingbao lingbao = AllServiceUtil.getLingbaoService().selectByPrimaryKey(new BigDecimal(otherid));
            bean.setLingbao(lingbao);
        }
        Salegoods salegoods = AllServiceUtil.getSalegoodsService().selectByPrimaryKey(new BigDecimal(saleid));
        bean.setSalegoods(salegoods);
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(bean));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
