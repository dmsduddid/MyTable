<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>STORE PAGE</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/assets/css/main.css" />
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/assets/css/noscript.css" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
</head>
<script type="text/javascript" src ="${pageContext.servletContext.contextPath }/assets/js/mytable.js"></script>
<script
 		src="https://code.jquery.com/jquery-3.6.3.js"
 		integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
 		crossorigin="anonymous">
</script>
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
    @import url(https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css);
 	body{
		font-family: 'NanumSquareNeo-Variable';
		font-size:16px;
		line-height:2;
		font-weight:400;
	}
	.btn{
		font-family: 'NanumSquareNeo-Variable';
		font-size:16px;
		line-height:1;
		font-weight:700;
	}
	li{
		display: inline-block;
		text-align: center;
	}
	.pagination{
		margin: 0 0 0 0;
		pading: 0 0 0 0;
	}

</style>
<body class="is-preload">
	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Header -->
		<header id="header">
			<div class="inner">
				<!-- Logo -->
				<a href="mytable" class="logo">
					<span class="symbol"><img src="${pageContext.servletContext.contextPath}/images/mytablelogo.png" alt="" /></span><span class="title"></span>
				</a>

				<!-- Nav -->
				<nav>
					<ul>
						<li><a href="#menu">Menu</a></li>
					</ul>
				</nav>
			</div>
		</header>

		<!-- Menu -->
		<nav id="menu" >
			<h2>MY TABLE</h2>
			
			안녕하세요<br>
			<%=session.getAttribute("user_id")%> 님<br>
			<c:set var = "memberDivision" value="${memberDivision}"/>
			<c:if test = "${memberDivision == 'O'}">
				<button class="logoutbtn" onclick="location.href ='/mytable/storeMypage'"><!-- onclick= "location.href='/mytable/logout'" --> MYSTORE 이동</button>
				<br>
				<br>
			</c:if>
			<button class="logoutbtn" onclick="logout();"><!-- onclick= "location.href='/mytable/logout'" --> 로그아웃</button>
			<button class="logoutbtn" onclick="location.href ='/mytable/memberEdit'">회원정보수정</button>
			<hr>
			
			
			<ul>
				<li><a href="javascript:show();" id = "show">예약 현황
					<c:set var = "registerYn" value="${registerYn}"/>
						<c:if test = "${registerYn==1}"> 
							<button class="logoutbtn" onclick="cancel();" style= "margin-left :20px"> 예약 취소</button> 
						</c:if>
						<c:if test = "${registerYn==0}">
							<button class="logoutbtn" onclick="cancel();" style= "margin-left :20px" disabled> 예약 취소</button>
						</c:if>
					</a>
				</li>
				<br>
				<span class = "register_list" name = "register_list" id = "register_list">
					${RegisterDTO.business_name}
					<br>예약 인원 : ${RegisterDTO.people_num} 명
					<br>${waiting_num}팀 남았습니다.
					<br>
					<br>
				</span>
			</ul>
		</nav>
		
	


		<!-- Main -->
		<div id="main">
			<div class="inner">
			<hr style="border:1.5px solid #FD9F28;">
			<header>
				<h2><%=request.getParameter("business_name")%> STORE</h2> 
			</header>

			<section class="tiles">
				<c:forEach var ="businessInfoList" items="${businessInfoList}">
					<article class="style1">
						<c:if test = "${storelist.img == null}">
							<img class="image" src="/images/storeUpload/${businessInfoList.business_info_img}" alt="" />
							<p>${businessInfoList.business_info}</p>
						</c:if>
					</article>
				</c:forEach>					
			</section>
			
			
			<hr style="border:1px solid #FD9F28;">	
			<header>
				<h2>대기 등록하기</h2> 
			</header>
			
			<div style = "width: 100%;">
				<div style = "margin: 0 auto; width: 350px; display:flex; align-items: center; ">
					<div style = "display:inline-block;">
						<i class='fas fa-chevron-down' style='font-size:48px; color:#FD9F28' name = "minusbtn" id = "minusbtn" onclick= "minus_count();"></i>
					</div>
					<div style = "display:inline-block; width: 100px;">
						<input class = "people_num" type = "text" id = "people_num" name = "people_num" value= "1" readonly = "readonly" style="text-align:center;"/>
					</div>
					<div style = "display:inline-block;">
						<i class='fas fa-chevron-up' style='font-size:48px;color:#FD9F28' name = "plusbtn" id = "plusbtn" onclick= "plus_count();"></i>
					</div>
					<div style = "display:inline-block;">
						<button class = "registerBtn" onclick="register();"> 등록하기 </button>
					</div>
				</div>

			</div>
	
			
				
			<input type="hidden" id="user_id" value="${user_id}">

    
			</div>
		</div>

	</div>
	<script>
		function show(){
			document.querySelector(".background").className = "background show";	
		}
	
		function close() {
		        document.querySelector(".background").className = "background";
		}
		document.querySelector("#show").addEventListener("click", show);
	    document.querySelector("#close").addEventListener("click", close);
	</script>
	<script src="${pageContext.servletContext.contextPath }/assets/js/jquery.min.js"></script>
	<script src="${pageContext.servletContext.contextPath }/assets/js/browser.min.js"></script>
	<script src="${pageContext.servletContext.contextPath }/assets/js/breakpoints.min.js"></script>
	<script src="${pageContext.servletContext.contextPath }/assets/js/util.js"></script>
	<script src="${pageContext.servletContext.contextPath }/assets/js/main.js"></script>

</body>
</html>