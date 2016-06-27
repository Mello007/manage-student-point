
function sendStudentAdding() { //функция которая добавляет студента
    var name = $('#fullname_student').val(); //получаем имя студента по id на странице
    var estimate = $('#estimate_student').val(); //получаем балл студента по id на странице
    var requestJSONparametr = "{\"fullName\": \"" + name + "\", \"estimate\": \"" + estimate + "\"}"; //создаем JSON
    $.ajax({ //запускаем запрос ajax
        type: "POST", //тип запроса POST
        url: "/student/add", //url куда будет отправлен запрос
        contentType: "application/json", //тип контента в запросе
        dataType: 'json', //тип контента
        data: requestJSONparametr,
        success: function (data) { //отправляем данные
            alert("Студент успешно добавлен!"); //сообщение при успешной отправке
        },
        error: function (data) {
            alert("Не удалось добавить студента! Вы ввели неправильные значения! Попробуйте еще раз"); //сообщение при неудачной отправке
        }
    });
}





function sendStudentDelete() {
    var name = $('#fullnamest').val();
    var requestJSONparametr = "{\"fullname\": \"" + name + "\"}";
    $.ajax({
        type: "POST",
        url: "/student/delete",
        contentType: "application/json",
        dataType: 'json',
        data: requestJSONparametr,
        success: function (data) {
            alert("Студент успешно удален!");
        },
        error: function (data) {
            alert("Не удалось удалить студента! Возможно, вы ввели неправильно имя либо ввели недопустимое значение имени");
        }
    });
}

function sendStudentDelete() {
    var name = $('#fullnamest').val();
    var requestJSONparametr = "{\"fullname\": \"" + name + "\"}";
    $.ajax({
        type: "POST",
        url: "/student/delete",
        contentType: "application/json",
        dataType: 'json',
        data: requestJSONparametr,
        success: function (data) {
            alert("Студент успешно удален!");
        },
        error: function (data) {
            alert("Не удалось удалить студента! Возможно, вы ввели неправильно имя либо ввели недопустимое значение имени");
        }
    });
}

function sendTeacherDelete() {
    var name = $('#teacherFullName').val();
    var requestJSONparametr = "{\"fullname\": \"" + name + "\"}";
    $.ajax({
        type: "POST",
        url: "/teacher/delete",
        contentType: "application/json",
        dataType: 'json',
        data: requestJSONparametr,
        success: function (data) {
            alert("Преподаватель успешно удален!");
        },
        error: function (data) {
            alert("Не удалось удалить преподавателя! Возможно, вы ввели неправильно имя либо ввели недопустимое значение имени");
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
                alert("Преподаватель успешно добавлен!");
            },
            error: function (data) {
                alert("Не удалось добавить преподавателя! Вы ввели неправильные значения! Попробуйте еще раз");
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
                var studentsTable = document.getElementById('all-student-table'); //ищем all-student-table
                //создаем таблицу
                studentsTable.innerHTML="<tr><th>Имя и Фамилия студента</th><th>Основные баллы</th>" +
                    "<th>Дополнительные баллы</th>" +
                    "<th>Количество пропусков по уважительной причине</th>" +
                    "<th>Количество пропусков по неуважительной причине</th></tr>"
                parsedStudents.forEach(function(item)  {
                    var reqDate = curent_date === undefined ? new Date : curent_date; //если дата не выбрана создаем новую дату
                    var fullNameElement = document.createElement('td');
                    fullNameElement.innerHTML = '<p>' + item['fullName'] + '</p>';

                    var estimateElement = document.createElement('td');
                    var estimateValue = item.estimate === null? "" : item.estimate.estimate;
                    var inputEstimateElement = document.createElement('input');
                    inputEstimateElement.setAttribute('type', 'text'); //указываем тип
                    inputEstimateElement.setAttribute('value', estimateValue);
                    inputEstimateElement.onblur = function() {
                        sendEstimateAdding(item.studentId, this.value, reqDate);//функция которая вызывается если мы кликаем за пределы полей балла
                    };
                    estimateElement.appendChild(inputEstimateElement);


                    var extEstimateElement = document.createElement('td');
                    var exEstimateValue = item.extEstimate === null? "" : item.extEstimate.estimate;
                    var exInputEstimateElement = document.createElement('input');
                    exInputEstimateElement.setAttribute('type', 'text');
                    exInputEstimateElement.setAttribute('value', exEstimateValue);
                    exInputEstimateElement.onblur = function() {
                        sendExEstimateAdding(item.studentId, this.value, reqDate); //добавляем балл за доп задания
                    };
                    extEstimateElement.appendChild(exInputEstimateElement);
                    
                    var elementContainer = document.createElement('tr');
                    var respectElement = document.createElement('td');
                    var respectValue = item.dateList === null? "" : item.dateList.respectCause;
                    var inputRespectElement = document.createElement('input');
                    inputRespectElement.setAttribute('type', 'text');
                    inputRespectElement.setAttribute('value', respectValue);
                    respectElement.appendChild(inputRespectElement);
                    inputRespectElement.onblur = function() {
                        sendRespectAdding(item.studentId, this.value, reqDate); //добавляем уважительные пропуски
                    };
                    var notrespectElement = document.createElement('td');
                    var notRespectCause = item.dateList === null? "" : item.dateList.notRespectCause;
                    var inputNotRespectElement = document.createElement('input');
                    inputNotRespectElement.setAttribute('type', 'text');
                    inputNotRespectElement.setAttribute('value', notRespectCause);
                    notrespectElement.appendChild(inputNotRespectElement);
                    inputNotRespectElement.onblur = function() {
                        sendNotRespectAdding(item.studentId, this.value, reqDate); //добавляем неуважительные пропуски
                    };
                    //добавляем все собранные данные в таблицу
                    elementContainer.appendChild(fullNameElement);
                    elementContainer.appendChild(estimateElement);
                    elementContainer.appendChild(extEstimateElement);
                    elementContainer.appendChild(respectElement);
                    elementContainer.appendChild(notrespectElement);
                    studentsTable.appendChild(elementContainer);
                });
            },
            error: function (data) {
                alert("Не удалось загрузить студентов!"); // выводи при ошибке
            }
        });
    }

    function sendEstimateAdding(id, estimate, date) { //функция которая отправляет запрос на добавления балл за осн задачи студенту
        var requestJSONparametr = "{\"id\": \"" + id + "\", \"estimate\": \"" + estimate + "\", \"date\": \"" + date + "\"}";
        $.ajax({
            type: "POST",
            url: "/estimatecontroller/add",
            contentType: "application/json",
            dataType: 'json',
            data: requestJSONparametr
        });
    }

function sendExEstimateAdding(id, estimate, date) {  //функция которая отправляет запрос на добавления балла за доп задачи студенту
    var requestJSONparametr = "{\"id\": \"" + id + "\", \"estimate\": \"" + estimate + "\", \"date\": \"" + date + "\"}";
    $.ajax({
        type: "POST",
        url: "/estimatecontroller/addext",
        contentType: "application/json",
        dataType: 'json',
        data: requestJSONparametr
    });
}
    function sendRespectAdding(id, respect, date) {  //функция которая отправляет запрос на добавления пропуска по уваж причине студенту
        var requestJSONparametr = "{\"id\": \"" + id + "\", \"respect\": \"" + respect + "\", \"date\": \"" + date + "\"}";
        $.ajax({
            type: "POST",
            url: "/attendance/respect/add",
            contentType: "application/json",
            dataType: 'json',
            data: requestJSONparametr
        });
    }

function sendNotRespectAdding(id, respect, date) {  //функция которая отправляет запрос на добавления пропуска по неуваж причине студенту
    var requestJSONparametr = "{\"id\": \"" + id + "\", \"notRespectCause\": \"" + respect + "\", \"date\": \"" + date + "\"}";
    $.ajax({
        type: "POST",
        url: "/attendance/notrespect/add",
        contentType: "application/json",
        dataType: 'json',
        data: requestJSONparametr
    });
}

    var teacher = new XMLHttpRequest(); //функция которая отображает всех преподавателей
    teacher.open("GET", "/teacher/all", true); //отправляем GET запрос по адресу /teacher/all
    teacher.onload = function (){ //запускаем функцию
        var parsedTeacher = JSON.parse(this.responseText); //указываем что это будет JSON
        var teacherTable = document.getElementById('teachers'); //ищем teachers таблицу
        parsedTeacher.forEach(function(t)  { //функция
            var Name = document.createElement('td'); //создаем тег для таблицы
            Name.innerHTML = '<p>' + t['fullName'] + '</p>'; //вставляем имя преподавателя
            var elementContainer1 = document.createElement('tr'); //тег для таблицы
            elementContainer1.appendChild(Name); //добавляем имя в тег tr
            teacherTable.appendChild(elementContainer1); //добавляем имя в таблицу
        });
    };
    teacher.send(null); //указываем что в ответ ничего не отправляем



