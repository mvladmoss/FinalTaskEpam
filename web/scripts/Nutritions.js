function setNutrition(time, id) {
    var controllerAdress = document.form.action.match("(.*?\\&)");
    document.form.action = controllerAdress[0] + "nutrition_id=" + id + "&nutrition_time=" + time;
    document.getElementById('nutrition_description').innerHTML = document.getElementById(time).innerHTML;
}