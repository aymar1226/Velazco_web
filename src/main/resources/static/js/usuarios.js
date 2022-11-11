
// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
    $('#usuarios').DataTable();
});
async function cargarUsuarios(){
      const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
      });
      const usuarios= await request.json();


      let listadoHtml = '';
      for(let usuario of usuarios){

      let botonEliminar = '<button class="borrar"><img class="trash" src="img/borrar.png" alt=""height="40px" width="40px" onclick="eliminarUsuario(' + usuario.idusuario + ')"></button>';
      let telefonoTexto = usuario.telefono==null ? '-' : usuario.telefono;

        let usuarioHtml='<tr><td>'+usuario.idusuario+'</td><td>'+usuario.rol+'</td><td>'+usuario.nombre+
        '</td><td>'+usuario.apellido+'</td><td>'+usuario.dni+'</td><td>'+usuario.correo+
        '</td><td>'+telefonoTexto+'</td><td>'+usuario.password+'</td><td>'+botonEliminar+'</td></tr>';
        listadoHtml += usuarioHtml;
      }

      document.querySelector('#usuarios tbody').outerHTML = listadoHtml;

}

async function eliminarUsuario(idusuario) {

    if(!confirm('Desea eliminar este usuario?')){
        return;
    }

    const request = await fetch('api/usuarios/'+idusuario, {
            method: 'DELETE',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
          });

    location.reload()

}