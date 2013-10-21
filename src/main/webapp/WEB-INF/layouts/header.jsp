<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<hgroup>
		<h1><a href='/'>Stream TV</a></h1>
		<h2>StreamTv</h2>
	</hgroup>
	<nav id=config>
		<ul>
			<li id=languages>
				<a href=#>Languages</a>
				<ul id=subMenu1 class=subMenu>
					<li><a href="?lang=en">English</a></li>
					<li><a href="?lang=nl">Nederlands</a></li>
					<li><a href="?lang=de">Deutsch</a></li>
				</ul>
			</li>
			<li id=themes>
				<a href=#>Themes</a>
				<ul id=subMenu2 class=subMenu>
					<li><a href="?theme=standard">Standard</a></li>
					<li><a href="?theme=black">Black</a></li>
					<li><a href="?theme=blue">Blue</a></li>
				</ul>
			</li>
		</ul>
	</nav>
	<nav id=global>
		<ul>
			<li><a href='/'>Home</a></li>
			<li><a href=#>Contact</a></li>
			<li id=services>
				<a href=#>Channels</a>
				<ul id=subMenu>
					<li><a href=/category/index>Categories</a></li>
					<li><a href=/country/index>Countries</a></li>
					<li><a href=/language/index>Languages</a></li>
					<li><a href=/tv/index>TV</a></li>
					<li><a href=/radio/index>Radio</a></li>
				</ul>
			</li>
			<sec:authorize access="isAnonymous()">
				<li><a href=/user/signup>Sign Up</a></li>
				<li><a href=/user/login>Login</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li><a href='<c:url value="/j_spring_security_logout"/>'>Logout</a></li>
				<li><a href=/user/profile>My account</a></li>
    		</sec:authorize>
		</ul>
	</nav>
</header>