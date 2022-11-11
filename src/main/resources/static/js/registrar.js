// Call the dataTables jQuery plugin
$(document).ready(function() {
    // on ready
});


async function registrarUsuario(){
    let datos = {};

    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
    datos.dni = document.getElementById('txtDni').value;
    datos.telefono = document.getElementById('txtTelefono').value;
    datos.correo = document.getElementById('txtCorreo').value;
    datos.password = document.getElementById('txtPassword').value;

    let repetirPassword = document.getElementById('txtRepetirPassword').value;

    if(repetirPassword !=datos.password){
        alert('La contraseña que escribiste es diferente')
        return;
    }
    else{
        window.location.href = 'index.html'
    }

      const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
}