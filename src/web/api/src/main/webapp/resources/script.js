
    function sendTeacherAdding() {
        var name = $('#fullName').val();
        var login = $('#login').val();
        var pass = $('#password').val();
        var requestJSONparametr = "{\"fullName\": \"" + name + "\", \"login\": \"" + login + "\", \"password\": \"" + pass + "\"}";
        $.ajax({
            type: "POST",
            url: "/teacher/add",
            contentType: "application/json",
            dataType: 'json',
            data: requestJSONparametr,
            success: function (data) {
                alert("hello world");
            },
            error: function (data) {
                alert("badly");
            }
        });
    }