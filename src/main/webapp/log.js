function validate() {
    var rsl = true;
    if ($('#login').val() === '') {
        alert($('#login').attr('title'));
        rsl = false;
    }
    if ($('#password').val() === '') {
        alert($('#password').attr('title'));
        rsl = false;
    }
    return rsl;
}

function checkUser() {
    event.preventDefault();
    let name = $('#login').val();
    let pas = $('#password').val();
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/todo/auth.do',
        data: JSON.stringify({
            name: name,
            password: pas,
        }), dataType: 'text'
    }).done(function (data) {
        if (data !== "400 Bad Request") {
            localStorage.setItem("user", name);
            window.location.href = "http://localhost:8080/todo/index.html";
        } else {
            alert("You entered incorrect login or password");
        }
    })
}
