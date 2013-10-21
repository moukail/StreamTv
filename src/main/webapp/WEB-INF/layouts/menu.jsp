<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="navbar navbar-inverse navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="/">StreamTv</a>
            </div>
            <div class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
                <li class="active"><a href="/">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
                <li class="dropdown">
                	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Stream <b class="caret"></b></a>
                	<ul class="dropdown-menu">
                	<sec:authorize access="hasRole('admin')">
                		<li><a href=/category/index>Categories</a></li>
						<li><a href=/country/index>Countries</a></li>
						<li><a href=/language/index>Languages</a></li>
					</sec:authorize>
					<li><a href=/tv/index>TV</a></li>
					<li><a href=/radio/index>Radio</a></li>
                  	<li><a href=/schedule/index>Schedules</a></li>
                  	<li class="divider"></li>
                  	<li class="dropdown-header">Video On Demand</li>
                  	<li><a href=/film/index>Films</a></li>
                  	<li><a href=/serie/index>Series</a></li>
                	</ul>
              	</li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
              	<sec:authorize access="isAnonymous()">
				<li><a href=/user/signup>Sign Up</a></li>
				<li class="dropdown">
					<a href="/user/login" class="dropdown-toggle" data-toggle="dropdown">Login<b class="caret"></b></a>
					<ul class="dropdown-menu">
					<form class="navbar-form pull-right" name='f' action="<c:url value='/j_spring_security_check' />" method="post">
						<li><input class="span2" type='text' name='j_username' placeholder="Username"></li>
						<li><input class="span2" type="password" name='j_password' placeholder="Password"></li>
						<li>
							<input id="rememberme" name="rememberme" type="checkbox" class="field login-checkbox" value="First Choice" tabindex="4"/>
							<label class="choice" for="Field">Remember me</label>
						</li>
						<li><button type="submit" class="btn btn-primary">Sign in</button></li>
					</form>
					</ul>
				</li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
				<li><a href='<c:url value="/j_spring_security_logout"/>'>Logout</a></li>
				<li><a href=/user/profile>My account</a></li>
    			</sec:authorize>
				</ul>
            </div>
          </div>
 	</div>
</div>