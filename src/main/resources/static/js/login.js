// Call the dataTables jQuery plugin


async function IniciarSesion(){
    let datos = {};

    datos.correo = document.getElementById('txtCorreo').value;
    datos.password = document.getElementById('txtPassword').value;

      const request = await fetch('api/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });

      const respuesta = await request.text();
      if(respuesta == "OK"){
        window.location.href = 'inicio.html'
      }else{
        alert("Las credenciales son incorrectas. Por favor intente nuevamente")
      }

}