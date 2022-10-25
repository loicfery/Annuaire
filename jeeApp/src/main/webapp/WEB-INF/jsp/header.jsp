<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="bootstrap_css"
       value="/webjars/bootstrap/4.6.0-1/css/bootstrap.min.css" />
<c:url var="bootstrap_js"
       value="/webjars/bootstrap/4.6.0-1/js/bootstrap.min.js" />
<c:url var="jquery_js" value="/webjars/jquery/3.5.1/jquery.min.js" />
<c:url var="css" value="/style.css" />

<c:url var="css2" value="https://www.w3schools.com/w3css/4/w3.css"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>Spring boot application</title>
    <link rel="stylesheet" href="${css}">
    <link rel="stylesheet" href="${bootstrap_css}">
    <link rel="stylesheet" href="${css2}">
    <script src="${jquery_js}"></script>
    <script src="${bootstrap_js}"></script>
</head>
<body>

    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

    <div class="w3-bar w3-light-grey">
        <div class="w3-bar-item">
            <sec:authorize access="!isAuthenticated()">
                <a href="/login">Login</a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a href="/logout">Logout</a>
            </sec:authorize>
        </div>
        <div class="w3-bar-item">
            <a href="/">Home</a>
        </div>
        <div class="w3-bar-item">
            <sec:authorize access="isAuthenticated()">
                <a href="/group/list">Groups</a>
            </sec:authorize>
        </div>
        <div class="w3-bar-item">
            <sec:authorize access="isAuthenticated()">
                <a href="/person/list">Persons</a>
            </sec:authorize>
        </div>
    </div>