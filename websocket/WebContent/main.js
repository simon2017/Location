var serverAddress = "ws://localhost:8080/SocketIL/tester";
var ws;

window.onload = init;

function init() {

	if ('WebSocket' in window) {
		ws = new WebSocket(serverAddress);
	} else if ('MozWebSocket' in window) {
		ws = new MozWebSocket(serverAddress);
	} else {
		alert('Tu navegador no soporta WebSockets');
		return;
	}

	ws.onopen = openHandler;

	ws.onclose = function() {
		disconnect();
	};

	ws.onerror = function(event) {
	};
}

var openHandler = function() {
	ws.onmessage = messageHandler;
}

var messageHandler = function(event) {
	var message = event.data;

}

function sendOld() {
	var message = {
		message : this.myText
	};
	ws.send(JSON.stringify(message));
}

var app = angular.module("testApp", []);
var count = 0;
app.controller("InputController", function() {

	this.cajaTexto = "";
	this.myText = "";
	this.count = 0;

	this.addText = function() {
		this.cajaTexto = this.cajaTexto + this.myText;
	};

	this.send = function() {
		var message = {
			message : this.myText
		};
		this.count++;
		ws.send(JSON.stringify(message));
	}
});

//socket.onmessage = function(event) {
//	var scope = angular.element($("#inputDiv")).scope();
//	var message = JSON.parse(event.data);
//	scope.inputCtrl.count++;
//	scope.inputCtrl.cajaTexto = scope.inputCtrl.cajaTexto + message.message;
//	scope.$apply();
//}

window.onbeforeunload = function() {
	disconnect();
}
function disconnect() {
	if (ws != null) {
		ws.onclose = function() {
		}; // disable onclose handler first
		ws.close();
		ws = null;
	}
};
