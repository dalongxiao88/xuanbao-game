package org.come.action.exchange;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExchangeUtil
{
    private static List<Compensation> compensations;
    
    public static void init() {
        ExchangeUtil.compensations = new ArrayList<>();
    }
    
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
    
    public static void addCompensation(BigDecimal roleId, long time) {
        for (int i = ExchangeUtil.compensations.size() - 1; i >= 0; --i) {
            Compensation compensation = (Compensation)ExchangeUtil.compensations.get(i);
            if (compensation.contain(time)) {
                compensation.addMap(roleId);
            }
        }
    }
    
    public static Compensation getCompensation(String CDK) {
        for (int i = ExchangeUtil.compensations.size() - 1; i >= 0; --i) {
            Compensation compensation = (Compensation)ExchangeUtil.compensations.get(i);
            if (compensation.getCCDK().equals(CDK)) {
                return compensation;
            }
        }
        return null;
    }
}
