<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<!-- CSS -->
<link rel="stylesheet" href="/resources/css/homeStyle.css">
<!-- jQuery -->
<!-- <script src="jquery-3.5.1.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- img -->  
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
  />
<title>Salle Main</title>
</head>
<body>
<!-- 
	<script type="text/javascript" src="home.js">
	</script>
 -->

	<div class="container">
		<input type="checkbox" id="categoryicon"> 	
		<label for="categoryicon"> 
			<span></span>
			<span></span> 
			<span></span>
		</label>
		<!-- 인접요소 선택자로 연계 -->
		<div class="hamburgerbar">
			<strong>카테고리</strong>
			<ul>
				<li><a href="<c:url value=""/>">디지털/가전</a></li>
				<li><a href="<c:url value=""/>">가구/인테리어</a></li>
				<li><a href="<c:url value=""/>">유아동/유아도서</a></li>
				<li><a href="<c:url value=""/>">생활/가공식품</a></li>
				<li><a href="<c:url value=""/>">스포츠/레저</a></li>
				<li><a href="<c:url value=""/>">여성잡화</a></li>
				<li><a href="<c:url value=""/>">여성의류</a></li>
				<li><a href="<c:url value=""/>">남성패션/잡화</a></li>
				<li><a href="<c:url value=""/>">게임/취미</a></li>
				<li><a href="<c:url value=""/>">뷰티/미용</a></li>
				<li><a href="<c:url value=""/>">반려동물식품</a></li>
				<li><a href="<c:url value=""/>">도서/티켓/음반</a></li>
				<li><a href="<c:url value=""/>">식물</a></li>
				<li><a href="<c:url value=""/>">기타 중고물품</a></li>
			</ul>
		</div>
		<div class="logo">
			<a href="<c:url value=""/>"> 
				<img alt="logo" src="/resources/img/Salle.png" width="80" height="30">
			</a>
		</div>
		<div class="searchbar">
			<input type="text" id="searchbar" placeholder="검색어를 입력하세요"
				maxlength="50" size="60">	
		</div>
		<div class="sell">
			<a href="<c:url value=""/>"> 
				판매하기
			</a>
		</div>
		<div class="shopinfo">
			<a href="<c:url value=""/>"> 
				내상점
			</a>
		</div>
		<c:choose>
			<c:when test="${member == null}">
				<div class="member">
					<a href="<c:url value="/login"/>"> 
						로그인/회원가입
					</a>
				</div>	
			</c:when>
			<c:otherwise>
				<div class="memberpostlogin">
						<button id="link_logininfo" >  
							${member.getNickName()}님
						</button>
				<ul id="link_logintoggle">
					<li>
						<a href="/logout">
							로그아웃
						</a>
					</li>
					<li>
						<a href="/login">
							내상점
						</a>
					</li>					
				</ul>
					<script>
							$("#link_logininfo").click(function() {
								$("#link_logintoggle").toggle();
							});
					</script>
				</div>
			</c:otherwise>
		</c:choose>
		</div>
	</div>

	

</body>
</html>