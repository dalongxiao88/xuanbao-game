package org.come.tt;

import java.awt.event.MouseEvent;
import org.come.until.Music;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;

public class EventSelectionMouseListener implements MouseListener
{
    private EventSelectionJpanel eventSelectionJpanel;
    private Integer type;
    
    public EventSelectionMouseListener(EventSelectionJpanel eventSelectionJpanel, Integer type) {
        this.eventSelectionJpanel = eventSelectionJpanel;
        this.type = type;
    }
    
    private void eventHandling(Integer type, Integer index) {
        switch ((int)type) {
            case 1: {
                if ((int)index == 1) {
                    this.eventSelectionJpanel.getTtBox_1_0().setVisible(false);
                    this.eventSelectionJpanel.getTtBox_1_1().setVisible(true);
                    break;
                }
                else if ((int)index == 2) {
                    FormsManagement.showForm(604);
                    String mes = Agreement.getAgreement().pankinglistAgreement("8");
                    SendMessageUntil.toServer(mes);
                    Music.addyinxiao("开关窗口.mp3");
                    FormsManagement.HideForm(605);
                    break;
                }
                else if ((int)index == 3) {
                    this.eventSelectionJpanel.getTtBox_1_0().setVisible(true);
                    this.eventSelectionJpanel.getTtBox_1_1().setVisible(false);
                    break;
                }
                else {
                    break;
                }
            }
            case 2: {
                if ((int)index == 1) {
                    this.eventSelectionJpanel.getTtBox_2_0().setVisible(false);
                    this.eventSelectionJpanel.getTtBox_2_1().setVisible(true);
                    break;
                }
                else if ((int)index == 2) {
                    Music.addyinxiao("开关窗口.mp3");
                    break;
                }
                else if ((int)index == 3) {
                    this.eventSelectionJpanel.getTtBox_2_0().setVisible(true);
                    this.eventSelectionJpanel.getTtBox_2_1().setVisible(false);
                    break;
                }
                else {
                    break;
                }
            }
            case 3: {
                if ((int)index == 1) {
                    this.eventSelectionJpanel.getTtBox_3_0().setVisible(false);
                    this.eventSelectionJpanel.getTtBox_3_1().setVisible(true);
                    break;
                }
                else if ((int)index == 2) {
                    Music.addyinxiao("开关窗口.mp3");
                    break;
                }
                else if ((int)index == 3) {
                    this.eventSelectionJpanel.getTtBox_3_0().setVisible(true);
                    this.eventSelectionJpanel.getTtBox_3_1().setVisible(false);
                    break;
                }
                else {
                    break;
                }
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.eventHandling(this.type, Integer.valueOf(2));
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        this.eventHandling(this.type, Integer.valueOf(1));
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        this.eventHandling(this.type, Integer.valueOf(3));
    }
}
