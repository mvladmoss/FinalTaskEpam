function setCosts(){
    var element = document.getElementById("period_cost");
    var cost = element.value;
    var period = document.getElementById('period_cost').options[document.getElementById('period_cost').selectedIndex].text;
    document.getElementById("cost").value = cost;
    var finalCost = calculateFinalCost(cost);
    document.getElementById("final_cost").value = finalCost;
    var new_period = document.form.action + "&period=" +String(period) + "&cost=" + finalCost;
    document.form.action = new_period;
}

function calculateFinalCost(cost) {
    alert(document.getElementById("heading"));
    var personal_sale = Number(document.getElementById("personal_sale").value);
    var corporate_sale = Number(document.getElementById("corporate_sale").value);
    var generalSale = personal_sale + corporate_sale;
    return cost*(1-generalSale/100);
}
