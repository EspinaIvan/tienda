//FUNCION PARA SUMAR O RESTAR LA CANTIDAD DEL CARRO

function sumarCantidad(id) {
  var cantidadInput = document.getElementById(`cantidad${id}`);
  var valor = parseInt(cantidadInput.value);
  
  if (!isNaN(valor)) {
    valor += 1;
    cantidadInput.value = valor;
  }
}

function quitarCantidad(id) {
  var cantidadInput = document.getElementById(`cantidad${id}`);
  var valor = parseInt(cantidadInput.value);
  
  if (!isNaN(valor) && valor > 0) {
    valor -= 1;
    cantidadInput.value = valor;
  }
}

function mostrarCarritoPopup() {
    var carritoPopup = document.getElementById("carrito-popup");
    carritoPopup.style.display = "block";
    // Aquí puedes cargar dinámicamente el contenido del carrito en el popup
}

function ocultarCarritoPopup() {
    var carritoPopup = document.getElementById("carrito-popup");
    carritoPopup.style.display = "none";
}