
var req;
var block = "<div id=\"reg\">\n" +
    "                                    <input type=\"button\" class=\"button\" onclick=\"chang(${exercise.id})\" value=\"${exercise.name}\">\n" +
    "                                    <div id=\"informer${exercise.id}\" class=\"b-toggle\">\n" +
    "                                        <div class=\"exercise\">\n" +
    "                                            <div class=\"col-25\">\n" +
    "                                                <label for=\"setNumber\">Sets</label>\n" +
    "                                            </div>\n" +
    "                                            <div class=\"col-75\">\n" +
    "                                                <input id=\"setNumber${exercise.id}\"  type=\"text\">\n" +
    "                                            </div>\n" +
    "\n" +
    "                                            <div class=\"col-25\">\n" +
    "                                                <label for=\"setNumber\">Repeats</label>\n" +
    "                                            </div>\n" +
    "                                            <div class=\"col-75\">\n" +
    "                                                <input id=\"repeats${exercise.id}\"  type=\"text\">\n" +
    "                                            </div>\n" +
    "                                        </div>\n" +
    "                                        <input type=\"submit\" class=\"button\" onclick=\"setExerciseProgram('${exercise.id}','${program.id}')\" value=\"Add\">\n" +
    "                                    </div>\n" +
    "                                </div>\n" +
    "                                <br/>\n" +
    "                                <hr style=\"margin-left: -20px;width: 110%;\"/>";
function makeRequest(searchArgument){
    req = newXMLHttpRequest();
    req.onreadystatechange = getReadyStateHandler(req, addExercises);
    req.open("POST", "Ajaxcontroller", true);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send("searchArgument=" + searchArgument);
}

function addExercises(){
    if(req.readyState === 4 && req.status === 200){
        var parsed = JSON.parse(req.responseText);
        document.form.innerHTML = "";
        for(var i = 0; i<parsed.exercises.length; i++){
            var programId = document.getElementById("programIdBlock");
            var toAdd = block;
            toAdd = toAdd.replace(/\${exercise.id}/g,parsed.exercises[i].id);
            toAdd = toAdd.replace(/\${exercise.name}/g,parsed.exercises[i].name);
            toAdd = toAdd.replace(/\${program.id}/g,programId.value);
            document.form.innerHTML += toAdd;
        }

    }
}