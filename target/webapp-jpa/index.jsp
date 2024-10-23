<jsp:include page="layout/header.jsp"/>

<h3>${title}</h3>
<ul class="list-group">
    <li class="list-group-item active">Menu</li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/products">show products html</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/login">/login</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/logout">/logout</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/cart/see">/see cart</a></li>
</ul>

<jsp:include page="layout/footer.jsp"/>