document.addEventListener("DOMContentLoaded", function() {
	var form = document.getElementById("cambiarclave");

	form.addEventListener("submit", function(event) {
		var isValid = true;

		// Validar el campo de contraseña
		var claveInput = document.querySelector("input[name='nuevaclave']");
		var claveError = document.getElementById("claveError");
		if (claveInput.value.trim() === "") {
			isValid = false;
			setInvalidInput(claveInput, claveError, "El campo Contraseña es obligatorio");
		} else {
			setValidInput(claveInput, claveError);
		}

		// Validar el campo de repetir contraseña
		var repetirClaveInput = document.querySelector("input[name='repetirnuevaclave']");
		var repetirClaveError = document.getElementById("repetirClaveError");
		if (repetirClaveInput.value.trim() === "") {
			isValid = false;
			setInvalidInput(repetirClaveInput, repetirClaveError, "El campo Repetir Contraseña es obligatorio");
		} else {
			setValidInput(repetirClaveInput, repetirClaveError);
		}

		// Validar que las contraseñas coincidan
		if (isValid) {
			if (claveInput.value !== repetirClaveInput.value) {
				isValid = false;
				setInvalidInput(claveInput, claveError, "Las contraseñas no coinciden");
				setInvalidInput(repetirClaveInput, repetirClaveError, "Las contraseñas no coinciden");
			} else {
				setValidInput(claveInput, claveError);
				setValidInput(repetirClaveInput, repetirClaveError);
			}
		}

		if (!isValid) {
			event.preventDefault();
		}
	});

	function setInvalidInput(input, errorElement, errorMessage) {
		input.style.border = "2px solid #ff073a";
		errorElement.innerHTML = errorMessage;
		errorElement.style.color = "#ff073a";
	}

	function setValidInput(input, errorElement) {
		input.style.border = "1px solid #0f0";
		errorElement.innerHTML = "";
	}
});