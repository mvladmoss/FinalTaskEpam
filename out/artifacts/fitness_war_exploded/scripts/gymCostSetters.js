function setCost(){
    var element = document.getElementById("period_cost");
    var cost = element.value;
    var option = document.getElementById('period_cost').options[document.getElementById('period_cost').selectedIndex];
    if(document.getElementById('period_cost').selectedIndex===0){
        document.getElementById("cost").value = '0.0$';
        document.getElementById("final_cost").value = '0.0$';
        return;
    }
    document.getElementById("period").value = option.text;
    document.getElementById("cost").value = cost;
    var finalCost = calculateFinalCost(cost);
    document.getElementById("final_cost").value = finalCost;
    document.getElementById("finalCostModalWindow").value = finalCost;
}

function calculateFinalCost(cost) {
    var personal_sale = Number(document.getElementById("personal_discount").value);
    var corporate_sale = Number(document.getElementById("corporate_discount").value);
    var generalSale = personal_sale + corporate_sale;
    return (cost*(1-generalSale/100)).toFixed(3);
}

function checkSelectOption() {
    var foo = document.getElementById('period_cost');
    if (foo)
    {
        if (foo.selectedIndex === 0)
        {
            alert('Please choose tariff')
        }else{
            document.getElementById("modal").click();
        }
    }
}