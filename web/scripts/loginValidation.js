function check() {
    var input = document.getElementById("login");
    var placeholderErrorText= input.getAttribute("title");
    if(!/^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$/.test(input.value)){
        input.setCustomValidity(placeholderErrorText);
    }else{
        input.setCustomValidity('');
    }
}