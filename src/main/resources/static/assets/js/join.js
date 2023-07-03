/**
 * 
 */
//아이디 중복 체크
function idCheck() {
	 var user_id = $('#user_id').val();
	 $.ajax({
		 url:'join',
		 type: 'post',
		 data: {"user_id" : user_id},
		 success: function(cnt){
			 if(cnt == 1){
				 $(".id_unusable").css("display", "block");
				 $(".id_usable").css("display", "none");
			 } else {
				 $(".id_usable").css("display", "block");
				 $(".id_unusable").css("display", "none");
			 }
		 },
		 error: function(){
			 alert("에러");
		 }
		 
	 });
	 
 }
 
 //다 입력해야 버튼 활성화
 function fieldCheck(){
	var btn = document.getElementById('joinbtn');
	 
	//console.log("fieldCheck")
	var id = document.frm.user_id;
	var pwd = document.frm.password;
	var name = document.frm.name;
	var email = document.frm.email;
	var phone = document.frm.phone;
	var agree = document.frm.agree;
	
	var result = valueCheck(id);
	if(!result) return ;
	
	result = valueCheck(pwd);
	if(!result) return ;
	
	result = valueCheck(name);
	if(!result) return ;
	
	result = valueCheck(email);
	if(!result) return ;
	
	result = valueCheck(phone);
	if(!result) return ;
	
	result = valueCheck(agree);
	if(!result) return ;
	
	btn.disabled = false;	
	
 }


function valueCheck(element){
	var btn = document.getElementById('joinbtn');
	console.log("element.value : " + element.value);
	if(element.value == "" ){
		btn.disabled = true;	
		return false;
	}else if(element.value != ""){
		console.log("element value ");	
		return true;
	}
}

function phoneCheck(){
	
	var phoneCheck = document.getElementById('phonecheck');
	
	phone.value = phone.value
		 .match(/\d*/g).join('')
         .match(/(\d{0,3})(\d{0,4})(\d{0,4})/).slice(1).join('-')
         .replace(/-*$/g, '');
    
    console.log("phone = " + phone.value);

	if(phone.value.length != 13){
		phoneCheck.style.display = 'block';
	}else if(phone.value.length == 13){
		phoneCheck.style.display = 'none';
		
	}

	console.log(phone.value.length);
}








 

 