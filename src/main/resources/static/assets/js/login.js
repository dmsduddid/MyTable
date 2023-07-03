/**
 * 
 */

 
 function fieldCheck(){
	var btn = document.getElementById('loginbtn');
	 
	//console.log("fieldCheck")
	var user_id = document.frm.user_id;
	var password = document.frm.password;

	var result = valueCheck(user_id);
	if(!result) return ;
	
	result = valueCheck(password);
	if(!result) return ;
	
		
	btn.disabled = false;	
	
 }


function valueCheck(element){
	var btn = document.getElementById('loginbtn');
	console.log("element.value : " + element.value);
	if(element.value == "" ){
		btn.disabled = true;	
		return false;
	}else if(element.value != ""){
		console.log("element value ");	
		return true;
	}
}

function doKakaoLogin(){
	const url = 'https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=d067c0b24f657bdcf9a8372ba1af258f&redirect_uri=http://localhost:9007/mytable/kakao';
	window.location.href=url;
}










 

 