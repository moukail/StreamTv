<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container marketing">
	<header>
		<h1>Sign up</h1>
	</header>
	<form:form method="post" action="add" commandName="user">
		<table>
		    <tr>
		        <td><form:label path="firstname"><spring:message code="user.label.firstname"/></form:label></td>
		        <td><form:input path="firstname" /></td>
		        <td><form:errors path="firstname" cssClass="error"/></td>
		    </tr>
		    <tr>
		        <td><form:label path="lastname"><spring:message code="user.label.lastname"/></form:label></td>
		        <td><form:input path="lastname" /></td>
		        <td><form:errors path="lastname" cssClass="error"/></td>
		    </tr>
		    <tr>
		        <td><form:label path="email"><spring:message code="user.label.email"/></form:label></td>
		        <td><form:input path="email" /></td>
		        <td><form:errors path="email" cssClass="error"/></td>
		    </tr>
		    <tr>
		        <td><form:label path="username"><spring:message code="user.label.username"/></form:label></td>
		        <td><form:input path="username" /></td>
		        <td><form:errors path="username" cssClass="error"/></td>
		    </tr>
		    <tr>
		        <td><form:label path="password"><spring:message code="user.label.password"/></form:label></td>
		        <td><form:password path="password" /></td>
		        <td><form:errors path="password" cssClass="error"/></td>
		    </tr>
		    <tr>
		        <td><form:label path="confirmpassword"><spring:message code="user.label.confirmpassword"/></form:label></td>
		        <td><form:password path="confirmpassword" /></td>
		        <td><form:errors path="confirmpassword" cssClass="error"/></td>
		    </tr>
		    <tr>
		        <td colspan="2">
		            <input type="submit" value="<spring:message code="user.label.signup"/>"/>
		        </td>
		    </tr>
		</table>
	</form:form>
</div>