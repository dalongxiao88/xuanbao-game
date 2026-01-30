package org.come.annex.Tournaments.Other;

import java.math.BigDecimal;
import java.util.List;

public class GameFiguresBean {
   private Integer total;
   private String msg;
   private List<GameFigures> gameFigures;
   private BigDecimal captainId1;
   private BigDecimal captainId2;

   public Integer getTotal() {
      return this.total;
   }

   public void setTotal(Integer total) {
      this.total = total;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public List<GameFigures> getGameFigures() {
      return this.gameFigures;
   }

   public void setGameFigures(List<GameFigures> gameFigures) {
      this.gameFigures = gameFigures;
   }

   public BigDecimal getCaptainId1() {
      return this.captainId1;
   }

   public void setCaptainId1(BigDecimal captainId1) {
      this.captainId1 = captainId1;
   }

   public BigDecimal getCaptainId2() {
      return this.captainId2;
   }

   public void setCaptainId2(BigDecimal captainId2) {
      this.captainId2 = captainId2;
   }
}
