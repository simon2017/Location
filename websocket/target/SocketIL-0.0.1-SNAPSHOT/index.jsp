<!doctype html>
<html ng-app="testApp">
  <head>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
	<script src="http://code.jquery.com/jquery-2.2.4.js" crossorigin="anonymous"></script>
	<script type="text/javascript" src="main.js"></script>
  </head>
  <body>
  <button onclick="sendOld()">Enviar</button>
    <div id="inputDiv" ng-controller="InputController as inputCtrl">
      <div>
      	<label>Texto:</label>
      	<input type="text" ng-model="inputCtrl.myText" placeholder="Enter a text here">
      	<button ng-click="inputCtrl.send()">Enviar</button>
      </div>
      
      <p>Hello {{inputCtrl.cajaTexto}}!</p>
      <p>Recibidos {{inputCtrl.count}}</p>
    </div>
  </body>
</html>