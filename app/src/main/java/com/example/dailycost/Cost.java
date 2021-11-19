package com.example.dailycost;

import org.litepal.crud.LitePalSupport;
import java.util.Date;

public class Cost extends LitePalSupport {
    Double money;
    Date date;
    int cost;
    int Imagid;
    String reason;

    public  Cost(Double m){
        money=m;
    }

    public int getImagid() {
        return Imagid;
    }

    public void setImagid(int imagid) {
        Imagid = imagid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
