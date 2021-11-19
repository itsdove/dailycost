package com.example.dailycost;

import java.util.Date;

public class Cost {
    Double money;
    Date date;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

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
}
