function checkInputExerciseData(inputId){
    var input = document.getElementById(inputId);
    if(!/^[\d]{1,2}$/.test(input.value) || input===''){
        var placeholderErrorText= input.getAttribute("title");
        input.setCustomValidity(placeholderErrorText);
    }else{
        input.setCustomValidity('');
    }
}