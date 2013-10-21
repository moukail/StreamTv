<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div class="container marketing">
	<header>
		<h1>Category Manager</h1>
	</header>
	<sec:authorize access="hasRole('admin')">
	 	<form:form method="post" action="add" commandName="category">
		 	<table>
			    <tr>
			        <td><form:label path="categoryName"><spring:message code="label.categoryName"/></form:label></td>
			        <td><form:input path="categoryName" /></td>
			        <td><form:errors path="categoryName" cssClass="error"/></td>
			    </tr>
			    <tr>
			        <td colspan="2">
			            <input type="submit" value="<spring:message code="label.addCategory"/>"/>
			        </td>
			    </tr>
			</table>
		</form:form>
	</sec:authorize>
	<c:if  test="${!empty categoryList}">
		<header>
			<h1>Categories</h1>
		</header>
		<table class="data">
			<tr>
				<th>Name</th>
				<th>&nbsp;</th>
				<sec:authorize access="hasRole('admin')">
				<th>&nbsp;</th>
				</sec:authorize>
			</tr>
			<c:forEach items="${categoryList}" var="category">
				<tr>
					<td>${category.categoryName}</td>
					<td><a href="channels/${country.id}">channels</a></td>
					<sec:authorize access="hasRole('admin')">
					<td><a href="delete/${category.id}">delete</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>