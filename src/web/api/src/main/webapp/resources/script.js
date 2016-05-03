
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
                alert("Учитель успешно добавлен!");
            },
            error: function (data) {
                alert("badly");
            }
        });
    }


    function sendStudentAdding() {
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
                alert("Учитель успешно добавлен!");
            },
            error: function (data) {
                alert("badly");
            }
        });
    }

    var x = new XMLHttpRequest();
    x.open("GET", "/student/all", true);
    x.onload = function (){
        var parsedStudents = JSON.parse(this.responseText);
        var studentsTable = document.getElementById('all-student-table');
        parsedStudents.forEach(function(item)  {
            var fullNameElement = document.createElement('td');
            fullNameElement.innerHTML = '<p>' + item['fullName'] + '</p>';
            var estimateElement = document.createElement('td');
            estimateElement.innerHTML = '<p>' + item['estimate'] + '</p>';
            var elementContainer = document.createElement('tr');
            elementContainer.appendChild(fullNameElement);
            elementContainer.appendChild(estimateElement);
            studentsTable.appendChild(elementContainer);
        });
    };
    x.send(null);

    var teacher = new XMLHttpRequest();
    teacher.open("GET", "/teacher/all", true);
    teacher.onload = function (){
        var parsedTeacher = JSON.parse(this.responseText);
        var teacherTable = document.getElementById('teachers');
        parsedTeacher.forEach(function(t)  {
            var Name = document.createElement('td');
            Name.innerHTML = '<p>' + t['fullName'] + '</p>';
            var elementContainer1 = document.createElement('tr');
            elementContainer1.appendChild(Name);
            teacherTable.appendChild(elementContainer1);
        });
    };
    teacher.send(null);
    