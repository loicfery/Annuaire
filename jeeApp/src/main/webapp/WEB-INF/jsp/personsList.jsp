<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="edit" value="/person/edit" />
<c:url var="information" value="/person/information"/>

<div class="container">
    <h1>Persons </h1><br/>
    <table class="table table-hover">
        <c:forEach items="${persons}" var="person">
            <tr>
                <td>
                    <a href="${information}?id=${person.id}">
                        <c:out value="${person.firstName}" /> <c:out value="${person.lastName}" />
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
