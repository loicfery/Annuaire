<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="edit" value="/group/edit" />
<c:url var="person" value="/person/information"/>
<c:url var="information" value="/group/information"/>
<c:url var="delete" value="/group/delete"/>
<c:url var="find" value="/find"/>

<div class="container">
    <h1>Informations</h1><br/>
    <h3>Name : <c:out value="${group.name}" /></h3><br/>
    <table class="table table-hover">
        <h3>Members : </h3><br/>
        <c:if test="${group.persons.size() == 0}">
            <tr>
                <td>
                    <p>No member register !</p>
                </td>
            </tr>
        </c:if>
        <c:if test="${group.persons.size() > 0}">
            <form action="${information}?id=${group.id}${find}" method="post">
                <sec:csrfInput/>
                <p>
                    <input class="btn btn-info" type="submit" value="Find person" />
                    <span style="margin-left: 30px;"></span> <input name="name" size="10" />
                    Does not work !!
                </p>
            </form><br/>
        </c:if>
        <c:forEach items="${group.persons}" var="persons">
            <tr>
                <td>
                    <a href="${person}?id=${persons.id}">
                        <c:out value="${persons.firstName}" />  <c:out value="${persons.lastName}" />
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${isAdmin}">
        <a class="btn btn-info" href="${edit}?id=${group.id}">Edit information</a>
        <a class="btn btn-info" href="${delete}?id=${group.id}">Delete group</a>
    </c:if>
</div>

<style>
    .head {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }
</style>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
