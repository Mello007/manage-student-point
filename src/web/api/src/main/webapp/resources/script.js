
function sendStudentAdding() {
    var name = $('#fullname_student').val();
    var estimate = $('#estimate_student').val();
    var requestJSONparametr = "{\"fullName\": \"" + name + "\", \"estimate\": \"" + estimate + "\"}";
    $.ajax({
        type: "POST",
        url: "/student/add",
        contentType: "application/json",
        dataType: 'json',
        data: requestJSONparametr,
        success: function (data) {
            alert("Студент успешно добавлен!");
        },
        error: function (data) {
            alert("Не удалось добавить студента!");
        }
    });
}

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
    

    var x = new XMLHttpRequest();
    x.open("GET", "/student/date", true);
    x.onload = function (){
        var parsedStudents = JSON.parse(this.responseText);
        var studentsTable = document.getElementById('all-student-table');
        parsedStudents.forEach(function(item)  {
            var fullNameElement = document.createElement('td');
            fullNameElement.innerHTML = '<p>' + item['fullName'] + '</p>';
            var estimateElement = document.createElement('td');
            estimateElement.innerHTML = '<input type="text" value=' + item[''] + '</input>';
            var elementContainer = document.createElement('tr');
            var respectElement = document.createElement('td');
            respectElement.innerHTML = '<input type="text" value=' + item[''] + '</input>';
            var not_respectElement = document.createElement('td');
            not_respectElement.innerHTML = '<input type="text" value=' + item[''] + '</input>';
            elementContainer.appendChild(fullNameElement);
            elementContainer.appendChild(estimateElement);
            elementContainer.appendChild(respectElement);
            elementContainer.appendChild(not_respectElement);
            studentsTable.appendChild(elementContainer);
        });
    };
    if (curent_date === undefined) {
        x.send(new Date);
    } else x.send(curent_date);

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
    
    
    