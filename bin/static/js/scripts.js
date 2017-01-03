 /**
 * 
 */

function executaAjaxGet(urlChamada, divDestino, preExecute, posExecute) {
	$.ajax({
		type : "GET",
		url : urlChamada,
		beforeSend : preExecute,

		success : function(response) {
			// we have the response
			var x = document.getElementById(divDestino);
			x.innerHTML = response;

		},
		complete : posExecute,
		error : function(xhr) {
			alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
		}
	});

}

function executaAjaxPost(divDestino, formOrigem, preExecute, posExecute) {
	var data = new FormData(formOrigem[0]);
	
	$.ajax({
		type : 'POST',
		url : formOrigem.attr("action"),
		data : data,
		processData: false,
		contentType: false,
		beforeSend : preExecute,
		success : function(response) {
			document.getElementById(divDestino).innerHTML = response;
			$("#myModal").modal('hide');
			return false;
		},
		complete : posExecute,
		error : function(xhr) {
			alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
		}
	});
	return false;
}

function menuStatusOnClick() {
	executaAjaxGet('/status', 'page-wrapper', '', '');
}

function menuNovoProcessoOnClick() {
	executaAjaxGet('/seedstarter', 'page-wrapper', '', previnePadrao());
}


function menuAssuntoOnClick() {
	executaAjaxGet('/assunto', 'page-wrapper', '', '');
}

function menuCidadesOnClick() {
	executaAjaxGet('/data/cidade', 'page-wrapper', '', function(e) {
		$(selecaoEstado).on({
			change : function(e) {
				carregaListaCidades()
			}
		});
		carregaListaCidades();
	});
}

function carregaListaCidades() {
	var sel = document.getElementById('selecaoEstado');
	executaAjaxGet("/data/cidadeporidestado/" + sel.value, "listaCidades", "",
			"");
}

function exibeModalEditaCidade(valor) {
	executaAjaxGet("/data/cidade/" + valor, "corpoModal", "", $("#myModal")
			.modal());
}

function exibeModalEditaStatus(valor) {
	executaAjaxGet("/status/" + valor, "corpoModal", "", $("#myModal")
			.modal());
}

function exibeModalEditaAssunto(valor) {
	executaAjaxGet("/assunto/" + valor, "corpoModal", "", $("#myModal")
			.modal());
}

function exibeModalEditaEstado(valor) {
	executaAjaxGet("/data/estado/" + valor, "corpoModal", "", $("#myModal")
			.modal());
}

$body = $("body");

$(document).on({
	ajaxStart : function() {
		$body.addClass("loading");
	},
	ajaxStop : function() {
		$body.removeClass("loading");
	}
});

function previnePadrao(){
	$('form.usaAjax').on({click : function(event) {
	    // impede o comportamento default (ir para página "teste")
	    event.preventDefault();
	   // alert("Clicaram");
	}});	
};


function formSubmitClick(e){
	var name   = $(e).attr('name');
    if (typeof name == 'undefined') return;
    var value  = $(e).attr('value');
    var form  = $(e).parents('form').first();
    var input = $('<input type="hidden" class="temp-hidden" />').attr('name', name).attr('value', value);
    $(form).find('input.temp-hidden').remove();
    $(form).append(input);
    executaAjaxPost("page-wrapper", form, "", previnePadrao());
    return false;
};
