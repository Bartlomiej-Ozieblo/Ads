<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
    <c:if test="${!empty roles}">
        <c:forEach items="${roles}" var="role">
            <h1>${role.roleName}</h1><h2>${role.id}</h2><br />
        </c:forEach>
    </c:if>
</body>
</html>