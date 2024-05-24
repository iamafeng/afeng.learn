package org.fengs.designpatterns;

public class CashNormal extends CashSuper{
    @Override
    double acceptCash(double money) {
        return money;
    }
}
