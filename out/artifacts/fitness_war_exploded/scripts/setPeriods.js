

function setCurrentTrainDayInForm(day) {
    var length = document.form.action.length;
    var address = document.form.action.substring(0,length-1) + String(day);
    document.form.action = address;
}

function setExerciseProgram(exercise, program) {
    var repeats = document.getElementById("repeats"+exercise).value;
    var setNumber = document.getElementById("setNumber"+exercise).value;
    var address = document.form.action + "&exerciseId=" + String(exercise) + "&programId=" + String(program) + "&setNumber=" + setNumber + "&repeats=" + repeats;
    document.form.action = address;
}