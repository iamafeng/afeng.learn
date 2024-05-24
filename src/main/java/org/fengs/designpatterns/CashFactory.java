package org.fengs.designpatterns;

public class CashFactory {
    public static CashSuper createCashAdapter(String type){
        return switch (type){
            case "正常收费" -> new CashNormal();
            case "满300减100" -> new CashReturn(300,100);
            case "打8折" -> new CashRebate(0.8);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
