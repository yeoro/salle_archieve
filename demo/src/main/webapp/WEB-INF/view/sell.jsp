<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>판매하기</title>
	<!-- CSS -->
	<link rel="stylesheet" href="/resources/css/sell.css">
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.js">
	</script>

</head>
<body>


    <form:form action="sellproduct/done" method="post" enctype="multipart/form-data">
    
    <section class="pr_img">
  		<p>	
    		<label for="img"><h2>상품 이미지</h2></label>
    	</p>
    	<div id="pr_img">
	    	<input type="file" id="img" name="pr_img_files" multiple="multiple"/>
	    
	    <%=request.getRealPath("/") %>
    	</div>
    </section>
    
    <section class="pr_title">
	    <p>
	    	<h2>제목</h2>
	   		<input type="text" name="pr_title" size="50">
	    </p>
    </section>

    <section class="pr_category">
	    <p>
	    	<h2>상품 카테고리</h2>
		    <select id="pr_category" name="pr_category" required>
		    	<option value="디지털/가전">디지털/가전</option>
		    	<option value="가구/인테리어">가구/인테리어</option>
		    	<option value="유아동/유아도서">유아동/유아도서</option>
		    	<option value="디지털/가전">디지털/가전</option>
			</select>
	    </p>
    </section>
    
    <section class="pr_region">
	    <p>
	    	<h2>거래지역</h2>
	    </p>
	    <p>
	    	<p>
	    		<!-- <form id="form" name="form" method="post"> -->
		    		<input type="button" onclick="goPopup();" value="주소검색">
		    </p>
					우편번호<input type="text" id="zipNo" name="zipNo" /><br>
				    전체주소 <input type="text" id="roadFullAddr" name="roadFullAddr" /><br>
				    도로명주소 <input type="text" id="roadAddrPart1" name="pr_region" /><br>
					상세주소<input type="text" id="addrDetail" name="addrDetail" /><br> 
					참고주소<input type="text" id="roadAddrPart2" name="roadAddrPart2" /><br>
				<!--</form> -->
	    </p>
    </section>
    
    <section class="pr_quality">
    	<p>
	    	<p>
		    	<h2>상품 상태</h2>
		    </p>	    	
	    		<input type="radio" id="pr_quality" name="pr_quality" value="상"/>
	    		<label for="pr_quality">상</label>
	    	
	    
	    		<input type="radio" id="pr_quality" name="pr_quality" value="중"/>
	    		<label for="pr_quality">중</label>
	    	
	    	
	    		<input type="radio" id="pr_quality" name="pr_quality" value="하"/>
	    		<label for="pr_quality">하</label>
    	</p>
    </section>
    
    <section class="pr_price">
	    <p>
	    	<h2>상품 가격</h2>
	   		<input type="text" id="pr_price_view" onkeyup="priceCommas(this.value)" name="pr_price_view"/>원
	   		<input type="hidden" id="pr_price" name="pr_price"/>
	    </p>
    </section>

    <section class="pr_detail">
	    <p>
	    	<h2>상품 설명</h2>
	   		<textarea id="pr_detail" placeholder="상품 설명을 입력하세요. 최대 500자" maxlength="1000" name="pr_detail"></textarea>
	    </p>
    </section>
    
	<input type="submit" value="등록하기"/> 
    </form:form>
    
    <!-- Javascript -->
    <!-- <script type="text/javascript" scr="/resources/static/js/sell.js"></script> -->
    <script>
    
    var img_count = 0;
	    
	//input 파일첨부 버튼 클릭하면 실행되는 change 메서드
	$("#img").change(function fileadd() {
		var reader = new FileReader;
	//이미지 파일 정보와 화면출력을 위해 <img> 태그를 변수로 만듦
		var str = "<img id='img_"+(img_count)+"' src='' name='pr_img'/>";
	//파일 경로에 넣기 위해 String으로 변환시켜줌
		var img_count_string = img_count.toString();
		
	//jQuery append 메서드를 사용해 <div id="pr_img"> 안에 <img> 태그 변수를 추가해줌
		$("#pr_img").append(str);
	//<img src=""> 사용자가 업로드한 이미지 파일 경로를 src로 저장해줌(data.target.result) 
		
	//onload는 파일이 업로드 완료된 시점에 function을 발생시키는 메서드
		reader.onload = function(data) {
	//태그 안의 속성을 입력할 수 있는 jQuery attr 메서드를 사용 
			$('#img_' + img_count_string).attr('src', data.target.result).width(150);
		};
		
	//화면에 이미지를 출력해주는 FileReader 객체 인스턴스 reader.readAsDataURL();
	//this.files는 <input type="file">을 통해 업로드한 파일의 정보를 저장하고 있는 배열이다.
	//첨부하기 1회당 file 하나만 업로드해서 <img_0,1,2...>에 각각의 파일들을
	//할당시켜줄 것이기 때문에 files[0]로 index 고정
		reader.readAsDataURL(this.files[0]);
		
			img_count++;
    });
    </script>
    
    <script language="javascript">
		function goPopup() {
			
			var pop = window.open("/sell_region","pop","width=570, height=420, scrollbars=yes, resizable=yes");
		} 	
		//주소입력창
		function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2, zipNo){ 
			// 2017년 2월 제공항목이 추가되었습니다. 원하시는 항목을 추가하여 사용하시면 됩니다. 
			document.form.roadFullAddr.value = roadFullAddr; 
			document.form.roadAddrPart1.value = roadAddrPart1; 
			document.form.roadAddrPart2.value = roadAddrPart2; 
			documentform.addrDetail.value = addrDetail; 
			document.form.zipNo.value = zipNo; 
		};
    </script>
    
    <script>
	    function priceCommas(x) {
	    	//입력값 가공
	    	
	    	x = x.replace(/[^0-9]/ig,'');
	    	x = x.replace(/,/ig,'');
	    	
	    	nonCommaX= parseInt(x);
	    	commaX = x.replace(/\B(?=(\d{3})+(?!\d))/g,',');
	    	
	    	$('#pr_price_view').val(commaX);
	    	$('#pr_price').val(nonCommaX);
	    }
    </script>

</body>
</html>