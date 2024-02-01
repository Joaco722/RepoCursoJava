// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#usuarios').DataTable();
  cargarUsuarios();
  actualizarEmailUsuario();
});

function actualizarEmailUsuario(){
    document.getElementById("txt-email-usuario").outerHTML = localStorage.email;
}
async function cargarUsuarios(){
  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers: getHeaders()
  });
  const usuarios = await request.json();


  let filas = '';
  for (let usuario of usuarios) {
    let telefonoTexto = usuario.telefono == null ? "-" : usuario.telefono
    let botonEliminar = '<a href="#" onclick="eliminarUsuario('+ usuario.id +')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';
    filas += `<tr><td>${usuario.id}</td><td>${usuario.nombre} ${usuario.apellido}</td><td>${usuario.email}</td><td>${telefonoTexto}</td><td>${botonEliminar}</td></tr>`;
  }

  document.querySelector('#usuarios tbody').innerHTML = filas;
}

function getHeaders(){
    return  {
                 'Accept': 'application/json',
                 'Content-Type': 'application/json',
                 'Authorization': localStorage.token
            }
}
async function eliminarUsuario(id){
if (!confirm('¿Desea eliminar al usuario?')){
    return;
}
  const request = await fetch('api/usuarios/' + id, {
    method: 'DELETE',
    headers: getHeaders()
  });

  location.reload()
}