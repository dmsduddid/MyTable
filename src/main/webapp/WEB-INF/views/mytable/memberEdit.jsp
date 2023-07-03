<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>ModifyPage</title>
      <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">
      <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/assets/css/style.css">
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
	  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
</head>
<script type="text/javascript">
	contextPath = "";

	window.onload = function(){
	 	contextPath = '${pageContext.servletContext.contextPath}';
		
	}

	function getContextPath(){
	 	return contextPath;
	}
	
	const swalWithBootstrapButtons = Swal.mixin({
		  customClass: {
		    confirmButton: 'btn btn-success btn-success',
		    cancelButton: 'btn btn-danger btn-danger',
		    cancelButtonMargin: '10px' // 취소 버튼에 마진 적용
		  },
		  buttonsStyling: false
		})
	
	
</script>
  <style>
 		@import url('https://fonts.googleapis.com/css2?family=Arimo:wght@500&family=Ubuntu:ital,wght@1,300&display=swap');  
 		@import url(https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css);
	 	body{
			font-famliy:'NanumSquare',san-serif;
			font-size:18px;
			line-height:1;
			font-weight:400;
		}
		.btn{
			font-famliy:'NanumSquare',san-serif;
			font-size:20px;
			line-height:1;
			font-weight:700;
		}
  		.id_usable{
  			color:#9E9E9E;
			display: none;
  		}
  		.id_unusable{
	  		color:#D94925; 
			display: none;
  		}
  		.phonecheck{
  			color:#D94925; 
			display: none;
  		}

  </style>

  <body>
     <form name = "frm" class="form">
     	<div class="logo">
     		<a href="mytable">
     	  	<img src = "${pageContext.servletContext.contextPath }/images/mytablelogo.png">
     	  	</a>
     	</div>
        <br>          
	    <hr>     
	    <br>
	    <br>   

		<div class="textb" style="text-align:center">
	       	<c:forEach var ="memberList" items="${memberList}">
	           	<c:if test = "${memberList.division == 'C'}">
	           		<input type = "radio" style="width:20px;height:20px;border:1px;" name ="division"  value="C" onkeyup="fieldCheck()" checked/>  기본 회원&ensp;&ensp;
	           		<input type = "radio" style="width:20px;height:20px;border:1px;" name ="division"  value="O" onkeyup="fieldCheck()" disabled/>  사업자 회원
	           	</c:if>
	           	<c:if test = "${memberList.division == 'O'}">
	           		<input type = "radio" style="width:20px;height:20px;border:1px;" name ="division"  value="C" onkeyup="fieldCheck()" disabled/>  기본 회원&ensp;&ensp;
	           		<input type = "radio" style="width:20px;height:20px;border:1px;" name ="division"  value="O" onkeyup="fieldCheck()" checked/>  사업자 회원
	           	</c:if>
			</c:forEach>
	           	
             
        </div>
        <br>
        <div class="textb">
        	<c:forEach var ="memberList" items="${memberList}">
	           	<input type="text" required name = "user_id" id= "user_id" disabled value = "${memberList.user_id}">
			</c:forEach>

        </div>
        <div class="textb">
             <input type="password" required name = "password" id= "password" onkeyup="fieldCheck()">
             <div class="placeholder">Password</div>
        </div>
         
        <div class="textb">
        	<c:forEach var ="memberList" items="${memberList}">
	           	<input type="text" required name = "name" id= "name" disabled value = "${memberList.name}">
			</c:forEach>
        </div>
        
        <div class="textb">
	       	<c:forEach var ="memberList" items="${memberList}">
				<input type="text" required name = "email" id ="email" onkeyup="fieldCheck()" disabled value = "${memberList.email}">
			</c:forEach>

		</div>
        
        <div class="textb">
             <input type="text" required name = "phone" id = "phone" onkeyup="fieldCheck()" oninput= "phoneCheck()">
             <div class="placeholder">Phone</div>
        </div>
        <span class = "phonecheck" id ="phonecheck"> 11자리의 숫자로 입력해주세요.</span>
        <br>
       
        <div class ="agreeradio">	
           <input type = "radio" checked style="width:20px;height:20px;border:1px;" name ="agree" id ="agree" onkeyup="fieldCheck()"/><label>  알림톡 수신을 위한 개인정보 수집 동의</label>		
     	</div>
		<br>
		<br>
	 
        <div class="find-btn">
	   		<button type="button" class="btn" disabled name ="joinbtn" id ="joinbtn" onclick="modify()" >수정하기</button>
	 	</div>

     </form>
     
  <script type="text/javascript" src ="${pageContext.servletContext.contextPath}/assets/js/join.js"></script>
  <script type="text/javascript" src ="${pageContext.servletContext.contextPath }/assets/js/mytable.js"></script>
  <script
  		src="https://code.jquery.com/jquery-3.6.3.js"
  		integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
  		crossorigin="anonymous">
  </script>
  </body>
</html>