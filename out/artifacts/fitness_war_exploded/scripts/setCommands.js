function setCommands(address){
    var period = document.getElementById('period_cost').options[document.getElementById('period_cost').selectedIndex].text;
    var newAddress = address+period;
    alert(newAddress);
    return newAddress;
}