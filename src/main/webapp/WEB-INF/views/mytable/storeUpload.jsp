<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>StoreUpload_1</title>
      <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/assets/css/style.css">
  </head>
  <style>
 	@import url('https://fonts.googleapis.com/css2?family=Arimo:wght@500&family=Ubuntu:ital,wght@1,300&display=swap');
  </style>
  <style>
	#modalBg{
            margin: 0 auto;            
            width: 100%;
            height: 100%;
            padding: 10px;
            display: none;
            background-color: rgba(0, 0, 0, .5);
            position: fixed;
            top: 0;
            left: 0;
        }

    #modalPop{
        margin: 0 auto;            
        width: 500px;
        height: 600px;
        padding: 10px;            
        position: fixed;
        top: calc(100vh/2 - 250px);
        left: calc(100vw/2 - 250px);
        background-color: #fff;
    }

    #modalPop .modalContentBox{
    }

    #modalCloseButton{
        margin-top: 10px;
    }
	
  </style>
  
  <body>
      <form name = "frm" action="/mytable/uploadBusiness" class="form" method = "POST">
      	  <div class="logo">
      	  	<img src = "${pageContext.servletContext.contextPath }/images/mytablelogo.png">
      	  </div>
          <br> 
          <p class = "title">MY STORE 등록</p>   
          <br>     
		  <hr>     
		  <br>
		  <br>
		      

         <div class="texta">
              <input type="text" value = "${sessionScope.user_id}" id ="owner_id" name = "owner_id" disabled >
              <div class="placeholder"></div>
         </div>
         <div class="texta">
              <input type="text" id ="name" name ="name" value = "${sessionScope.name}" disabled>
              <div class="placeholder"></div>
         </div>
         <div class="textc">
		     <input type="text" required id ="business_no" name ="business_no" disabled>
		     <div class="placeholder_2" style = "display:inline-block;">사업자등록번호</div>
		     <div class="display" style = "display:inline-block;"><button type="button" class="storeBtn" id = "modalShowButton" onclick="open_business_check_modal()">조회</button></div>
         </div>
         <div class="textb">
              <input type="text" required name ="business_name" onkeyup="fieldCheck()">
              <div class="placeholder" id ="business_name">STORE NAME</div>
              <p style="margin-top:10px">* STORE NAME은 MY TABLE에 등록되는 상호명입니다.</p>
         </div>
         <div class="textc" >
              <input type="text" required id = "postcode" name = "postcode" disabled>
              <div class="placeholder_2" style = "display:inline-block;">우편번호</div>
              <div class="display" style = "display:inline-block;"><button type="button" class="storeBtn" onclick="serchAddress();">주소찾기</button></div>
         </div>
         <div id="wrap" style="display:none;border:1px solid;width:100%;height:100%;margin:5px 0;position:relative">
		 	<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
		 </div>
         <div class="textb">
              <input type="text" required id = "address" name = "address" disabled>
              <div class="placeholder">주소</div>
         </div>
         <div class="textb">
              <input type="text" disabled id = "detailAddress" name = "detailAddress" onkeyup="fieldCheck()">
              <div class="placeholder">상세 주소 입력</div>
         </div>
         
		 <br>
		 <br>
         <div class="find-btn">
		    <button type="submit" disabled class="btn" id="uploadBtn">등록하기</button>
		 </div>
		 
	 </form>
	 
		 
	 <!-- 모달창 -->
	 <div id = "modalBg">
	 	<div id="modalPop">
	 		<div class="modalContentBox">
				 <div class = "business_check_modal" id ="check_modal" name ="check_modal">
				 	<div style="display:inline-block;"><h2>사업자등록번호 조회</h2></div>
				 	<div id="modalCloseButton" style="display:inline-block;">
				 		<Button type="button" class="cancelBtn">취소</Button>
                     </div>
				 	 <br>
				 	 <br>
					 <div class="textb">
			              <input type="text" required id = "modal_business_no" name = "modal_business_no" >
			              <div class="placeholder">사업자등록번호</div>
			         </div>
			         <br>
					 <div class="textb">
			              <input type="text" required id = "modal_name" name = "modal_name" value = "${sessionScope.name}">
			              <div class="placeholder"  >대표자명</div><br><br>
			              <p>* 대표자명은 가입 시 입력한 이름과 동일해야 합니다.</p>
			         </div>
			         <br>
			         <div class="textb">
			              <input type="text" required id = "modal_date" name = "modal_date">
			              <div class="placeholder">개업일자</div>
			         </div>
			         <br>
			         <div>
			         	<span name = "result" id = "result"></span>
			         </div>
			         <br>
			         <br>
			         <div class="display">
			         	  <button type="button" class="btn_3" onclick="searchBusiness_no()">진위여부 확인</button>
			         	  <button type="button" id = "businessUpload" class="btn_3" disabled onclick="registerBusiness_no()">등록</button>
			         </div>

		         </div>
			</div>
		</div>
	 </div>
	 
	 
	 
	 <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> <!-- 다음 지도 api-->
	 <script type="text/javascript" src ="${pageContext.servletContext.contextPath }/assets/js/storeupload.js"></script>
	 <script
	  		src="https://code.jquery.com/jquery-3.6.3.js"
	  		integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
	  		crossorigin="anonymous">
	 </script>
	 <script type="text/javascript">

				var modalShowButton = document.querySelector("#modalShowButton");
		        var modalPop = document.querySelector("#modalBg");

		        var modalCloseButton = document.querySelector("#modalCloseButton");

		        // 모달 열기
		        modalShowButton.addEventListener("click", function(){
		            modalPop.style.display = "block";
		        });

		        // 모달 닫기
		        modalCloseButton.addEventListener("click", function () {
		            modalPop.style.display = "none";
		        });
				
		
	  </script>



  </body>
</html>