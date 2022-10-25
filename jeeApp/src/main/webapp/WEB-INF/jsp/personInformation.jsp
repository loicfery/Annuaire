<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="group" value="/group/information"/>
<c:url var="edit" value="/person/edit" />
<c:url var="delete" value="/person/delete"/>


<div class="container">
    <h1>Informations</h1><br/>
    <h3>First name : <c:out value="${person.firstName}" /></h3>
    <h3>Last name : <c:out value="${person.lastName}" /></h3>
    <c:if test="${userInformation.equals(person.mail)}">
        <h3>Mail : <c:out value="${person.mail}" /> </h3>
        <h3>Birthday : <c:out value="${person.birthday}" /> </h3>
        <h3>Password : <c:out value="${person.password}" /> </h3>
    </c:if>
    <c:if test="${!person.web.equals('')}">
        <h3>Web : <c:out value="${person.web}" default="No web register !"/></h3>
    </c:if>
    <c:if test="${person.web.equals('')}">
        <h3>Web : No web register !</h3>
    </c:if>
    <h3>
        Group :
        <c:if test="${person.group != null}">
            <a href="${group}?id=${person.group.id}">
                <c:out value="${person.group.name}" />
            </a>
        </c:if>
        <c:if test="${person.group == null}">
            No group register !
        </c:if>
    </h3><br/>
    <sec:authorize access="isAuthenticated()">
        <c:if test="${userInformation.equals(person.mail)}">
            <a class="btn btn-info" href="${edit}?id=${person.id}">Edit information</a>
        </c:if>
        <c:if test="${isAdmin}">
            <a class = "btn btn-info" href="${delete}?id=${person.id}">Delete person</a>
        </c:if>
    </sec:authorize>

</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
