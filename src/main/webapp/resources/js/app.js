$(function () {
    console.log("Test");
    var testJS = $("#testJS");
    testJS.on("click", function () {
        $(this).toggleClass("test");
    });

    var registerForm = $("#registerForm");
    registerForm.on("submit", function(event){
        var email = document.forms["registerForm"]["email"].value;
        console.log(email);
        var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if (pattern.test(email)==false) {
            alert("Invalid Email, please enter valid Email.");
            event.preventDefault();
        }
    });

    var loginField = $("#loginField");
    var loginAlertFlag = 0;
    var registerSubmitButton = $("#submitButton");
    loginField.on("change", function(event){
        $.ajax({
            url: "http://localhost:8080/Library/loginExists/" + loginField.val(),
            datatype: "json",
            crossDomain: true
        })
            .done(function(data){
                console.log(data);
                if (data==true) {
                    if (loginAlertFlag==0) {
                        var alert = $("<div id='loginAlert' class='alert alert-danger' role='alert'>Such login already exists.</div>");
                        loginAlertFlag = 1;
                        registerSubmitButton.after(alert);
                    }
                    registerSubmitButton.prop("disabled", true);
                } else {
                    registerSubmitButton.prop("disabled", false);
                    if (loginAlertFlag==1) {
                        $("#loginAlert").remove();
                        loginAlertFlag=0;
                    }
                    if (emailAlertFlag==0 && loginAlertFlag==0) {
                        registerSubmitButton.prop("disabled", false);
                    }
                }
            })
            .fail(function (xhl, error1, error2) {
                console.log("http://localhost:8080/Library/loginExists/" + loginField.val());
                console.log("Failed miserably");
                console.log(xhl);
                console.log(error1);
                console.log(error2);
            }
    )});

    var emailField = $("#emailField");
    var emailAlertFlag = 0;
    emailField.on("change", function(event){
        $.ajax({
            url: "http://localhost:8080/Library/emailExists/" + emailField.val().replaceAll(".", "$"),
            datatype: "json",
            crossDomain: true
        })
            .done(function(data){
                console.log(data);
                console.log(emailField.val().replaceAll(".", "$"));
                if (data==true) {
                    if (emailAlertFlag==0) {
                        var alert = $("<div id='emailAlert' class='alert alert-danger' role='alert'>Such email already exists.</div>");
                        emailAlertFlag = 1;
                        registerSubmitButton.after(alert);
                    }
                    registerSubmitButton.prop("disabled", true);
                } else {
                    if (emailAlertFlag==1) {
                        $("#emailAlert").remove();
                        emailAlertFlag=0;
                    }
                    if (emailAlertFlag==0 && loginAlertFlag==0) {
                        registerSubmitButton.prop("disabled", false);
                    }
                }
            })
            .fail(function (xhl, error1, error2) {
                console.log("http://localhost:8080/Library/emailExists/" + emailField.val().replaceAll(".", "$"));
                console.log("Failed miserably");
                console.log(emailField.val().replaceAll(".", "$"));
                console.log(xhl);
                console.log(error1);
                console.log(error2);
            }
    )});

});