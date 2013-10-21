<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <h3>Play</h3>
<c:if  test="${!empty streamList}">
	<c:if  test="${player == 'youtube'}">
		<div id='mediaspace'>Youtube Player</div>
	</c:if>
	<c:if  test="${player == 'flash'}">
		<div id='mediaspace'>Flash Player</div>
		<script type='text/javascript'>
  			jwplayer('mediaspace').setup({
    			'flashplayer': '/resources/player.swf',
    			'file': '${name}',
    			'streamer': '${stream}',
    			'controlbar': 'bottom',
    			'width': '470',
    			'height': '290'
  			});
		</script>
	</c:if>
</c:if>