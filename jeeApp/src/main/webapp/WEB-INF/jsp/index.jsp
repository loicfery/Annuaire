<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container">
	<h1>Annuaire</h1>
	<p>
		<sec:authorize access="isAuthenticated()">
			Welcome <c:out value="${username}" /><br/>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
			<c:out value="${message}" />
		</sec:authorize>
	</p>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>