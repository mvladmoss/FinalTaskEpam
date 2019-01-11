package com.epam.fitness.uitls.sale;

public enum SaleSystem {
    less_than_3(5f,3),
    less_than_7(8f,7),
    less_than_20(10.5f,20);

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
