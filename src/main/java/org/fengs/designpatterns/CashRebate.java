package org.fengs.designpatterns;

public class CashRebate extends CashSuper{

    private double rebate = 1d;

    public CashRebate(double rebate) {
        this.rebate = rebate;
    }

    @Override
    double acceptCash(double money) {
        return money * rebate;
    }

}
