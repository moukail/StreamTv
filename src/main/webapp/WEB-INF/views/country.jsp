<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<div class="container marketing">
	<sec:authorize access="hasRole('admin')">
		<header>
			<h1>Country Manager</h1>
		</header>
		<form:form method="post" action="add" commandName="country">
			<table>
	    		<tr>
			        <td><form:label path="countryName"><spring:message code="label.countryName"/></form:label></td>
			        <td><form:input path="countryName" /></td>
			        <td><form:errors path="countryName" cssClass="error"/></td>
	    		</tr>
			    <tr>
			        <td><form:label path="countryCode"><spring:message code="label.countryCode"/></form:label></td>
			        <td><form:input path="countryCode" /></td>
			        <td><form:errors path="countryCode" cssClass="error"/></td>
			    </tr>
	    		<tr>
	        		<td colspan="2">
	            		<input type="submit" value="<spring:message code="label.addCountry"/>"/>
	        		</td>
	    		</tr>
			</table>
		</form:form>
	</sec:authorize>
	<header>
		<h1>Countries</h1>
	</header>
<c:if  test="${!empty countryList}">
<table class="data">
<tr>
	<th>Name</th>
	<th>Code</th>
	<th>&nbsp;</th>
	<sec:authorize access="hasRole('admin')">
	<th>&nbsp;</th>
	</sec:authorize>
</tr>
<c:forEach items="${countryList}" var="country">
	<tr>
		<td>${country.countryName}</td>
		<td>${country.countryCode}</td>
		<td><a href="channels/${country.id}">channels</a></td>
		<sec:authorize access="hasRole('admin')">
		<td><a href="delete/${country.id}">delete</a></td>
		</sec:authorize>
	</tr>
</c:forEach>
</table>
</c:if>
</div>