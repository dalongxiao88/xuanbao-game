package org.come.entity;

import java.util.ArrayList;
import java.util.List;
import org.come.bean.RoleTxBean;
import org.come.until.UserMessUntil;
import java.math.BigDecimal;

public class PackRecord
{
    private BigDecimal roleId;
    private String record;
    private String helpBb;
    private String petOrder;
    private String helpLing;
    private String tx;
    private int suitNum;
    private String suit1;
    private String suit2;
    private String suit3;
    private String suit4;
    private String suit5;
    private String suit6;
    private String suit7;
    private String suit8;
    private String suit9;
    private String suit10;
    private String suit11;
    private String collect;
    private String lCard;
    
    public StringBuffer getPutTXBuffer() {
        if (this.tx != null && !this.tx.equals("")) {
            StringBuffer buffer = null;
            String[] txs = this.tx.split("\\|");
            for (int i = 0; i < txs.length; ++i) {
                if (txs[i].endsWith("#")) {
                    int id = Integer.parseInt(txs[i].substring(0, txs[i].length() - 1));
                    RoleTxBean bean = UserMessUntil.getTxBean(id);
                    if (bean != null) {
                        if (buffer == null) {
                            buffer = new StringBuffer();
                        }
                        else {
                            buffer.append("|");
                        }
                        buffer.append((bean.getRdType() == 1) ? "X" : ((bean.getRdType() == 2) ? "P" : "J"));
                        buffer.append(bean.getRdId());
                        if (bean.getRdType() == 1 || bean.getRdType() == 2) {
                            buffer.append("_");
                            buffer.append(bean.getRdStatues() - bean.getRdType());
                        }
                    }
                }
            }
            return buffer;
        }
        else {
            return null;
        }
    }
    
    public List<String> getPutTX() {
        if (this.tx == null || this.tx.equals("")) {
            return null;
        }
        List<String> list = null;
        String[] txs = this.tx.split("\\|");
        for (int i = 0; i < txs.length; ++i) {
            if (txs[i].endsWith("#")) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(txs[i].substring(0, txs[i].length() - 1));
            }
        }
        return list;
    }
    
    public List<String> getPutTXK() {
        if (this.tx == null || this.tx.equals("")) {
            return null;
        }
        List<String> list = null;
        String[] txs = this.tx.split("\\|");
        list = new ArrayList<>();
        for (int i = 0; i < txs.length; ++i) {
            if (txs[i].endsWith("&")) {
                list.add(txs[i].substring(0, txs[i].length() - 1));
            }
        }
        return list;
    }
    
    public String putTX(String... ps) {
        if (this.tx == null || this.tx.equals("")) {
            return this.tx;
        }
        String[] txs = this.tx.split("\\|");
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < txs.length; ++i) {
            String tid = txs[i];
            if (tid.endsWith("#")) {
                tid = tid.substring(0, tid.length() - 1);
            }
            int j = 0;
            while (j < ps.length) {
                if (tid.equals(ps[j])) {
                    tid += "#";
                    break;
                }
                else {
                    ++j;
                }
            }
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(tid);
        }
        return this.tx = buffer.toString();
    }
    
    public String putTXK(String... ps) {
        if (this.tx == null || this.tx.equals("")) {
            return this.tx;
        }
        String[] txs = this.tx.split("\\|");
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < txs.length; ++i) {
            String tid = txs[i];
            if (tid.endsWith("&")) {
                tid = txs[i].substring(0, txs[i].length() - 1);
            }
            int j = 0;
            while (j < ps.length) {
                if (tid.equals(ps[j])) {
                    tid += "&";
                    break;
                }
                else {
                    ++j;
                }
            }
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(tid);
        }
        return this.tx = buffer.toString();
    }
    
    public String addTX(String id) {
        if (this.isTX(id)) {
            return this.tx;
        }
        if (this.tx == null || this.tx.equals("")) {
            this.tx = id;
        }
        else {
            this.tx = this.tx + "|" + id;
        }
        return this.tx;
    }
    
    public String removeTX(String id) {
        if (this.tx == null || this.tx.equals("")) {
            return this.tx;
        }
        String[] txs = this.tx.split("\\|");
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < txs.length; ++i) {
            String tid = txs[i];
            if (tid.endsWith("#")) {
                tid = tid.substring(0, tid.length() - 1);
            }
            if (!id.equals(tid)) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(txs[i]);
            }
        }
        return this.tx = buffer.toString();
    }
    
    public boolean isTX(String id) {
        if (this.tx == null || this.tx.equals("")) {
            return false;
        }
        String[] txs = this.tx.split("\\|");
        for (int i = 0; i < txs.length; ++i) {
            if (Integer.parseInt(txs[i].substring(0, txs[i].length() - 1)) >= 1294) {
                if (txs[i].endsWith("&")) {
                    txs[i] = txs[i].substring(0, txs[i].length() - 1);
                }
            }
            else if (txs[i].endsWith("#")) {
                txs[i] = txs[i].substring(0, txs[i].length() - 1);
            }
            if (id.equals(txs[i])) {
                return true;
            }
        }
        return false;
    }
    
    public PartJade getPartJade(int suitid, int partId) {
        PartJade partJade = new PartJade(suitid, partId);
        String suit = this.getSuit(suitid);
        if (suit == null || suit.equals("")) {
            return partJade;
        }
        String[] suits = suit.split("\\|");
        for (int i = 0; i < suits.length; ++i) {
            String[] suits2 = suits[i].split("-");
            int sid = Integer.parseInt(suits2[0]);
            if (suitid == sid) {
                int j = 1;
                while (j < suits2.length) {
                    String[] suits3 = suits2[j].split("_");
                    int pid = Integer.parseInt(suits3[0]);
                    if (partId != pid) {
                        ++j;
                    }
                    else {
                        partJade.initJade(suits3);
                        return partJade;
                    }
                }
            }
        }
        return partJade;
    }
    
    public void setPartJade(int suitid, int partId, int pz, int sum) {
        String suit = this.getSuit(suitid);
        if (suit == null || suit.equals("")) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(suitid);
            buffer.append("-");
            buffer.append(partId);
            for (int i = 1; i < 6; ++i) {
                buffer.append("_");
                if (i == pz) {
                    buffer.append(sum);
                }
                else {
                    buffer.append(0);
                }
            }
            this.setSuit(suitid, buffer.toString());
            return;
        }
        else {
            PartJade partJade = new PartJade(suitid, partId);
            StringBuffer buffer2 = new StringBuffer();
            String[] suits = suit.split("\\|");
            boolean is1 = true;
            for (int j = 0; j < suits.length; ++j) {
                String[] suits2 = suits[j].split("-");
                int sid = Integer.parseInt(suits2[0]);
                if (suitid != sid) {
                    if (j != 0) {
                        buffer2.append("|");
                    }
                    buffer2.append(suits[j]);
                }
                else {
                    if (j != 0) {
                        buffer2.append("|");
                    }
                    is1 = false;
                    boolean is2 = true;
                    buffer2.append(suits2[0]);
                    for (int k = 1; k < suits2.length; ++k) {
                        String[] suits3 = suits2[k].split("_");
                        int pid = Integer.parseInt(suits3[0]);
                        if (partId != pid) {
                            buffer2.append("-");
                            buffer2.append(suits2[k]);
                        }
                        else {
                            is2 = false;
                            partJade.initJade(suits3);
                            partJade.setJade(pz, sum);
                            if (!partJade.isJade()) {
                                buffer2.append("-");
                                buffer2.append(partJade.toString());
                            }
                        }
                    }
                    if (is2) {
                        partJade.setJade(pz, sum);
                        if (!partJade.isJade()) {
                            buffer2.append("-");
                            buffer2.append(partJade.toString());
                        }
                    }
                }
            }
            if (is1) {
                buffer2.append("|");
                buffer2.append(suitid);
                partJade.setJade(pz, sum);
                if (!partJade.isJade()) {
                    buffer2.append("-");
                    buffer2.append(partJade.toString());
                }
            }
            this.setSuit(suitid, buffer2.toString());
            return;
        }
    }
    
    public void setPartJade(PartJade partJade) {
        String suit = this.getSuit(partJade.getSuitid());
        if (suit == null || suit.equals("")) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(partJade.getSuitid());
            buffer.append("-");
            buffer.append(partJade.getPartId());
            for (int i = 1; i < 6; ++i) {
                buffer.append("_");
                buffer.append(partJade.getJade(i));
            }
            this.setSuit(partJade.getSuitid(), buffer.toString());
            return;
        }
        else {
            StringBuffer buffer = new StringBuffer();
            String[] suits = suit.split("\\|");
            boolean is1 = true;
            for (int j = 0; j < suits.length; ++j) {
                String[] suits2 = suits[j].split("-");
                int sid = Integer.parseInt(suits2[0]);
                if (partJade.getSuitid() != sid) {
                    if (j != 0) {
                        buffer.append("|");
                    }
                    buffer.append(suits[j]);
                }
                else {
                    if (j != 0) {
                        buffer.append("|");
                    }
                    is1 = false;
                    boolean is2 = true;
                    buffer.append(suits2[0]);
                    for (int k = 1; k < suits2.length; ++k) {
                        String[] suits3 = suits2[k].split("_");
                        int pid = Integer.parseInt(suits3[0]);
                        if (partJade.getPartId() != pid) {
                            buffer.append("-");
                            buffer.append(suits2[k]);
                        }
                        else {
                            is2 = false;
                            if (!partJade.isJade()) {
                                buffer.append("-");
                                buffer.append(partJade.toString());
                            }
                        }
                    }
                    if (is2 && !partJade.isJade()) {
                        buffer.append("-");
                        buffer.append(partJade.toString());
                    }
                }
            }
            if (is1) {
                buffer.append("|");
                buffer.append(partJade.getSuitid());
                if (!partJade.isJade()) {
                    buffer.append("-");
                    buffer.append(partJade.toString());
                }
            }
            this.setSuit(partJade.getSuitid(), buffer.toString());
            return;
        }
    }
    
    public void setCollect(int suitid, int partId) {
        if (this.collect == null || this.collect.equals("")) {
            this.collect = suitid + "-" + partId;
            return;
        }
        StringBuffer buffer = new StringBuffer();
        String[] collects = this.collect.split("\\|");
        boolean is1 = true;
        for (int i = 0; i < collects.length; ++i) {
            String[] collects2 = collects[i].split("-");
            int sid = Integer.parseInt(collects2[0]);
            if (suitid != sid) {
                if (i != 0) {
                    buffer.append("|");
                }
                buffer.append(collects[i]);
            }
            else {
                if (i != 0) {
                    buffer.append("|");
                }
                is1 = false;
                boolean is2 = true;
                buffer.append(collects2[0]);
                for (int j = 1; j < collects2.length; ++j) {
                    int pid = Integer.parseInt(collects2[j]);
                    if (partId != pid) {
                        buffer.append("-");
                        buffer.append(collects2[j]);
                    }
                    else {
                        is2 = false;
                    }
                }
                if (is2) {
                    buffer.append("-");
                    buffer.append(partId);
                }
            }
        }
        if (is1) {
            buffer.append("|");
            buffer.append(suitid);
            buffer.append("-");
            buffer.append(partId);
        }
        this.collect = buffer.toString();
    }
    
    public List<Integer> accessCollect() {
        if (this.collect == null || this.collect.equals("")) {
            return null;
        }
        List<Integer> listSid = new ArrayList<>();
        String[] collects = this.collect.split("\\|");
        for (int i = 0; i < collects.length; ++i) {
            String[] collects2 = collects[i].split("-");
            int sid = Integer.parseInt(collects2[0]);
            listSid.add(Integer.valueOf(sid));
        }
        return listSid;
    }
    
    public String[] getCollect(int suitid) {
        if (this.collect == null || this.collect.equals("")) {
            return null;
        }
        String[] collects = this.collect.split("\\|");
        int i = 0;
        while (i < collects.length) {
            String[] collects2 = collects[i].split("-");
            int sid = Integer.parseInt(collects2[0]);
            if (suitid != sid) {
                ++i;
            }
            else {
                String[] v = new String[collects2.length - 1];
                for (int j = 1; j < collects2.length; ++j) {
                    v[j - 1] = collects2[j];
                }
                return v;
            }
        }
        return null;
    }
    
    public String isCollect(int suitId, int partId) {
        if (this.collect == null || this.collect.equals("")) {
            return null;
        }
        String[] collects = this.collect.split("\\|");
        int i = 0;
        while (i < collects.length) {
            String[] collects2 = collects[i].split("-");
            int sid = Integer.parseInt(collects2[0]);
            if (suitId != sid) {
                ++i;
            }
            else {
                for (int j = 1; j < collects2.length; ++j) {
                    if (Integer.parseInt(collects2[j]) == partId) {
                        return "不能重复收录";
                    }
                }
                return null;
            }
        }
        if (this.suitNum <= collects.length) {
            return "达到最大收录数";
        }
        return null;
    }
    
    public String getSuit(int id) {
        if (id <= 0 || id > 110) {
            return null;
        }
        id = (id - 1) / 10;
        switch (id) {
            case 0: {
                return this.suit1;
            }
            case 1: {
                return this.suit2;
            }
            case 2: {
                return this.suit3;
            }
            case 3: {
                return this.suit4;
            }
            case 4: {
                return this.suit5;
            }
            case 5: {
                return this.suit6;
            }
            case 6: {
                return this.suit7;
            }
            case 7: {
                return this.suit8;
            }
            case 8: {
                return this.suit9;
            }
            case 9: {
                return this.suit10;
            }
            case 10: {
                return this.suit11;
            }
            default: {
                return null;
            }
        }
    }
    
    public void setSuit(int id, String suit) {
        if (id <= 0 || id > 110) {
            return;
        }
        id = (id - 1) / 10;
        switch (id) {
            case 0: {
                this.suit1 = suit;
                break;
            }
            case 1: {
                this.suit2 = suit;
                break;
            }
            case 2: {
                this.suit3 = suit;
                break;
            }
            case 3: {
                this.suit4 = suit;
                break;
            }
            case 4: {
                this.suit5 = suit;
                break;
            }
            case 5: {
                this.suit6 = suit;
                break;
            }
            case 6: {
                this.suit7 = suit;
                break;
            }
            case 7: {
                this.suit8 = suit;
                break;
            }
            case 8: {
                this.suit9 = suit;
                break;
            }
            case 9: {
                this.suit10 = suit;
                break;
            }
            case 10: {
                this.suit11 = suit;
                break;
            }
        }
    }
    
    public BigDecimal getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
    
    public String getRecord() {
        return this.record;
    }
    
    public void setRecord(String record) {
        this.record = ((record == null) ? null : record.trim());
    }
    
    public String getHelpBb() {
        return this.helpBb;
    }
    
    public void setHelpBb(String helpBb) {
        this.helpBb = helpBb;
    }
    
    public String getPetOrder() {
        return this.petOrder;
    }
    
    public void setPetOrder(String petOrder) {
        this.petOrder = petOrder;
    }
    
    public String getHelpLing() {
        return this.helpLing;
    }
    
    public void setHelpLing(String helpLing) {
        this.helpLing = helpLing;
    }
    
    public String getSuit1() {
        return this.suit1;
    }
    
    public void setSuit1(String suit1) {
        this.suit1 = suit1;
    }
    
    public String getSuit2() {
        return this.suit2;
    }
    
    public void setSuit2(String suit2) {
        this.suit2 = suit2;
    }
    
    public String getSuit3() {
        return this.suit3;
    }
    
    public void setSuit3(String suit3) {
        this.suit3 = suit3;
    }
    
    public String getSuit4() {
        return this.suit4;
    }
    
    public void setSuit4(String suit4) {
        this.suit4 = suit4;
    }
    
    public String getSuit5() {
        return this.suit5;
    }
    
    public void setSuit5(String suit5) {
        this.suit5 = suit5;
    }
    
    public String getSuit6() {
        return this.suit6;
    }
    
    public void setSuit6(String suit6) {
        this.suit6 = suit6;
    }
    
    public String getSuit7() {
        return this.suit7;
    }
    
    public void setSuit7(String suit7) {
        this.suit7 = suit7;
    }
    
    public String getSuit8() {
        return this.suit8;
    }
    
    public void setSuit8(String suit8) {
        this.suit8 = suit8;
    }
    
    public String getSuit9() {
        return this.suit9;
    }
    
    public void setSuit9(String suit9) {
        this.suit9 = suit9;
    }
    
    public String getSuit10() {
        return this.suit10;
    }
    
    public void setSuit10(String suit10) {
        this.suit10 = suit10;
    }
    
    public String getSuit11() {
        return this.suit11;
    }
    
    public void setSuit11(String suit11) {
        this.suit11 = suit11;
    }
    
    public String getCollect() {
        return this.collect;
    }
    
    public void setCollect(String collect) {
        this.collect = collect;
    }
    
    public int getSuitNum() {
        return this.suitNum;
    }
    
    public void setSuitNum(int suitNum) {
        this.suitNum = suitNum;
    }
    
    public String getTx() {
        return this.tx;
    }
    
    public void setTx(String tx) {
        this.tx = tx;
    }
    
    public String getlCard() {
        return this.lCard;
    }
    
    public void setlCard(String lCard) {
        this.lCard = lCard;
    }
}
