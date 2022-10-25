<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url value="/password-forgot" var="forgot"/>


<div class="container">
    <div class="vertical-center">
        <form action="${forgot}" method="post">
            <div class="form-group">
                <input type="text" id="username" name="username" placeholder="Username"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>
            <div>
                <p>
                    <c:out value="${message}" />
                </p>
            </div>
                <div class="form-group">
                <button type="submit" class="btn btn-info">Send mail</button>
            </div>
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