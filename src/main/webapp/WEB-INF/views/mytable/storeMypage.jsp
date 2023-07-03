<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<!--
	Editorial by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
	<title>StorePage</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/storePage/assets/css/main.css" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
</head>
<script>
 
 
</script>

<body class="is-preload">
	

	<!-- Wrapper -->
		<div id="wrapper">

			<!-- Main -->
				<div id="main">
					<div class="inner">

						<!-- Header -->
							<header id="header">
								<a href="mytable" class="logo">
									<span class="symbol"><img src="${pageContext.servletContext.contextPath }/images/mytablelogo.png" alt="" /></span><span class="title"></span>
								</a>
							</header>

						<!-- Banner -->

								<div >
									<header>
										<h2>${storelist.business_name}</h2>
										<br>
										<h2>예약현황</h2>
										<br>	
									</header>
								</div>
								 <div>
                          			<div style = "width: 100%; height: 500px; overflow: auto; ">
	                                	<table id = "registerTable">
		                                    <thead style="position: sticky; top:0; background-color: white;">
		                                        <tr class="title">
													<th>등록번호</th>
													<th>고객 ID</th>
													<th>접수 인원</th>
													<th>접수 시간</th>
													<th>입장하기</th>
												</tr>
											</thead>
											<tbody >													
												<c:forEach var ="registlist" items = "${list}">
													<tr>
														<td>${registlist.register_seq_no}</td>
														<td id = "${registlist.customer_id}" value = "${registlist.customer_id}">${registlist.customer_id}</td>
														<td>${registlist.people_num}</td>
														<td><fmt:formatDate value="${registlist.register_sysdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
														<td>
															<button class = "entering" id = "entering" name = "entering" onclick="sendKakaoMsg('${registlist.customer_id}');">대기중</button>
															<button class = "cancel" id= "cancel" name = "cancel" style = "display:none">미발송</button>
														</td>
													</tr>
												</c:forEach>

		                                    </tbody>
                               			</table>
                           			</div>
                       			</div>
					</div>
					<br>
					<br>
					<div class="inner">
						<div style="display:inline-block;">
							<header>
								<h2>STORE 관리</h2>	
							</header>
						</div>
						<div class = "storeUploadBtn" style="display:inline-block;">
							<button type="button" class="button" id = "modalShowButton">
							  스토어 등록하기
							</button>
						</div>
						<br>
						<br>
						<!-- 이미지 박스 -->
				        <div id="imgsBox">
				        	<c:forEach var ="storeImgASCList" items="${storeImgASCList}">
					            <div class="imgContent">
				            		<div class="imgBox">
					            			<button class="changeBtn" onclick="mainImg('${storeImgASCList.business_info_no}');">
					            				<c:if test = "${storeImgASCList.represent_yn == 0}">
												<h3 style="color: FD9F28" >☆</h3>
												</c:if>
												<c:if test = "${storeImgASCList.represent_yn == 1}">
												<h3 style="color: FD9F28;" >★</h3>
												</c:if>
											</button>
											<button class="changeBtn changeBtnClose" onclick="deleteInfo('${storeImgASCList.business_info_no}', '${storeImgASCList.represent_yn}');">
												<h3>X</h3>
											</button>
				                    	<img id = "changeImg" src = "/images/storeUpload/${storeImgASCList.business_info_img}" style="max-width: 100%; height: auto;" alt="" onclick="javascript:changeImg('${storeImgASCList.business_info_img}');">
				                	</div>
				                	<p>${storeImgASCList.business_info}</p>             
					            </div>
				            </c:forEach>

				        </div>
				        <br>
				        <br>
				        
				        <c:if test = "${storeListCnt >= 4}">
					        <div id="imgsBox">
								<c:forEach var ="storeImgDESCList" items="${storeImgDESCList}">
						            <div class="imgContent">
					            		<div class="imgBox">
						            		<button class="changeBtn" onclick="mainImg('${storeImgDESCList.business_info_no}');">
					            				<c:if test = "${storeImgDESCList.represent_yn == 0}">
												<h3 style="color: FD9F28">☆</h3>
												</c:if>
												<c:if test = "${storeImgDESCList.represent_yn == 1}">
												<h3 style="color: FD9F28">★</h3>
												</c:if>
											</button>
											<button class="changeBtn changeBtnClose" onclick="deleteInfo('${storeImgDESCList.business_info_no}','${storeImgDESCList.represent_yn}');">
												<h3>X</h3>
											</button>
					                    	<img id = "changeImg" src="/images/storeUpload/${storeImgDESCList.business_info_img}" style="max-width: 100%; height: auto;" alt="" onclick="javascript:changeImg('${storeImgDESCList.business_info_img}');">
					                	</div>
					                	<p>${storeImgDESCList.business_info}</p>             
						            </div>
					            </c:forEach>
					        </div>
				        </c:if>

				        <br>
				        <br>
				        <br>
				        <br>
					</div>					
				</div>

		</div>
		
		
		<!-- 등록모달창 -->
        <div id="modalBg">
            <div id="modalPop">
                <div class="modalContentBox">
                	<div style="display:inline-block;">
                    <h2>STORE 등록하기</h2>
                    </div>
                    <div id="modalCloseButton" style="display:inline-block;">
	                  <Button>취소</Button>
	                </div>
                    <form action="/mytable/uploadStoreMypage" method="post" class="modalForm" enctype="multipart/form-data">
                        <div>
                            <label for="aboutFile">파일첨부</label>
                            <input type="file" class = "file" id = "file" name = "file" accept = "${pageContext.servletContext.contextPath }/images/storeUpload/*" onchange = "loadFile(event)">
                            <br>
                            <div id = "image_container"></div>
                        </div>
                        <div>
                            <label for="aboutFile">설명</label>
                            <input type="text" id="business_info" name="business_info">
                        </div>
	                    <div id="modalUploadButton" style="display:inline-block;">
	                        <Button class="modalUploadBtn">UPLOAD</Button>
	                    </div>
                   </form>
	                   
                </div>
            </div>
        </div>
        
        <!-- 수정모달창 -->
        <div id="modifyModalBg">
            <div id="modifyModalPop">
                <div class="modifyModalContentBox">
                	<div style="display:inline-block;">
                    <h2>STORE 수정하기</h2>
                    </div>
                    <div id="modifyModalCloseButton" style="display:inline-block;">
	                  <Button>취소</Button>
	                </div>
                    <form action="/mytable/uploadStoreMypage" method="post" class="modalForm" enctype="multipart/form-data">
                        <div>
                            <label for="aboutFile">파일첨부</label>
                            <input type="file" class = "file" id = "file" name = "file" accept = "${pageContext.servletContext.contextPath }/images/storeUpload/*" onchange = "loadFile(event)">
                            <br>
                            <div id = "image_container"></div>
                        </div>
                        <div>
                            <label for="aboutFile">설명</label>
                            <input type="text" id="business_info" name="business_info">
                        </div>
	                    <div id="modifyModalUploadButton" style="display:inline-block;">
	                        <Button class="modalModifyBtn">수정하기</Button>
	                    </div>
                   </form>
	                   
                </div>
            </div>
        </div>
		

		<script type="text/javascript" src ="${pageContext.servletContext.contextPath }/assets/js/mytable.js"></script>
		<script type="text/javascript" src ="${pageContext.servletContext.contextPath }/assets/js/storeMypage.js"></script>
		<script
				src="https://code.jquery.com/jquery-3.6.3.js"
				integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
				crossorigin="anonymous">
		</script>
		<script type="text/javascript">

				var modalShowButton = document.querySelector("#modalShowButton");
		        var modalPop = document.querySelector("#modalBg");
		        var modifyModalPop = document.querySelector('#modifyModalPop');

		        var modalCloseButton = document.querySelector("#modalCloseButton");
		        
		        var modifyModalCloseButton = document.querySelector("#modifyModalCloseButton");

		        // 모달 열기
		        modalShowButton.addEventListener("click", function(){
		            modalPop.style.display = "block";
		        });

		        // 모달 닫기
		        modalCloseButton.addEventListener("click", function () {
		            modalPop.style.display = "none";
		        });
		        
		     	// 수정 모달 닫기
		        modalCloseButton.addEventListener("click", function () {
		            modifyModalPop.style.display = "none";
		        });
		     	
		     	
		        $(function(){
		        	$("#changeImg").click(function(){
		        		//사용하고자 하는 함수
		        		changeStoreImg(imgInfo);
		        		
		        	});
		        });	
		        
		        
		        $(function(){
		        	timer = setInterval(function(){
		        		
		        		console.log("여기 들어옴???111");
		        	
		        	 //$('.registerTable').html('');
		        	 /* $.ajax ({ 
		                url : "selectRegisterList",
		                type : "POST",
		                cache : false,
		                success : function (list) {
		                	str = '<TR>';
		                	$.each(list , function(i){
		                        str += '<TD>' + list[i].register_seq_no + '</TD>'+
		                        	   '<TD>' + list[i].customer_id + '</TD>'+
		                               '<TD>' + list[i].people_num + '</TD>'+
		                               '<TD>' + list[i].register_sysdate + '</TD>';
		                        str += '</TR>';
		                    });
		                	$('.registerTable').append(str);
		                },
		                error:function(){
		                	console.log("에러");
		                }

		            }); */
		            $('#registerTable').load(location.href+' #registerTable')

		            }, 1000);

		        });
				
		
		</script>

	<!-- Scripts -->
	
	<!-- 	<script src="assets/js/jquery.min.js"></script>
		<script src="assets/js/browser.min.js"></script>
		<script src="assets/js/breakpoints.min.js"></script>
		<script src="assets/js/util.js"></script>
		<script src="assets/js/main.js"></script> -->

	</body>
</html>