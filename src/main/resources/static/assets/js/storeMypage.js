/**
 * 
 */


var contextPath = "";
				
window.onload = function(){
 	contextPath = '${pageContext.servletContext.contextPath}';	
}

function getContextPath(){
	return contextPath;
}

function loadFile(event){
	
	var reader = new FileReader();
	
	reader.onload = function(event) {
		
		  $('#image_container').empty();
		
		  $('#image_container').val('');
          var img = document.createElement("img");
          
          
          img.setAttribute("src", event.target.result);
          document.querySelector("div#image_container").appendChild(img);
        };

        reader.readAsDataURL(event.target.files[0]);
}

function mainImg(imgNo){
	
	const swalWithBootstrapButtons = Swal.mixin({
	  customClass: {
	    confirmButton: 'btn btn-success',
	    cancelButton: 'btn btn-danger',
	    cancelButtonMargin: '10px' // 취소 버튼에 마진 적용
	  },
	  buttonsStyling: false
	})
	
	swalWithBootstrapButtons.fire({
		  title: '메인 이미지 변경',
		  text: "메인 화면에 업로드되는 이미지이니 신중하게 결정해주세요!",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonText: '변경',
		  cancelButtonText: '취소',
		  reverseButtons: true
		}).then((result) => {
			if (result.isConfirmed) {
				var business_info_no = imgNo;
				console.log(business_info_no);
				$.ajax({
			
					url: "/mytable/changeMainImg",
					type: "post",
					data:{"business_info_no" : business_info_no},
					success: function(){
						swalWithBootstrapButtons.fire(
						      '변경 완료',
						      '변경되었습니다.',
						      'success'
						    ).then(function(){
								location.href='storeMypage';
							})
						
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
	

	
	//var btn = document.querySelector('.changeBtn');
	//var mainImg = btn.querySelector('h4');
/*	var business_info_no = imgNo;
	console.log(business_info_no);
	$.ajax({

		url: "/mytable/changeMainImg",
		type: "post",
		data:{"business_info_no" : business_info_no},
		success: function(){
			
			
		}
	});
	
	window.location.href='storeMypage';
	*/
	
}

function deleteInfo(imgNo, repYn){
	
	const swalWithBootstrapButtons = Swal.mixin({
	  customClass: {
	    confirmButton: 'btn btn-success',
	    cancelButton: 'btn btn-danger',
	    cancelButtonMargin: '10px' // 취소 버튼에 마진 적용
	  },
	  buttonsStyling: false
	})
	
	swalWithBootstrapButtons.fire({
		  title: '정말 삭제할까요?',
		  text: "삭제 후 복구할 수 없습니다",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonText: '삭제',
		  cancelButtonText: '취소',
		  reverseButtons: true
		}).then((result) => {
			if (result.isConfirmed) {
				if(repYn == 1){
						swalWithBootstrapButtons.fire(
							'삭제 불가',
							'메인 이미지는 삭제할 수 없습니다.',
							'error'
					)
				}else{
					
					var business_info_no = imgNo;
					console.log(business_info_no);
					$.ajax({
			
						url: "/mytable/deleteInfo",
						type: "post",
						data:{"business_info_no" : business_info_no},
						success: function(){
							swalWithBootstrapButtons.fire(
						       '삭제 완료',
						       '삭제되었습니다!',
						       'success'
				    		).then(function(){
								location.href='storeMypage';
							})	
						}
					});
					
			}
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

function changeStoreImg(imgInfo){
	console.log(imgInfo);
	
	var modifyModalPop = document.querySelector('#modifyModalPop');
	
	modifyModalPop.style.display = "block";
	
}


