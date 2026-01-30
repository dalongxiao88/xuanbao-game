package org.come.action.lottery;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;

public class HDXThread implements Runnable
{
    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(10 - i);
            SendMessage.sendMessageToMapRoles(Long.valueOf(1197L), Agreement.getAgreement().PromptAgreement(buffer.toString()));
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
