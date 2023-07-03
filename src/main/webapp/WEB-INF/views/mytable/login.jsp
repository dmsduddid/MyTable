<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>LoginPage</title>
      <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/assets/css/style.css">
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
	  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
  </head>
  <style>
	@import url("https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,700,900");
  </style>
  <script type="text/javascript" src ="${pageContext.servletContext.contextPath }/assets/js/login.js"></script>
  <script
  		src="https://code.jquery.com/jquery-3.6.3.js"
  		integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
  		crossorigin="anonymous">
  </script>
  <script>
	  const swalWithBootstrapButtons = Swal.mixin({
		  customClass: {
		    confirmButton: 'btn btn-success btn-custom',
		    cancelButton: 'btn btn-danger',
		    cancelButtonMargin: '10px', // 취소 버튼에 마진 적용
		  },
		  buttonsStyling: false
		})
  </script>

  <body>
      <form action="login" class="form" name ="frm" method="POST">
      	  <div class="logo">
      	  	<img src = "${pageContext.servletContext.contextPath }/images/mytablelogo.png">
      	  </div>
          <br>          
		  <hr>     
		  <br>
		  <br>
          <div class="textb">
              <input type="text" required name ="user_id" id ="user_id" onkeyup="fieldCheck()">
              <div class="placeholder">ID</div>
          </div>

          <div class="textb">
              <input type="password" required name ="password" id ="password" onkeyup="fieldCheck()">
              <div class="placeholder">Password</div>
              <div class="show-password fas fa-eye-slash"></div>
          </div>
		  
         <div class="find-btn">
		    <button type="submit" disabled class="btn" id="loginbtn">로그인</button>
		    <button type="button" class="btn" onClick="location.href='join'">회원가입</button>
		 </div>
		 
		 <div>
			<c:if test="${failMsg != null}">
					<script>
					swalWithBootstrapButtons.fire(
						      '로그인 실패',
						      '${failMsg}',
						      'error'
						    )
					/* swal.fire({title:'로그인 실패', text:'${failMsg}',type:'error'}) */</script>
					<%-- <p style="color:#FF0000;">${failMsg}</p> --%>
			</c:if>
		 </div>
		<br>
		<hr>
		<br>
		 <div class="snsloginbtn">
		 	<input type = "image" src="${pageContext.servletContext.contextPath }/images/kakaoLogin.png" alt = "Submit"  width = "90%" onclick="doKakaoLogin();"/>
      	 </div>
      </form>

  </body>
</html>