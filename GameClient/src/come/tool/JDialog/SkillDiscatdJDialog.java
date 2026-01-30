package come.tool.JDialog;

import org.come.model.Lingbao;
import org.come.Frame.LingbaoJframe;
import com.tool.role.RoleLingFa;
import org.come.until.UserData;

public class SkillDiscatdJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        String skill = (String)object;
        if (tishi && UserData.uptael(800000L)) {
            Lingbao lingbao = RoleLingFa.getRoleLingFa().getChoseBao();
            String sikils = lingbao.getSkills();
            lingbao.setSkills(UserData.Splice(sikils, skill, 0));
            lingbao.UpdateLing();
            LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().shuxingzhanshi(lingbao);
        }
    }
}
