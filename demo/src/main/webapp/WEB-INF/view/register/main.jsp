<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>회원가입</title>
</head>
<body>
    <form:form action="done" modelAttribute="member" method="post">
    <p>
        <label>
        이메일:
        <form:input path="email"/>
        </label>
    </p>

    <p>
        <label>
        비밀번호:
        <form:password path="password"/>
        </label>
    </p>

    <p>
        <label>
        비밀번호 확인:
        <form:password path="confirmPassword"/>
        </label>
    </p>

    <p>
        <label>
        이름:
        <form:input path="name"/>
        </label>
    </p>

    <p>
        <label>
        닉네임:
        <form:input path="nickName"/>
        </label>
    </p>

    <p>
        <label>
        휴대전화:
        <form:input path="phoneNum"/>
        </label>
    </p>

    <input type="submit" value="가입하기">
    </form:form>
</body>
</html>