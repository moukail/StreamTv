<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div class="container marketing">
	<sec:authorize access="hasRole('admin')">
		<header>
			<h1>Channel Manager</h1>
		</header>
		<form:form method="post" action="add" commandName="channel">
			<table>
	    		<tr>
	        		<td><form:label path="channelName"><spring:message code="channel.label.name"/></form:label></td>
	        		<td><form:input path="channelName" /></td>
	        		<td><form:errors path="channelName" cssClass="error"/></td>
	    		</tr>
	    		<tr>
	        		<td><form:label path="description"><spring:message code="channel.label.description"/></form:label></td>
	        		<td><form:input path="description" /></td>
	        		<td><form:errors path="description" cssClass="error"/></td>
	    		</tr>
	    		<tr>
	        		<td><form:label path="website"><spring:message code="channel.label.website"/></form:label></td>
	        		<td><form:input path="website" /></td>
	        		<td><form:errors path="website" cssClass="error"/></td>
	    		</tr>
	    		<tr>
	        		<td><form:label path="country"><spring:message code="channel.label.country"/></form:label></td>
	        		<td>
	        			<form:select id="countryList" path="country">
	  						<form:options items="${countryList}" itemValue="id" itemLabel="countryName"/>
						</form:select>
	        		</td>
	    		</tr>
	    		<tr>
	        		<td><form:label path="category"><spring:message code="channel.label.category"/></form:label></td>
		        	<td>
		        		<form:select id="categoryList" path="category">
		  					<form:options items="${categoryList}" itemValue="id" itemLabel="categoryName"/>
						</form:select>
		        	</td>
	    		</tr>
	    		<tr>
	        		<td colspan="2">
	            		<input type="submit" value="<spring:message code="channel.label.add"/>"/>
	        		</td>
	    		</tr>
			</table>
		</form:form>
	</sec:authorize>
	<header>
		<h1>Channels</h1>
	</header>		

	<c:if  test="${!empty channelList}">
		<table class="data">
			<tr>
				<th>Country</th>
				<th>Category</th>
				<th>Name</th>
				<th>Description</th>
				<th>Logo</th>
				<th>&nbsp;</th>
				<sec:authorize access="hasRole('admin')">
					<th>&nbsp;</th>
					<th>&nbsp;</th>
				</sec:authorize>
			</tr>
			<c:forEach items="${channelList}" var="channel">
				<tr>
					<td>${channel.country.countryName}</td>
					<td>${channel.category.categoryName}</td>
					<td>${channel.channelName}</td>
					<td width="500">${channel.description}</td>
					<td><img src="http://192.168.1.1/img/logo/${channel.logo}" width="100"/></td>
					<td><a href="/stream/index/${channel.id}">view</a></td>
					<sec:authorize access="hasRole('admin')">
					<td><a href="delete/${channel.id}">delete</a></td>
					<td><a href="/stream/index/${channel.id}">streams</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>