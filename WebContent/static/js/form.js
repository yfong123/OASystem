
function makeInput( obj, json ){
	var readonly = (obj.readonly) ? obj.readonly : "";	
	var val = "";
	if( json[obj.name] ){
		val = json[obj.name];
	}
	console.log( "{makeInput} val = "+ val );
	return "<tr>\r\n"+
		   "<td>"+ obj.title +"</td>\r\n"+
		   "<td>\r\n"+
		   "<input type=\"text\" id=\""+ obj.name +"\""+
		   " name=\""+ obj.name +"\" "+ readonly +
		   " value=\""+ val +"\"/>\r\n</td>\r\n"+
		   "</tr>\r\n";
}

/*
 * 	{
 * 		title:"用户帐号",
 * 		name:"account",
 *      options:...,
 * 		val:json['account']
 *  }
 */ 
function makeSelect( obj, json ){
	var opts = obj.options;
	var val = json[ obj.name ];
	if(obj.name=='primaryRole'){
		val = json[ 'primaryRole' ].id;
	}
	console.log(obj.name+":"+val);
	var html = "<tr><td>"+ obj.title +"</td>";
	html += "<td><select id=\""+ obj.name +"\" name=\""+ obj.name +"\">";
	for( var i=0; i<opts.length; i++ ){
		var d = opts[i];
		var selected = ( val==d['val'] ) ? "selected" : "";
		html += "<option value=\""+ d['val'] +"\" "+ selected +">"+
			d['text'] +"</option>";
	}
	html += "</select></td></tr>";
	return html;
}


/*
 * {ps} 创建一个隐藏域
 * <input type="hidden" id="" ... />
 */
function makeHide( obj, json ){
	return "<input type=\"hidden\" id=\""+ obj.name 
		+"\" value=\""+ json[ obj.name ] +"\"/>\r\n";
}

function makeFile(){
	return "<input id=\"file\" type=\"file\" name=\"file\"/>\r\n";
}

function makeForm( inputs, json ){	   
	var html = "<form id=\"tbl\" method=\"POST\" enctype=\"multipart/form-data\">";
	for( var i=0; i<inputs.length; i++ ){
		var obj = inputs[ i ];
		switch( inputs[i].type ){
			case "text":
				html += makeInput( obj, json )+"</br></br>";
			break;
			case "select":
				html += makeSelect( obj, json );
			break;
			case "hide":
				html += makeHide( obj, json );
			break;
		}
	}
	html += "</form>";
	return html;
}

function makeTable( inputs, json ){	   
	var html = "<table id=\"tbl\">";
	for( var i=0; i<inputs.length; i++ ){
		var obj = inputs[ i ];
		switch( inputs[i].type ){
			case "text":
				html += makeInput( obj, json );
			break;
			case "select":
				html += makeSelect( obj, json );
			break;
			case "hide":
				html += makeHide( obj, json );
			break;
		}
	}
	html += "</table>";
	return html;
}









