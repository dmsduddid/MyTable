/**
 * 
 */
function moveToStore(business_no,  business_name){
	console.log("moveToStore -- " + business_no +","+business_name);
	location.href = getContextPath() + "/mytable/storePage?business_name=" + business_name 																	  + "&business_no="+business_no
}

function moveToReserveList(){
	var url = document.location.href;
	var query = window.location.search;
	var param = new URLSearchParams(query);
	console.log(url);
	console.log(query);
	
	var business_no = parseInt(param.get('business_no'));
	var business_name = String(param.get('business_name'));
	
	console.log("moveToReserveList -- " + business_no +","+business_name);
	location.href = getContextPath() + "/mytable/storePage2?business_name=" + business_name 
																	  + "&business_no="+business_no
}

function minus_count(){
	
	var tmp = document.getElementById("people_num").value;
	if(tmp > 1){
		tmp--;
		document.getElementById("people_num").value = tmp;
	}else {
		swalWithBootstrapButtons.fire(
		      'error!',
		      '최소 선택 인원은 1명 이상입니다!',
		      'error'
		    )
	}

}

function plus_count(){
	
	var tmp = document.getElementById("people_num").value;
	if(tmp < 9){
		tmp++;
		document.getElementById("people_num").value = tmp;
	}else {
		swalWithBootstrapButtons.fire(
		      'error!',
		      '최대 선택 인원은 9명입니다!',
		      'error'
		    )
	}

}

function register(){
	
	var url = document.location.href;
	var query = window.location.search;
	var param = new URLSearchParams(query);
	console.log(url);
	console.log(query);
	
	var business_no = parseInt(param.get('business_no'));
	console.log(business_no);
	var user_id = String(document.getElementById('user_id').value);
	console.log(user_id);
	var people_num = parseInt($('#people_num').val());
	console.log(people_num);
	
	swalWithBootstrapButtons.fire({
		  title: '예약 등록',
		  text: user_id+'님, '+people_num+'명 등록하시겠습니까??',
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonText: '등록',
		  cancelButtonText: '취소',
		  reverseButtons: true
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url:'register',
					type: 'POST',
					data: {
						"customer_id" : user_id,
						"business_no" : business_no,
						"people_num" : people_num
					},
					success: function(registerResult) {
						if(registerResult == true){
							swalWithBootstrapButtons.fire(
						      '등록 완료',
						      '등록되었습니다!',
						      'success'
						    ).then(function(){
								location.href=url;
							})
							
						}else if(registerResult == false){
							swalWithBootstrapButtons.fire(
						      '등록 불가',
						      '이미 등록한 가게가 존재합니다. 취소하고 다시 예약하세요.',
						      'error'
						    )
						}

					}
				});

		}else if (
		    result.dismiss === Swal.DismissReason.cancel
		  ) {
		    swalWithBootstrapButtons.fire(
		      '취소 완료',
		      '취소되었습니다.',
		      'error'
		    )
		  }
		})
	
}

function sendKakaoMsg(customer_id){
	
	var url = document.location.href;
	
	console.log(customer_id);
	
	$.ajax({
		url:'sendMsg',
		type: 'POST',
		data: {"customer_id" : customer_id},
		success: function() {
			
		swalWithBootstrapButtons.fire(
					      '발송 완료',
					      '메세지를 발송했습니다!',
					      'success'
					    ).then(function(){
							location.href=url;
						})
			
		},
		error: function() {
			swalWithBootstrapButtons.fire(
		      '발송 실패',
		      '발송 실패했습니다.',
		      'error'
		    )
		}
	})
}

function logout(){
	
	sessionStorage.clear();
	
	window.location.href= getContextPath()+ "/mytable/login";
	
}

function cancel(){
	
	swalWithBootstrapButtons.fire({
		  title: '예약 삭제',
		  text: "정말 예약을 삭제할까요??",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonText: '삭제',
		  cancelButtonText: '취소',
		  reverseButtons: true
		}).then((result) => {
			if (result.isConfirmed) {
				swalWithBootstrapButtons.fire(
						      '삭제 완료',
						      '삭제되었습니다.',
						      'success'
						    ).then(function(){
								location.href=getContextPath()+ "/mytable/cancelWait";	
							})


		}else if (
		    result.dismiss === Swal.DismissReason.cancel
		  ) {
		    return false;
		  }
		})
	

}

function modify(){
	
	var user_id = document.getElementById("user_id").value;
	var password = document.getElementById("password").value;
	var phone = document.getElementById("phone").value;
         		 
	console.log(phone);
	console.log(user_id);
	console.log(password);
	
	$.ajax({
		url:'modify',
		type: 'POST',
		data: {"user_id" : user_id,
			   "password" :password,
			   "phone" : phone},
		success: function() {
			swalWithBootstrapButtons.fire(
						      '수정 완료',
						      '수정되었습니다!\n다시 로그인해주세요!',
						      'success'
						    ).then(function(){
								location.href= getContextPath()+ "/mytable/login";
							})
							

			
		},
		error: function() {
			alert("에러");
		}
	});
	
}
	 
