<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container marketing">
	<header>
		<h1>Password Recovery</h1>
	</header>
	<c:if test="${not empty message}">
		<div class="errorblock">
			<p>${message}</p>
		</div>
	</c:if>
	<form:form method="post" action="passwordrecovery" commandName="user">
		<table>
			<tr>
				<td><form:label path="email"><spring:message code="user.label.email"/></form:label></td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssClass="error"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="<spring:message code="user.label.send"/>"/>
				</td>
			</tr>
		</table>
	</form:form>
</div>