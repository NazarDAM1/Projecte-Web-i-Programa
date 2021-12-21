window.onload = () => {
	let input = document.querySelector('#input1');

	input.oninput = function () {
		// let value = this.value.trim();
		let value = this.value.trim();
		let list = document.querySelectorAll('.producte .descripcio');
		value
			? list.forEach(elem => {
				elem.innerText.search(value) == -1
					? elem.parentElement.classList.add('hide')
					: elem.parentElement.classList.remove('hide');
			})
			: list.forEach(elem => {
				elem.parentElement.classList.remove('hide');
			});
	};
};
