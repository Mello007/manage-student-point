var x = new XMLHttpRequest();
x.open("GET", "/student/all", true);
x.onload = function (){
    var parsedStudents = JSON.parse(this.responseText);
    var studentsTable = document.getElementById('all-student');
    parsedStudents.forEach(function(item)  {
        var fullNameElement = document.createElement('td');
        fullNameElement.innerHTML =  item['fullName'] ;
        var estimateElement = document.createElement('td');
        var estimateList = item.estimate;
        var exestimateList = item.extensionEstimate;
        var estimateValue = 0;
        estimateList.forEach(function(item, i, arr) {
            estimateValue+=item.estimate;
        });

        exestimateList.forEach(function(item, i, arr) {
            estimateValue+=item.estimate;
        });

        estimateElement.innerHTML =  estimateValue ;
        var respectElement = document.createElement('td');
        var respectList = item['dateList'];
        var respectValue = 0;
        var notRespectValue = 0;
        respectList.forEach(function(item, i, arr) {
            respectValue+=item.respectCause+item.notRespectCause;
        });
        respectElement.innerHTML =  respectValue ;
        var elementContainer = document.createElement('tr');
        elementContainer.appendChild(fullNameElement);
        elementContainer.appendChild(estimateElement);
        elementContainer.appendChild(respectElement);
        studentsTable.appendChild(elementContainer);
    });
    $("#all-student-table").tablesorter();
};


x.send(null);