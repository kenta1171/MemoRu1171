/**
 *  確認ダイアログ用
 */
function checkExecute() {
	if (window.confirm("Memo Edit or Delete OK?")) {
		return true;
	}
	else {
		return false;
	}
};

function tableByTabulator(){
	var table = new Tabulator("#table", {
		columns:[
			{title:"ID"},
			{title:"Memo"},
			{title:"Create"},
			{title:"Update"},
			{title:"Operation"}
		]
	})
};