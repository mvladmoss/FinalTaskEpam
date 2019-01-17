
var req;

function makeRequest(){
    alert("dsad000");
    req = newXMLHttpRequest();
    req.onreadystatechange = getReadyStateHandler(req, addExercises);
    req.open("POST", "Ajaxcontroller", true);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    var currentSerach = document.getElementById("searchType").value;
    alert(currentSerach);
    req.send("searchArgument=" + currentSerach);
}

function addExercises(){
    if(req.readyState === 4 && req.status === 200){
        var parsed = JSON.parse(req.responseText);
        var flexItem = getFlexItemBlock();
        var flexContainer = document.getElementById('flex-container');
        flexContainer.innerHTML="";
        flexContainer.appendChild(flexItem);
        for(var i = 0; i<parsed.exercises.length; i++){
            var toAdd = document.getElementById('flex-item').innerHTML;
            var programId = document.getElementById("programIdBlock");
            toAdd = toAdd.replace(/{exercise.id}/g,parsed.exercises[i].id);
            toAdd = toAdd.replace(/{exercise.name}/g,parsed.exercises[i].name);
            toAdd = toAdd.replace(/{image_url}/g,parsed.exercises[i].image);
            toAdd = toAdd.replace(/{description}/g,parsed.exercises[i].description);
            toAdd = toAdd.replace(/{program.id}/g,programId.value);
            var newFlexItem = document.createElement('div');
            newFlexItem.innerHTML = toAdd;
            newFlexItem.classList.add("flex-item-iter");
            flexContainer.appendChild(newFlexItem);
        }

    }
}

function getFlexItemBlock() {
    var divText = document.getElementById('flex-item').innerHTML;
    var newFlexItem = document.createElement('div');
    newFlexItem.style.display = "none";
    newFlexItem.id='flex-item';
    newFlexItem.classList.add("flex-item");
    newFlexItem.innerHTML = divText;
    return newFlexItem;
}
