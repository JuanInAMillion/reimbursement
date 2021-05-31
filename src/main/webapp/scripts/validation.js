let formContainer = document.getElementById("formContainer");
let inputs = document.getElementsByTagName('input');
let hasNoMessage = true;

function validatePassword() {

	let userPass = inputs[1].value;

	if (userPass.length < 8) {
		if (hasNoMessage === true) {
			let form = document.getElementById('form');
			let errorMessage = document.createElement('p');
			errorMessage.innerText = "Password Must Be at Least 8 Characters";
			form.append(errorMessage);
			hasNoMessage = false;
		}
		if (event.cancelable) {
			event.preventDefault();
		}
	}
}

let button = document.querySelector('button');
button.addEventListener('click', validatePassword);