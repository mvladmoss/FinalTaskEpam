package com.epam.fitness.sale;

public enum SaleSystem {
    less_than_3(new Float(5),3),
    less_than_7(new Float(8),7),
    less_than_20(new Float(10.5),20);

    public static Float getSaleByVisitNumber(int visitNumber){
        for(SaleSystem sale : SaleSystem.values()){
            if(visitNumber < sale.getMinimumVisitNumber()){
                return sale.getSale();
            }
        }
        return less_than_20.getSale();
    }

    private Float sale;
    private int minimumVisitNumber;

    private Float getSale(){
        return sale;
    }

    private int getMinimumVisitNumber(){
        return minimumVisitNumber;
    }


    private SaleSystem(Float sale,int minimumVisitNumber) {
        this.sale = sale;
        this.minimumVisitNumber = minimumVisitNumber;
    }
}
