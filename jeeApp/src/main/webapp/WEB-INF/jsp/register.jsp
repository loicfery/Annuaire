<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container">
    <h1>Edit Person</h1>

    <form:form method="POST" modelAttribute="person">
        <sec:csrfInput />
        <form:errors path="*" cssClass="alert alert-danger" element="div" />

        <div class="form-group">
            <label for="firstName">First name :</label>
            <form:input class="form-control" path="firstName" />
            <form:errors path="firstName" cssClass="alert alert-warning" element="div" />
        </div>
        <div class="form-group">
            <label for="lastName">Last name :</label>
            <form:input path="lastName" class="form-control"/>
            <form:errors path="lastName" cssClass="alert alert-warning"
                         element="div" />
        </div>
        <div class="form-group">
            <label for="mail">Mail :</label>
            <form:input path="mail" class="form-control"/>
            <form:errors path="mail" cssClass="alert alert-warning"
                         element="div" />
        </div>
        <div class="form-group">
            <label for="password">Password :</label>
            <form:input path="password" class="form-control"/>
            <form:errors path="password" cssClass="alert alert-warning"
                         element="div" />
        </div>
        <div class="form-group">
            <label for="web">Web :</label>
            <form:input path="web" class="form-control"/>
            <form:errors path="web" cssClass="alert alert-warning"
                         element="div" />
        </div>
        <div class="form-group">
            <label for="birthday">Birthday :</label>
            <form:input type="date" path="birthday" class="form-control"/>
            <form:errors path="birthday" cssClass="alert alert-warning"
                         element="div" />
        </div>
        <div class="form-group">
            <label for="group">Group :</label>
            <form:select path="group" multiple="false" class="form-control">
                <form:option value="" label="--- Select ---" />
                <form:options items="${groupSelection}" />
            </form:select>
            <form:errors path="group" cssClass="alert alert-warning"
                         element="div" />
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-info">Submit</button>
        </div>
    </form:form>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>