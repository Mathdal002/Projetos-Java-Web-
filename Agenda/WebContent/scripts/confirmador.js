/**
 * Confirmação de exclusão de um contato
 */

function confirmar(idcon){
	let resposta = confirm("confirmar a exclusão deste contato")
	if (resposta === true ){
		//teste [ alert(idcon) ]
		window.location.href = "delete?idcon=" + idcon
	}
}