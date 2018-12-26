
var req;

function makeRequest(){
    req = newXMLHttpRequest();
    req.onreadystatechange = getReadyStateHandler(req, addExercises);
    req.open("POST", "Ajaxcontroller", true);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    var currentSerach = document.getElementById("searchType").value;
    req.send("searchArgument=" + currentSerach);
}

function addExercises(){
    if(req.readyState === 4 && req.status === 200){
        var parsed = JSON.parse(req.responseText);
        var newDiv = getDiv();
        document.form.innerHTML = "";
        document.form.appendChild(newDiv);
        for(var i = 0; i<parsed.exercises.length; i++){
            var toAdd = document.getElementById('reg').innerHTML;
            var programId = document.getElementById("programIdBlock");
            toAdd = toAdd.replace(/{exercise.id}/g,parsed.exercises[i].id);
            toAdd = toAdd.replace(/{exercise.name}/g,parsed.exercises[i].name);
            toAdd = toAdd.replace(/{program.id}/g,programId.value);
            var div = document.createElement('div');
            div.innerHTML = toAdd;
            document.form.innerHTML += (toAdd);
        }

    }
}

function getDiv(){
    var divText = document.getElementById('reg').innerHTML;
    var newDiv = document.createElement('div');
    newDiv.style.display = "none";
    newDiv.id='reg';
    newDiv.innerHTML = divText;
    return newDiv;
}