package org.come.nettyClient;

import java.io.IOException;

public class ServerToClientMesControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes) throws IOException {
        String ab = mes.substring(0, 4);
        String ReceiveMes = null;
        FromServerAction action = (FromServerAction)ClientMapAction.serverAction.get(ab);
        if (action != null) {
            return;
        }
        mes = Clinet_NewAESUtil.AESJDKDncode(mes);
        if (mes == null) {
            System.out.println("服务器返回空数据");
            return;
        }
        int wz = mes.indexOf("//");
        if (wz == -1) {
            return;
        }
        ReceiveMes = mes.substring(wz + 2);
        ab = (ab = mes.substring(0, wz));
        int n = -1;
        switch (ab.hashCode()) {
            case 697212239: {
                if (ab.equals("LOGINVERSION")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                ((FromServerAction)ClientMapAction.serverAction.get("LOGINVERSION")).controlMessFromServer(ReceiveMes);
                break;
            }
        }
    }
}
