package come.tool.JDialog;

import org.come.socket.SendMessageUntil;

public class ChongTuJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        String sendmes = (String)object;
        if (tishi) {
            SendMessageUntil.toServer(sendmes);
        }
    }
}
