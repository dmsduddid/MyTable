<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>join_success</title>
      <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">
      <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/assets/css/style.css">
  </head>
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
  </style>
  <body>
      <form action="#" class="login-form">
      	  <div class="logo">
      	  	<img src = "${pageContext.servletContext.contextPath }/images/mytablelogo.png">
      	  </div>
          <br>          
		  <hr>     
		  <br>
		  <br>
		  <br>
		  <p class="success"> 회원가입 완료되었습니다. <br><br> 로그인하여 MY TABLE을 이용해 보세요.</p>  
	     
		 <br>
		 <br>
		 
         <div class="find-btn">
		    <button type="button" class="btn" id = "btn" onClick="location.href='login'">로그인</button>
		 </div>

      </form>

  </body>
</html>