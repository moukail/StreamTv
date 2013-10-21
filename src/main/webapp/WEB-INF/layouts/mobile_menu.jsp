<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="f">
	<li data-role="list-divider">Menu</li>
	<li><a href="/">Home</a></li>
	<li><a href="/about">About</a></li>
	<li><a href="/contact">Contact</a></li>
	<sec:authorize access="isAnonymous()">
	<li><a href="/user/signup">Signup</a></li>
	<li><a href="/user/login">Login</a></li>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
	<li><a href='<c:url value="/j_spring_security_logout"/>'>Logout</a></li>
	<li><a href=/user/profile>My account</a></li>
    </sec:authorize>
</ul>