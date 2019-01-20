function setCost(){
    var element = document.getElementById("period_cost");
    var cost = element.value;
    var period = document.getElementById('period_cost').options[document.getElementById('period_cost').selectedIndex].text;
    document.getElementById("cost").value = cost;
    var finalCost = calculateFinalCost(cost);
    document.getElementById("final_cost").value = finalCost;
    document.getElementById("finalCostModalWindow").value = finalCost;
    var controllerAddress = document.form.action.match("(.*?\\&)");
    document.form.action = controllerAddress[0] + "period=" + String(period) + "&cost=" + finalCost;
}

function calculateFinalCost(cost) {
    var personal_sale = Number(document.getElementById("personal_discount").value);
    var corporate_sale = Number(document.getElementById("corporate_discount").value);
    var generalSale = personal_sale + corporate_sale;
    return cost*(1-generalSale/100);
}
