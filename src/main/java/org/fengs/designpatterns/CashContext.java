package org.fengs.designpatterns;

public class CashContext {
    private CashSuper cs;

    public CashContext(String type){
        this.cs = switch (type){
            case "正常收费" -> new CashNormal();
            case "满300返100" -> new CashReturn(300,100);
            case "打8折" -> new CashRebate(0.8);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    public double getResult(double money){
        return cs.acceptCash(money);
    }

}
