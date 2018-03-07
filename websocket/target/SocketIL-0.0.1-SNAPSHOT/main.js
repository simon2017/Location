var user;
var	psswd;
var serverAddress="ws://localhost:8080/SocketIl/test";
var socket = io.connect(serverAddress, { 'forceNew': true });


function login(e){
	user=$("#username").val();
	psswd=$("#password").val();
	alert(user+psswd);

	data={
		profile:user,
		password:psswd
	};
	socket.emit("requestLogin",data,function(response){ 
      // Regresa la funcion
       console.log( response ); 
     // Nos saldra en la consola de nuestro navegador "Hola Mundo"
    });
}




function render (data) {  
  var html = data.map(function(elem, index) {
    return(`<div>
              <strong>${elem.author}</strong>:
              <em>${elem.text}</em>
            </div>`);
  }).join(" ");

  document.getElementById('messages').innerHTML = html;
}

function addMessage(e) {  
  var message = {
    author: document.getElementById('username').value,
    text: document.getElementById('texto').value
  };

  socket.emit('new-message', message);
  return false;
}
