function setCurrentTrainDayInForm(day) {
    var length = document.form.action.length;
    document.form.action = document.form.action.substring(0, length - 1) + String(day);
}

function setExerciseProgram(exercise, program) {
    var repeats = document.getElementById("repeats"+exercise).value;
    var setNumber = document.getElementById("setNumber"+exercise).value;
    document.form.action = document.form.action + "&exerciseId=" + String(exercise) + "&programId=" + String(program) + "&setNumber=" + setNumber + "&repeats=" + repeats;
}