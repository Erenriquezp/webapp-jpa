<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 8/26/2024
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.edd.apiservlet.webapp.headers.models.*" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%--%>
<%--    List<Category> categories = (List<Category>) request.getAttribute("categories");--%>
<%--    Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");--%>
<%--    Product product = (Product) request.getAttribute("product");--%>
<%--    String date = product.getCreatedDate() != null ?--%>
<%--            product.getCreatedDate().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";--%>
<%--%>--%>
<jsp:include page="layout/header.jsp"/>

<h3>${title}</h3>
<form action="${pageContext.request.contextPath}/products/form" method="post">

    <div class="row mb-2">
        <label for="name">Name</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" name="name" id="name" value="${requestScope.product.name}">
            <!-- This is the same as the above line -->
        </div>
        <c:if test="${errors != null && errors.containsKey('name')}">
            <div style="color:red">${errors.name}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="price">Price</label>
        <div class="col-sm-4">
            <input class="form-control" type="number" name="price" id="price"
                   value="${product.price > 0? product.price: ""}">
        </div>
        <%--    <c:if test="${errors != null && errors.containsKey('price')}">--%>
        <c:if test="${errors != null && not empty errors.price}">
            <div style="color:red">${errors.price}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="sku">SKU</label>
        <div class="col-sm-4">
            <%--        <input type="text" name="sku" id="sku" value="<%=product.getSku() != null ? product.getSku() : ""%>">--%>
            <input class="form-control" type="text" name="sku" id="sku"
                   value="${product.sku != null ? product.sku : ""}">
            <!-- This is the same as the above line -->
        </div>
        <c:if test="${errors != null && errors.containsKey('sku')}">
            <div style="color:red">${errors.sku}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="createdDate">Created Date</label>
        <div class="col-sm-4">
            <input class="form-control" type="date" name="createdDate" id="createdDate"
                   value="${product.createdDate != null ? product.createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}">
        </div>
        <c:if test="${errors != null && errors.containsKey('createdDate')}">
            <div style="color:red">${errors.createdDate}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="category" class="col-form-label col-sm-2">Category</label>
        <div class="col-sm-4">
            <select class="form-select" name="category" id="category">
                <option value="">---select---</option>
                <c:forEach items="${categories}" var="c">
                    <option value="${c.id}" ${c.id.equals(product.category.id) ? "selected": ""}>${c.name}</option>
                </c:forEach>
            </select>
        </div>
        <c:if test="${errors != null && errors.containsKey('category')}">
            <div style="color:red">${errors.category}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <div>
            <input class="btn btn-primary" type="submit"
                   value="${product.id != null && product.id > 0 ? "Edit" : "Save"}">
        </div>
    </div>
    <input type="hidden" name="id" value="${product.id}">
</form>
<jsp:include page="layout/footer.jsp"/>
