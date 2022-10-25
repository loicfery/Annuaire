<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url var="edit" value="/group/edit" />
<c:url var="information" value="/group/information"/>
<c:url var="find" value="/group/find"/>

<div class="container">
    <h1>Groups </h1><br/>
    <form action="${find}" method="post">
        <sec:csrfInput/>
        <p>
            <input class="btn btn-info" type="submit" value="Find group" />
            <span style="margin-left: 30px;"></span> <input name="name" size="10" />
        </p>
    </form><br/>
    <table class="table table-hover">
        <c:forEach items="${groups}" var="gr">
            <tr>
                <td>
                    <div class="group">
                        <a href="${information}?id=${gr.id}">
                            <c:out value="${gr.name}" />
                        </a>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p>
        <a class="btn btn-info" href="${edit}">Create new group</a>
    </p>
</div>

<style>
    .group {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }
</style>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
