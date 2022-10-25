<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container">
    <h1>Edit group</h1>

    <form:form method="POST" modelAttribute="group">
        <sec:csrfInput />
        <form:errors path="*" cssClass="alert alert-danger" element="div" />
        <div class="form-group">
            <label for="name">Name :</label>
            <form:input path="name" class="form-control"/>
            <form:errors path="name" cssClass="alert alert-warning" element="div" />
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-info">Submit</button>
        </div>
    </form:form>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
