<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
	<head>
		<title>StreamTv</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
		<link rel="stylesheet" href="http://jquerymobile.com/demos/1.1.1/docs/_assets/css/jqm-docs.css" />
	</head> 
	<body>
		<div data-role="page" class="type-home">
			<div data-role="content">
				<div class="content-secondary">

					<div id="jqm-homeheader">
						<h1 id="jqm-logo">StreamTv</h1>
						<h3>De ideale video streaming</h3>
					</div>
					<tiles:insertAttribute name="menu" />
					
				</div><!--/content-primary-->

				<div class="content-primary">
					<tiles:insertAttribute name="body" />
				</div>
			</div>

			<tiles:insertAttribute name="footer" />

		</div>
	</body>
</html>