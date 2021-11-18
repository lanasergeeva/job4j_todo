function validate() {
    let rsl = true;
    if ($('#login').val() === '') {
        alert($('#login').attr('title'));
        rsl = false;
    }
    if ($('#email').val() === '') {
        alert($('#email').attr('title'));
        rsl = false;
    }
    if ($('#password').val() === '') {
        alert($('#password').attr('title'));
        rsl = false;
    }
    return rsl;
}


function addUser() {
    event.preventDefault();
    let name = $('#login').val();
    let email = $('#email').val();
    let pas = $('#password').val();
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/todo/user.do',
        data: JSON.stringify({
            name: name,
            email: email,
            password: pas,
        }), dataType: 'text'
    }).done(function (data) {
        if (data === "200 OK") {
            window.location.href = "http://localhost:8080/todo/log.html";
        } else {
            alert("User exist");
            document.getElementById('login').value='';
            document.getElementById('email').value='';
            document.getElementById('password').value='';
        }
    })
}
