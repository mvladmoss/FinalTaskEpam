function check() {
    var input = document.getElementById("login");
    if(!/^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$/.test(input.value)){
        var placeholderErrorText= input.getAttribute("title");
        input.setCustomValidity(placeholderErrorText);
    }else{
        input.setCustomValidity('');
    }
}

function checkForEnterAnyData() {
    var inputLogin = document.getElementById("login");
    if(inputLogin.value===""){
        var placeholderErrorText= inputLogin.getAttribute("title");
        inputLogin.setCustomValidity(placeholderErrorText);
    }
    var inputPassword = document.getElementById("password");
    if(inputPassword.value===""){
        var passwordErrorText = inputPassword.getAttribute("title");
        inputPassword.setCustomValidity(passwordErrorText);
    }else{
        inputPassword.setCustomValidity('');
    }
}

function checkForRegistrationAnyData(){
    var loginRegisterInput = document.getElementById("loginRegister");
    if(loginRegisterInput.value===""){
        var loginRegisterTitle = loginRegisterInput.getAttribute("title");
        loginRegisterInput.setCustomValidity(loginRegisterTitle);
    }else{
        loginRegisterInput.setCustomValidity('');
    }

    var passwordRegisterInput = document.getElementById("passwordRegister");
    if(passwordRegisterInput.value===""){
        var passwordRegisterTitle = passwordRegisterInput.getAttribute("title");
        passwordRegisterInput.setCustomValidity(passwordRegisterTitle);
    }else{
        passwordRegisterInput.setCustomValidity('');
    }

    var nameRegisterInput = document.getElementById("nameRegister");
    if(nameRegisterInput.value===""){
        var nameRegisterTitle = nameRegisterInput.getAttribute("title");
        nameRegisterInput.setCustomValidity(nameRegisterTitle);
    }else{
        nameRegisterInput.setCustomValidity('');
    }

    var surnameRegisterInput = document.getElementById("surnameRegister");
    if(surnameRegisterInput.value===""){
        var surnameRegisterTitle = surnameRegisterInput.getAttribute("title");
        surnameRegisterInput.setCustomValidity(surnameRegisterTitle);
    }else{
        surnameRegisterInput.setCustomValidity('');
    }

}