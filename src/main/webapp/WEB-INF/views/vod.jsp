<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div class="container marketing">
	<sec:authorize access="hasRole('admin')">
		<header>
			<h1>VOD Manager</h1>
		</header>
		<form:form method="post" action="add" commandName="vod">
			<table>
	    		<tr>
	        		<td><form:label path="title"><spring:message code="vod.label.title"/></form:label></td>
	        		<td><form:input path="title" /></td>
	        		<td><form:errors path="title" cssClass="error"/></td>
	    		</tr>
	    		<tr>
	        		<td><form:label path="language"><spring:message code="vod.label.language"/></form:label></td>
		        	<td>
		        		<form:select id="languageList" path="language">
		  					<form:options items="${languageList}" itemValue="id" itemLabel="languageName"/>
						</form:select>
		        	</td>
	    		</tr>
	    		<tr>
	        		<td><form:label path="category"><spring:message code="vod.label.category"/></form:label></td>
		        	<td>
		        		<form:select id="categoryList" path="category">
		  					<form:options items="${categoryList}" itemValue="id" itemLabel="categoryName"/>
						</form:select>
		        	</td>
	    		</tr>
	    		<tr>
	        		<td><form:label path="description"><spring:message code="vod.label.description"/></form:label></td>
	        		<td><form:textarea path="description" /></td>
	        		<td><form:errors path="description" cssClass="error"/></td>
	    		</tr>
	    		<tr>
	        		<td><form:label path="year"><spring:message code="vod.label.year"/></form:label></td>
	        		<td><form:input path="year" /></td>
	        		<td><form:errors path="year" cssClass="error"/></td>
	    		</tr>
	    		<tr>
	        		<td><form:label path="poster"><spring:message code="vod.label.poster"/></form:label></td>
	        		<td><form:input path="poster" /></td>
	        		<td><form:errors path="poster" cssClass="error"/></td>
	    		</tr>
	    		<tr>
	        		<td colspan="2">
	            		<input type="submit" value="<spring:message code="vod.label.add"/>"/>
	        		</td>
	    		</tr>
			</table>
		</form:form>
	</sec:authorize>
	<header>
		<h1>VODs</h1>
	</header>		

	<c:if  test="${!empty vodList}">
		<table class="data">
			<tr>
				<th>Title</th>
				<th>Category</th>
				<th>Description</th>
				<th>Year</th>
				<th>Poster</th>
				<th>&nbsp;</th>
				<sec:authorize access="hasRole('admin')">
					<th>&nbsp;</th>
				</sec:authorize>
			</tr>
			<c:forEach items="${vodList}" var="vod">
				<tr>
					<td>${vod.title}</td>
					<td>${vod.category.categoryName}</td>
					<td width="500">${vod.description}</td>
					<td>${vod.year}</td>
					<td><img src="${vod.poster}"/></td>
					<td><a href="/episode/index/${vod.id}">view</a></td>
					<sec:authorize access="hasRole('admin')">
					<td><a href="delete/${vod.id}">delete</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>