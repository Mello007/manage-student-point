
    function sendTeacherAdding() {
        alert("hello word");
//        var name = document.getElementById('name');
//        var login = document.getElementById('login');
//        var pass = document.getElementById('password');
        // var requestJSONparametr = "{\"fullName\": \"" + name.value + "\", \"login\": \"" + login.value + "\", \"password\": \"" + pass.value + "\"}";
        var msg   = $('teacher-add').serialize();
        $.ajax({
            type: "POST",
            url: "/teacher/add",
            contentType: "application/json",
            dataType: 'json',
            data: msg,
            success: function (data) {
                alert("hello world");
            },
            error: function (data) {
                alert("badly");
            }
        });
    }