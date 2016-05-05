
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

$(window).load(function () {
    fillStudentTable("");
});

    function fillStudentTable(urlT) {
                $.ajax({
            type: "GET",
            url: "/student/date"+urlT,
            contentType: "application/json",
            dataType: 'json',
            success: function (parsedStudents) {
                var studentsTable = document.getElementById('all-student-table');
                studentsTable.innerHTML="<tr><th>Имя и Фамилия студента</th><th>Количество баллов</th>" +
                    "<th>Количество пропусков по уважительной причине</th>" +
                    "<th>Количество пропусков по неуважительной причине</th></tr>"
                parsedStudents.forEach(function(item)  {
                    var reqDate = curent_date === undefined ? new Date : curent_date;
                    var fullNameElement = document.createElement('td');
                    fullNameElement.innerHTML = '<p>' + item['fullName'] + '</p>';
                    var estimateElement = document.createElement('td');
                    var estimateValue = item.estimate === null? "" : item.estimate.estimate;
                    var inputEstimateElement = document.createElement('input');
                    inputEstimateElement.setAttribute('type', 'text');
                    inputEstimateElement.setAttribute('value', estimateValue);
                    inputEstimateElement.onblur = function() {
                        sendEstimateAdding(item.studentId, this.value, reqDate);
                    };
                    estimateElement.appendChild(inputEstimateElement);
                    var elementContainer = document.createElement('tr');
                    var respectElement = document.createElement('td');
                    var respectValue = item.dateList === null? "" : item.dateList.respectCause;
                    var inputRespectElement = document.createElement('input');
                    inputRespectElement.setAttribute('type', 'text');
                    inputRespectElement.setAttribute('value', respectValue);
                    respectElement.appendChild(inputRespectElement);
                    inputRespectElement.onblur = function() {
                        sendRespectAdding(item.studentId, this.value, reqDate);
                    };
                    var notrespectElement = document.createElement('td');
                    var notRespectCause = item.dateList === null? "" : item.dateList.notRespectCause;
                    var inputNotRespectElement = document.createElement('input');
                    inputNotRespectElement.setAttribute('type', 'text');
                    inputNotRespectElement.setAttribute('value', notRespectCause);
                    notrespectElement.appendChild(inputNotRespectElement);
                    inputNotRespectElement.onblur = function() {
                        sendNotRespectAdding(item.studentId, this.value, reqDate);
                    };
                    elementContainer.appendChild(fullNameElement);
                    elementContainer.appendChild(estimateElement);
                    elementContainer.appendChild(respectElement);
                    elementContainer.appendChild(notrespectElement);
                    studentsTable.appendChild(elementContainer);
                });
            },
            error: function (data) {
                alert("badly");
            }
        });
    }

    function sendEstimateAdding(id, estimate, date) {
        var requestJSONparametr = "{\"id\": \"" + id + "\", \"estimate\": \"" + estimate + "\", \"date\": \"" + date + "\"}";
        $.ajax({
            type: "POST",
            url: "/estimatecontroller/add",
            contentType: "application/json",
            dataType: 'json',
            data: requestJSONparametr
        });
    }

    function sendRespectAdding(id, respect, date) {
        var requestJSONparametr = "{\"id\": \"" + id + "\", \"respect\": \"" + respect + "\", \"date\": \"" + date + "\"}";
        $.ajax({
            type: "POST",
            url: "/attendance/respect/add",
            contentType: "application/json",
            dataType: 'json',
            data: requestJSONparametr
        });
    }

function sendNotRespectAdding(id, respect, date) {
    var requestJSONparametr = "{\"id\": \"" + id + "\", \"notRespectCause\": \"" + respect + "\", \"date\": \"" + date + "\"}";
    $.ajax({
        type: "POST",
        url: "/attendance/notrespect/add",
        contentType: "application/json",
        dataType: 'json',
        data: requestJSONparametr
    });
}

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


    