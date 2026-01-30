package org.come.servlet.sale;

import java.io.PrintWriter;
import org.come.entity.Salegoods;
import java.util.List;
import org.come.until.GsonUtil;
import org.come.bean.SearchGoodsResultBean;
import com.github.pagehelper.PageInfo;
import org.come.until.AllServiceUtil;
import com.github.pagehelper.BasePageHelper;
import org.come.action.sale.MyOrderSearchAction;
import org.come.action.sale.GoodsSearchAction;
import org.come.entity.SalegoodsExample;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class GoodsShowServlet extends HttpServlet
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
        String type = request.getParameter("Contiontype");
        String pageNum = request.getParameter("PageNow");
        String shows = request.getParameter("show");
        String orders = request.getParameter("order");
        String saletypes = request.getParameter("saletype");
        int saletype = 1;
        if (saletypes != null && !"".equals(saletypes)) {
            saletype = Integer.parseInt(saletypes);
        }
        int show = Integer.parseInt(shows);
        int order = Integer.parseInt(orders);
        SalegoodsExample example = new SalegoodsExample();
        SalegoodsExample.Criteria c = example.createCriteria();
        int dataNum = 15;
        if (type != null && !"".equals(type)) {
            if (saletype == 3) {
                GoodsSearchAction.selectProp(type, c);
            }
            else if (saletype == 4) {
                GoodsSearchAction.selectPet(type, c);
            }
            else if (saletype == 5) {
                GoodsSearchAction.selectEquip(type, c);
            }
        }
        if (saletype != 1) {
            c.andSaletypeEqualTo(Integer.valueOf(saletype));
            if (saletype != 2) {
                if (show == 0) {
                    c.andUptimeLessThan(MyOrderSearchAction.getDate(Integer.valueOf(1)));
                }
                else if (show == 2) {
                    c.andUptimeGreaterThan(MyOrderSearchAction.getDate(Integer.valueOf(1)));
                }
            }
        }
        else if (show == 0) {
            c.andUptimeLessThan(MyOrderSearchAction.getDate(Integer.valueOf(1)));
            c.andSaletypeNotEqualTo(Integer.valueOf(10));
            SalegoodsExample example2 = new SalegoodsExample();
            SalegoodsExample.Criteria criteria = example2.createCriteria();
            criteria.andSaletypeEqualTo(Integer.valueOf(2));
            criteria.andFlagEqualTo(Integer.valueOf(2));
            example.or(criteria);
        }
        else if (show == 2) {
            c.andUptimeGreaterThan(MyOrderSearchAction.getDate(Integer.valueOf(1)));
            c.andSaletypeNotEqualTo(Integer.valueOf(2));
            c.andSaletypeNotEqualTo(Integer.valueOf(10));
        }
        if (order == 1) {
            example.setOrderByClause("saleprice");
        }
        else if (order == 2) {
            example.setOrderByClause("saleprice desc");
        }
        c.andFlagEqualTo(Integer.valueOf(2));
        BasePageHelper.startPage(Integer.parseInt(pageNum), dataNum);
        List<Salegoods> list = AllServiceUtil.getSalegoodsService().selectByExample(example);
        PageInfo<Salegoods> pageInfo = new PageInfo(list);
        SearchGoodsResultBean resultBean = new SearchGoodsResultBean();
        resultBean.setSalegoods(pageInfo.getList());
        resultBean.setTotal(pageInfo.getPages());
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(resultBean));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
