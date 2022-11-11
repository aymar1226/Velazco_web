$(document).ready(function() {
    cargarProductos();
    $('#productos').DataTable();
});
async function cargarProductos(){
      const request = await fetch('api/productos', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
      });
      const productos= await request.json();


      let listadoHtml = '';
      for(let producto of productos){

      let botonEliminar = '<button class="borrar"><img class="trash" src="img/borrar.png" alt=""height="40px" width="40px" onclick="eliminarProducto(' + producto.idproducto + ')"></button>';

        let productoHtml='<tr><td>'+producto.idproducto+'</td><td>'+producto.categoria+'</td><td>'+producto.nombre+
        '</td><td>'+producto.descripcion+'</td><td>'+producto.stock+'</td><td>'+producto.precio+
        '</td><td>Disponible</td><td>'+botonEliminar+'</td></tr>';
        listadoHtml += productoHtml;
      }

      document.querySelector('#productos tbody').outerHTML = listadoHtml;

}

async function eliminarProducto(idproducto) {

    if(!confirm('Desea eliminar este usuario?')){
        return;
    }

    const request = await fetch('api/usuarios/'+idproducto, {
            method: 'DELETE',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
          });

    location.reload()

}