<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="http://getbootstrap.com/assets/ico/favicon.png">

    <title>Carousel Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="http://getbootstrap.com/dist/css/bootstrap.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://getbootstrap.com/assets/js/html5shiv.js"></script>
      <script src="http://getbootstrap.com/assets/js/respond.min.js"></script>
    <![endif]-->

    <!-- Custom styles for this template -->
    <link href="http://getbootstrap.com/examples/carousel/carousel.css" rel="stylesheet">
    <style>
	<!--
	footer{bottom:0;width:100%;}
	-->
	</style>
  </head>
<!-- NAVBAR
================================================== -->
  <body>
    <div class="navbar-wrapper">
      <tiles:insertAttribute name="menu" />
    </div>
	<tiles:insertAttribute name="body" />
	<!-- FOOTER -->
    <tiles:insertAttribute name="footer" />
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="http://getbootstrap.com/assets/js/jquery.js"></script>
    <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
    <script src="http://getbootstrap.com/assets/js/holder.js"></script>
  </body>
</html>