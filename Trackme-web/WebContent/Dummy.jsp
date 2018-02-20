<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="dummyApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.min.js"></script>
<script src="http://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.5.0/ui-bootstrap.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/ng-grid/2.0.11/ng-grid.min.js"></script>
<script src="script/person.js"></script>

<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/ng-grid/2.0.11/ng-grid.css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>Insert title here</title>
</head>
<body>

<br>

<div class="grid">
    <!-- Specify a JavaScript controller script that binds Javascript variables to the HTML.-->
    <div ng-controller="userInfoController as userInfo">
       <div ng-repeat="location in userInfo.locations">
       	<div>Latitud: {{location.latitud}}</div>
       	<div>Longitud: {{location.longitud}}</div>
       	<div>Altitud: {{location.altitud}}</div>
       	<div>Fecha: {{location.date}}</div>
       </div> 
    </div> 
</div>
        
        <!-- Binds the grid component to be displayed. -->
        <div class="gridStyle" ng-grid="gridOptions"></div>

        <!--  Bind the pagination component to be displayed. -->
        <pagination direction-links="true" boundary-links="true"
                    total-items="persons.totalResults" page="persons.currentPage" items-per-page="persons.pageSize"
                    on-select-page="refreshGrid(page)">
        </pagination>
    </div>
</div>

</body>
</html>