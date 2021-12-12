window.onload = () => {
	let input = document.querySelector('#input1');

	input.oninput = function () {
		// let value = this.value.trim();
		let value = this.value.trim();
		let list = document.querySelectorAll('.producte');
		value
			? list.forEach(elem => {
				elem.innerText.search(value) == -1
					? elem.classList.add('hide')
					: elem.classList.remove('hide');
			})
			: list.forEach(elem => {
				elem.classList.remove('hide');
			});
	};
};
