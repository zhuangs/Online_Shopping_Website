function validationForm(){
	var un = document.loginform.userName.value;
        var pw = document.loginform.password.value;
        var username = "jenny"; 
        var password = "jenny";
        if ((un == username) && (pw == password)) {
            return true;
        }
        else {
            alert ("Login was unsuccessful, please check your username and password");
            return false;
        }
}