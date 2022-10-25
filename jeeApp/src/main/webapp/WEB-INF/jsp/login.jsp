<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url value="/login" var="loginUrl"/>
<c:url value="/register" var="register"/>
<c:url value="/password-forgot" var="forgot"/>

<div class="container">
    <div class="vertical-center">
        <h1>Please sign in</h1>
        <form action="${loginUrl}" method="post">
            <c:if test="${param.error != null}">
                <p>Invalid username and password.</p>
            </c:if>
            <c:if test="${param.logout != null}">
                <p>You have been logged out.</p>
            </c:if>
            <p>
                <input type="text" id="username" name="username" placeholder="Username"/>
            </p>
            <p>
                <input type="password" id="password" name="password" placeholder="Password"/>
            </p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-info" >Sign in</button>
        </form>
        <br/>
        <form method="get" action="${forgot}">
            <button type="submit" class="btn btn-info" >password forgot</button>
        </form>
        <br/>
        <form method="get" action="${register}">
            <button type="submit" class="btn btn-info" >Register</button>
        </form>
    </div>
</div>

<style>
    .container{
        text-align: center;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 200px;
        transform: translatey(50%);
    }
    button{
        width: 200px;
    }
    body{
        background-color: lightgray;
    }
</style>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
