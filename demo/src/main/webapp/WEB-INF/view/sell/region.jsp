<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- .jsp <head> 통일시켜주기 위해서 주석처리(12/29)
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
-->

    <title>주소검색</title>

	<% 
	request.setCharacterEncoding("UTF-8"); //해당시스템의 인코딩타입이 UTF-8일 경우 
	//한글이 깨지는 경우 주석 제거 
	String inputYn = request.getParameter("inputYn");
	String roadFullAddr = request.getParameter("roadFullAddr"); 
	String pr_region = request.getParameter("pr_region"); 
	String roadAddrPart2 = request.getParameter("roadAddrPart2"); 
	String engAddr = request.getParameter("engAddr"); 
	String jibunAddr = request.getParameter("jibunAddr"); 
	String zipNo = request.getParameter("zipNo"); 
	String addrDetail = request.getParameter("addrDetail");
	String admCd = request.getParameter("admCd"); 
	String rnMgtSn = request.getParameter("rnMgtSn"); 
	String bdMgtSn = request.getParameter("bdMgtSn"); 
	String detBdNmList = request.getParameter("detBdNmList"); 
	//** 2017년 2월 추가제공 **/ 
	String bdNm = request.getParameter("bdNm"); 
	String bdKdcd = request.getParameter("bdKdcd"); 
	String siNm = request.getParameter("siNm"); 
	String sggNm = request.getParameter("sggNm"); 
	String emdNm = request.getParameter("emdNm"); 
	String liNm = request.getParameter("liNm"); 
	String rn = request.getParameter("rn"); 
	String udrtYn = request.getParameter("udrtYn"); 
	String buldMnnm = request.getParameter("buldMnnm"); 
	String buldSlno = request.getParameter("buldSlno"); 
	String mtYn = request.getParameter("mtYn"); 
	String lnbrMnnm = request.getParameter("lnbrMnnm"); 
	String lnbrSlno = request.getParameter("lnbrSlno"); 
	String emdNo = request.getParameter("emdNo"); 
	%>
</head>

	
<body onload="init();">
	
	<form id="form" name="form" method="post"> 
		<input type="hidden" id="confmKey" name="confmKey" value=""/> 
		<input type="hidden" id="returnUrl" name="returnUrl" value=""/> 
		<input type="hidden" id="resultType" name="resultType" value=""/> 
	</form>
    
    	<script language="javascript">
		function init() {
		var url = location.href;
		var confmKey = "devU01TX0FVVEgyMDIwMTIxNzE1NDEyOTExMDU2MzQ=";//승인키
		// resultType항목 추가(2016.10.06)
		var resultType = "4"; // 도로명주소 검색결과 화면 출력유형, 1 : 도로명, 2 : 도로명+지번+상세보기(관련지번, 관할주민센터), 3 : 도로명+상세보기(상세건물명), 4 : 도로명+지번+상세보기(관련지번, 관할주민센터, 상세건물명)
		var inputYn= "<%=inputYn%>";
		if(inputYn != "Y") {
			document.form.confmKey.value = confmKey;
			document.form.returnUrl.value = url;
			document.form.resultType.value = resultType; // resultType항목 추가(2016.10.06)
			document.form.action="https://www.juso.go.kr/addrlink/addrLinkUrl.do"; // 인터넷망
			document.form.submit();
		} else {
			/** API 서비스 제공항목 확대 (2017.02) **/
			//opener가 열리기 전 window, 즉 sell_region을 호출시키는구나 근데 sell을 호출해줘야 담을 수 있음!!!
			opener.jusoCallBack("<%=roadFullAddr%>","<%=pr_region%>","<%=addrDetail%>", "<%=roadAddrPart2%>","<%=zipNo%>");
			window.close();
		}
		
	}

	</script>
</body>
</html>