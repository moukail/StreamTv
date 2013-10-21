<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<script type="text/javascript">                                         
var flashVars = '';
var extraVars= 0;

function generateCode() {
	$("#previewLabels").show();
	flashVars = 'http://d2gy0hmj48ipqr.cloudfront.net/PBS_COVE_Next_Gen_Master/naat-001103-stream.mp4';
	embedPlayer();
		
}

function embedPlayer() {
	var embedCode =  '<object width="470" height="320"> ';
	embedCode += '<param name="movie" value="http://www.moukail.nl/swf/StrobeMediaPlayback.swf"></param>';
	embedCode += '<param name="flashvars" value="src=http://akamedia10.lsops.net/live/skynewsi_ar.smil/playlist.m3u8&plugin_mpegTS=http://s1-cdns.veez.co/player/s_OnlinelibHLSTransmuxer.swf&controlBarMode=floating&poster=&streamType=live&scaleMode=letterbox&autoPlay=true"></param>';
	embedCode += '<param name="allowFullScreen" value="true"></param><param name="allowscriptaccess" value="always"></param>';
	embedCode += '<param name="wmode" value="direct"></param>';
	embedCode += '<embed src="http://s1-cdns.veez.co/player/StrobeMediaPlayback.swf?423234cc60c0a82a" type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true" wmode="direct" width="470" height="320" flashvars="src=http://akamedia10.lsops.net/live/skynewsi_ar.smil/playlist.m3u8&plugin_mpegTS=http://s1-cdns.veez.co/player/s_OnlinelibHLSTransmuxer.swf&controlBarMode=floating&poster=&streamType=live&scaleMode=letterbox&autoPlay=true">';
	embedCode += '</embed></object>';
	$("#player").html(embedCode);
}
</script>

<div class="container marketing">
	<sec:authorize access="hasRole('admin')">
		<header>
			<h1>Episode Manager</h1>
		</header>
		<form:form method="post" action="/episode/add" commandName="episode">
			<table>
				<tr>
	        		<td><form:label path="season"><spring:message code="episode.label.season"/></form:label></td>
	        		<td><form:input path="season" /></td>
	        		<td><form:errors path="season" cssClass="error"/></td>
	    		</tr>
				<tr>
	        		<td><form:label path="episode"><spring:message code="episode.label.episode"/></form:label></td>
	        		<td><form:input path="episode" /></td>
	        		<td><form:errors path="episode" cssClass="error"/></td>
	    		</tr>
	    		<tr>
	        		<td><form:label path="subtitle"><spring:message code="episode.label.subtitle"/></form:label></td>
	        		<td><form:input path="subtitle" /></td>
	        		<td><form:errors path="subtitle" cssClass="error"/></td>
	    		</tr>
	    		<tr>
	        		<td><form:label path="description"><spring:message code="episode.label.description"/></form:label></td>
	        		<td><form:textarea path="description" /></td>
	        		<td><form:errors path="description" cssClass="error"/></td>
	    		</tr>
	    		<tr>
	        		<td><form:label path="filepath"><spring:message code="episode.label.filepath"/></form:label></td>
	        		<td><form:input path="filepath" /></td>
	        		<td><form:errors path="filepath" cssClass="error"/></td>
	    		</tr>
	    		<tr>
	        		<td colspan="2">
	            		<input type="submit" value="<spring:message code="episode.label.add"/>"/>
	        		</td>
	    		</tr>
			</table>
		</form:form>
	</sec:authorize>
	
	<div id="player"></div>
	
	<header>
		<h1>Episodes</h1>
	</header>		

	<c:if  test="${!empty episodeList}">
		<table class="data">
			<tr>
				<th>Season Nr</th>
				<th>Episode Nr</th>
				<th>Subtitle</th>
				<th>Description</th>
				<sec:authorize access="hasRole('admin')">
				<th>File path</th>
				</sec:authorize>
				<th>&nbsp;</th>
				<sec:authorize access="hasRole('admin')">
					<th>&nbsp;</th>
				</sec:authorize>
			</tr>
			<c:forEach items="${episodeList}" var="episode">
				<tr>
					<td>${episode.season}</td>
					<td>${episode.episode}</td>
					<td>${episode.subtitle}</td>
					<td width="500">${episode.description}</td>
					<sec:authorize access="hasRole('admin')">
						<td>${episode.filepath}</td>
						<td><a href="/episode/delete/${episode.id}">delete</a></td>
					</sec:authorize>
					<sec:authorize access="hasRole('admin')">
						<td><a href="javascript:generateCode()">play</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>
<script>
	function play(filepath){
		alert('test ok :' + filepath);
	}
</script>