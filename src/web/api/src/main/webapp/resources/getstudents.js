var x = new XMLHttpRequest();
x.open("GET", "/student/all", true);  //Указываем адрес GET-запроса
x.onload = function (){ //Функция которая отправляет запрос на сервер для получения всех студентов
    var parsedStudents = JSON.parse(this.responseText); //указываем что
    var studentsTable = document.getElementById('all-student'); //получаем данные на странице по Id  - all-student
    parsedStudents.forEach(function(item)  { //запускаем цикл
        var fullNameElement = document.createElement('td'); //создаем элемент td для таблицы
        fullNameElement.innerHTML =  item['fullName'] ; //внедряем имя студента из БД
        var estimateElement = document.createElement('td'); //создаем элемент td для таблицы
        var estimateList = item.estimate; //создаем объект который равент баллам за основные задания текущего студента
        var exestimateList = item.extensionEstimate; //создаем объект который равент баллам за доп задания текущего студента
        var estimateValue = 0; //создаем объект для того чтобы оценки складывались одна с одной
        estimateList.forEach(function(item, i, arr) {
            estimateValue+=item.estimate; //добавляем оценку студента
        });

        exestimateList.forEach(function(item, i, arr) {
            estimateValue+=item.estimate; //добавляем оценку студента
        });

        estimateElement.innerHTML =  estimateValue; //внедряем оценку в таблицу
        var respectElement = document.createElement('td'); //создаем тег td для таблицы
        var respectList = item['dateList']; //создаем объект который принимает dateList
        var respectValue = 0;
        var notRespectValue = 0;
        respectList.forEach(function(item, i, arr) {
            respectValue+=item.respectCause+item.notRespectCause; //складываем пропуски по уваж и по неуваж причинам
        });
        respectElement.innerHTML =  respectValue ; //внедряем пропуски
        var elementContainer = document.createElement('tr'); //создаем тег
        //добавляем все в тег
        elementContainer.appendChild(fullNameElement);
        elementContainer.appendChild(estimateElement);
        elementContainer.appendChild(respectElement);
        studentsTable.appendChild(elementContainer);
    });
    $("#all-student-table").tablesorter(); //подключаем к таблице библиотеку для сортировки
};


x.send(null);