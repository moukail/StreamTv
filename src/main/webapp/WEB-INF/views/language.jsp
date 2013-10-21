<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div class="container marketing">
	<header>
		<h1>Language Manager</h1>
	</header>
	<sec:authorize access="hasRole('admin')">
 		<form:form method="post" action="add" commandName="language">
 			<table>
			    <tr>
			        <td><form:label path="languageName"><spring:message code="label.languageName"/></form:label></td>
			        <td><form:input path="languageName" /></td>
			        <td><form:errors path="languageName" cssClass="error"/></td>
			    </tr>
			    <tr>
			        <td><form:label path="languageCode"><spring:message code="label.languageCode"/></form:label></td>
			        <td><form:input path="languageCode" /></td>
			        <td><form:errors path="languageCode" cssClass="error"/></td>
			    </tr>
			    <tr>
			        <td colspan="2">
			            <input type="submit" value="<spring:message code="label.addLanguage"/>"/>
			        </td>
			    </tr>
			</table>
		</form:form>
	</sec:authorize>
	<c:if  test="${!empty languageList}">
		<header>
			<h1>Languages</h1>
		</header>
		<table class="data">
			<tr>
				<th>Name</th>
				<th>Code</th>
				<th>&nbsp;</th>
				<sec:authorize access="hasRole('admin')">
				<th>&nbsp;</th>
				</sec:authorize>
			</tr>
			<c:forEach items="${languageList}" var="language">
				<tr>
					<td>${language.languageName}</td>
					<td>${language.languageCode}</td>
					<td><a href="channels/${country.id}">channels</a></td>
					<sec:authorize access="hasRole('admin')">
					<td><a href="delete/${language.id}">delete</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>