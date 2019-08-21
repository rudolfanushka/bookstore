<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="properties.label"/>

<c:import url="jsp/patterns/header.jsp"/>

<a href="/controller?command=all_books">
    <fmt:message key="all_books"/>

    <c:import url="jsp/patterns/footer.jsp"/>

