<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--<%@include file="layout/header.jsp"%>--%>
<jsp:include page="layout/header.jsp"/>

<h3>${title}</h3>
<c:if test="${username.present}">
    <%--  <div>Hello c:out value="${requestScope.username.get()}"/>, welcome</div>--%>
    <div class="alert alert-info">Hello ${requestScope.username.get()}, welcome</div>
    <p><a class="btn btn-primary my-2" href="<c:out value="${pageContext.request.contextPath}"/>/products/form">save
        [+]</a>
    </p>
</c:if>
<table class="table table-hover table-striped">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>type</th>
        <c:if test="${username.present}">
            <th>price</th>
            <th>add to cart</th>
            <th>edit</th>
            <th>delete</th>
        </c:if>
    </tr>
    <c:forEach items="${products}" var="p">
        <tr>
                <%--    <td><c:out value="${p.id}"/> </td>--%>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.category.name}</td>
            <c:if test="${username.present}">
                <td>${p.price}</td>
                <td><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/cart/add?id=${p.id}">add
                    to cart</a></td>
                <td><a class="btn btn-sm btn-success"
                       href="${pageContext.request.contextPath}/products/form?id=${p.id}">edit</a></td>
                <td><a class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?');"
                       href="${pageContext.request.contextPath}/products/delete?id=${p.id}">delete</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<p>${applicationScope.message}</p>
<p>${requestScope.message}</p>
<jsp:include page="layout/footer.jsp"/>