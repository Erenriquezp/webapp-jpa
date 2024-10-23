<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 8/16/2024
  Time: 8:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>

<h3>${title}</h3>
<c:choose>
    <c:when test="${cart.items.isEmpty()}">
        <div class="alert alert-warning"> There is no products in the cart shopping!</div>
    </c:when>
    <c:otherwise>
        <form name="formcart" action="${pageContext.request.contextPath}/cart/update" method="post">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Product Price</th>
                    <th>Product Quantity</th>
                    <th>Product Total</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cart.items}" var="item">
                <tr>
                    <td>${item.product.id}</td>
                    <td>${item.product.name}</td>
                    <td>${item.product.price}</td>
                    <td><input type="text" size="4" name="cant_${item.product.id}" value="${item.cantidad}"/></td>
                    <td>${item.importe}</td>
                    <td><input type="checkbox" value="${item.product.id}" name="deleteProducts"/></td>
                </tr>
                </c:forEach>
                <tr>
                    <td colspan="5" style="text-align: right">Total</td>
                    <td>${cart.total}</td>
                </tr>
            </table>
            <a class="btn btn-primary" href="javascript:document.formcart.submit();">Update</a>
        </form>
    </c:otherwise>
</c:choose>
<div class="my-2">
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/index.jsp">home</a>
    <a class="btn btn-success" href="${pageContext.request.contextPath}/products">shopping</a>
</div>
<jsp:include page="layout/footer.jsp"/>