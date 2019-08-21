<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="patterns/header.jsp"/>

<form action="controller" method="POST">
    <input type = "hidden" name = "command" value = "all_books" />

    <table class="table table-striped">
        <thead>

        <th>ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
        <th>Year</th>
        <th>Publisher</th>
        <th>Genre</th>

        </thead>
        <tbody>
        <c:forEach var="elem" items="${allBooks}">
            <tr>
                <td> ${elem.bookId} </td>
                <td> ${elem.title} </td>
                <td> ${elem.author} </td>
                <td> ${elem.price} </td>
                <td> ${elem.year} </td>
                <td> ${elem.publisher} </td>
                <td> ${elem.genre} </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</form>

<c:import url="patterns/footer.jsp"/>
