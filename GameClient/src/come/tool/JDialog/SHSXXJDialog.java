package come.tool.JDialog;

import com.google.gson.Gson;
import java.util.List;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import org.come.MountShouHu.ShouhuPackJpanel;
import org.come.MountShouHu.ShouhuPackJframe;
import org.come.entity.Mount;
import org.come.until.UserMessUntil;

public class SHSXXJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            List<Mount> list = UserMessUntil.getMountlsit();
            for (int i1 = 0; i1 <= list.size() - 1; ++i1) {
                if ((int)((Mount)list.get(i1)).getMountid() == (int)object) {
                    int f = 0;
                    while (true) {
                        int n = f;
                        ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                        if (n <= ShouhuPackJpanel.Eqment.size() - 1) {
                            ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                            if (((Goodstable)ShouhuPackJpanel.Eqment.get(f)).getRgid().intValue() == ((Mount)list.get(i1)).getShouhu()) {
                                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                                ((Goodstable)ShouhuPackJpanel.Eqment.get(f)).setStatus(Integer.valueOf(0));
                                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                                GoodsListFromServerUntil.newgood((Goodstable)ShouhuPackJpanel.Eqment.get(f));
                                List<Goodstable> goodstableList = ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList();
                                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                                goodstableList.add(ShouhuPackJpanel.Eqment.get(f));
                                Agreement agreement = Agreement.getAgreement();
                                StringBuilder append = new StringBuilder().append("upshuanimabi&");
                                Gson getgson = GsonUtil.getGsonUtil().getgson();
                                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                                String sendmes2 = agreement.rolechangeAgreement(append.append(getgson.toJson(ShouhuPackJpanel.Eqment.get(f))).toString());
                                SendMessageUntil.toServer(sendmes2);
                            }
                            ++f;
                        }
                        else {
                            break;
                        }
                    }
                    int finalI = i1;
                    ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                    ShouhuPackJpanel.Eqment.removeIf(q/* org.come.entity.Goodstable, */ -> q.getRgid().intValue() == ((Mount)list.get(finalI)).getShouhu());
                    ((Mount)list.get(i1)).setShouhu(0);
                    String sendmes3 = Agreement.getAgreement().changeMountValue(GsonUtil.getGsonUtil().getgson().toJson(list.get(i1)));
                    SendMessageUntil.toServer(sendmes3);
                }
            }
        }
    }
}
