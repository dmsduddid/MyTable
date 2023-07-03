/**
 * 
 */
 
function foldDaumPostcode() {
	var element_wrap = document.getElementById('wrap');
	element_wrap.style.display = 'none';
}

function serchAddress() {
	var element_wrap = document.getElementById('wrap');
    // 현재 scroll 위치를 저장해놓는다.
    var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    new daum.Postcode({
        oncomplete: function(data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("postcode").disabled = false;
            document.getElementById("address").disabled = false;
            document.getElementById("detailAddress").disabled = false;
            document.getElementById("detailAddress").focus();

            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            element_wrap.style.display = 'none';

            // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
            document.body.scrollTop = currentScroll;
        },
        // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
        onresize : function(size) {
            element_wrap.style.height = size.height+'0px';
        },
        width : '100%',
        height : '100%'
    }).embed(element_wrap);

    // iframe을 넣은 element를 보이게 한다.
    element_wrap.style.display = 'block';
}
function open_business_check_modal(){
	
	$("#modal").attr("style", "display:block");
	
}

function searchBusiness_no(){
	
	var business_no = String($('#modal_business_no').val());
	
	console.log(business_no);
	
	$.ajax({
		 url:'checkBusinessNo',
		 type: 'post',
		 data: {"business_no" : business_no},
		 success: function(cnt){
			 if(cnt == 1){
				 $("#result").text("이미 가입한 사업자번호입니다.");
				 $("#result").css("color", "red");
			 } else {
				 checkBusiness_no(business_no);
			 }
		 },
		 error: function(){
			 alert("에러");
		 }
		 
	 });
}

function checkBusiness_no(business_no){
	
	console.log(business_no);
	check = {}
	
	var data = { "b_no": [""+business_no+""] };
	var btn = document.getElementById('businessUpload');
	
	$.ajax({
		url:"https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=6qiS%2Fa7GmFW7BSUhBualVa4T00%2FDW3a2Xqb3OZr19J5cRUTci2B2WoURtt8Lw6nuMUpHw8MbGmGqVwD%2BH6mGZw%3D%3D",
		type:'post',
		data: JSON.stringify(data), 
		dataType: "JSON",
		contentType: "application/json",
	    accept: "application/json",
	    success: function(result) {
	      
		  	  console.log("dddd");
		  	  console.log(result);
		  	  
		  	  check.code = result.data[0].b_stt_cd;
              check.b_no = result.data[0].b_no;
              
              if(check.code == "01") {				  
			    $("#result").text("정상적인 사업자번호입니다.");
			    btn.disabled = false;

              }else if(check.code == "02" || check.code == "03") {				  
				$("#result").text("휴/폐업한 사업자번호입니다.");
				$("#result").css("color", "red");
                
              }else {				
				$("#result").text("등록되지 않은 사업자번호입니다.");
				$("#result").css("color", "red");
              }  
	    },
	    error: function(result) {
	      console.log(result.responseText); //responseText의 에러메세지 확인
	    }
	});
}

function registerBusiness_no(){
	var business_no = $('#modal_business_no').val();
	var modalPop = document.querySelector("#modalBg");
	
	document.getElementById("business_no").value = business_no;
	document.getElementById("business_no").disabled = false;
	modalPop.style.display = "none";	
}


//다 입력해야 버튼 활성화
 function fieldCheck(){
	var btn = document.getElementById('uploadBtn');
	 
	//console.log("fieldCheck")
	var businessNo = document.frm.business_no;
	var businessName = document.frm.business_name;
	var detailAddress = document.frm.detailAddress;
	
	var result = valueCheck(businessNo);
	if(!result) return ;
	
	result = valueCheck(businessName);
	if(!result) return ;
	
	result = valueCheck(detailAddress);
	if(!result) return ;

	
	btn.disabled = false;	
	
 }
 
 
 function valueCheck(element){
	var btn = document.getElementById('uploadBtn');
	console.log("element.value : " + element.value);
	if(element.value == "" ){
		btn.disabled = true;	
		return false;
	}else if(element.value != ""){
		console.log("element value ");	
		return true;
	}
}
