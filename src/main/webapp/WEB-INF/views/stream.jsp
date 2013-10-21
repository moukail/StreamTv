<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div class="container marketing">
	<sec:authorize access="hasRole('admin')">
	<header>
		<h1>Stream Manager</h1>
	</header>
 
<form:form method="post" action="/stream/add" commandName="stream">
 
    <table>
    <tr>
		<td><form:label path="country"><spring:message code="channel.label.country"/></form:label></td>
		<td>
			<form:select id="countryList" path="country">
				<form:options items="${countryList}" itemValue="id" itemLabel="countryName"/>
			</form:select>
		</td>
	</tr>
	<tr>
		<td><form:label path="language"><spring:message code="channel.label.language"/></form:label></td>
		<td>
			<form:select id="languageList" path="language">
				<form:options items="${languageList}" itemValue="id" itemLabel="languageName"/>
			</form:select>
		</td>
	</tr>
    <tr>
        <td><form:label path="streamfile"><spring:message code="stream.label.streamfile"/></form:label></td>
        <td><form:input path="streamfile" /></td>
        <td><form:errors path="streamfile" cssClass="error"/></td>
    </tr>
    <tr>
        <td><form:label path="bitrate"><spring:message code="stream.label.bitrate"/></form:label></td>
        <td><form:input path="bitrate" /></td>
        <td><form:errors path="bitrate" cssClass="error"/></td>
    </tr>
    <tr>
        <td><form:label path="format"><spring:message code="stream.label.format"/></form:label></td>
        <td><form:select path="format">
       	<form:option value="MP3">MP3</form:option>
       	<form:option value="M4A">AAC</form:option>
       	<form:option value="MP4">MP4</form:option>
       	<form:option value="MP4">MOV</form:option>
       	<form:option value="MP4">F4V</form:option>
       	<form:option value="FLV">FLV</form:option>
       	<form:option value="WMA">WMA</form:option>
        </form:select></td>
        <td><form:errors path="format" cssClass="error"/></td>
    </tr>
    <tr>
        <td><form:label path="scheme"><spring:message code="stream.label.scheme"/></form:label></td>
        <td><form:select path="scheme">
       	<form:option value="MMS">MMS</form:option>
       	<form:option value="HTTP">HTTP</form:option>
       	<form:option value="RTMP">RTMP</form:option>
       	<form:option value="RTSP">RTSP</form:option>
        </form:select></td>
        <td><form:errors path="scheme" cssClass="error"/></td>
    </tr>
    <tr>
        <td><form:label path="player"><spring:message code="stream.label.player"/></form:label></td>
        <td><form:select path="player">
       	<form:option value="flash">Flash</form:option>
       	<form:option value="youtube">Youtube</form:option>
       	<form:option value="wmp">Windows Media Player</form:option>
       	<form:option value="nos">NOS Player</form:option>
       	<form:option value="delve">Delve Player</form:option>
        </form:select></td>
        <td><form:errors path="player" cssClass="error"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="stream.label.add"/>"/>
        </td>
    </tr>
</table>
</form:form>
</sec:authorize>
	<header>
		<h1>Streams</h1>
	</header>
	
	
<c:if  test="${!empty streamList}">
<c:if  test="${player == 'youtube'}">
		<div id='mediaspace'>
			<embed src="http://www.youtube.com/v/${streamfile}?version=3&autoplay=1" type="application/x-shockwave-flash" width="600" height="390"></embed>
		</div>
	</c:if>
	<c:if  test="${player == 'flash'}">
		<div id='mediaspace'>Flash Player</div>
		<script type='text/javascript' src="/resources/jwplayer/jwplayer.js"></script>
		<script type='text/javascript'>
  			jwplayer('mediaspace').setup({
    			'flashplayer': '/resources/jwplayer/player.swf',
    			'file': '${streamfile}',
    			'controlbar': 'over',
    			'skin': '/resources/skins/dw.zip',
    			'autostart': true,
    			'width': '580',
    			'height': '400',
				'plugins': 'googlytics-1,audiodescription,captions',
  			});
  		</script>
	</c:if>
	<c:if  test="${player == 'wmp'}">
		<div id="wmp"></div>
		<script type='text/javascript' src="/resources/wmvplayer/silverlight.js"></script>
		<script type='text/javascript' src="/resources/wmvplayer/wmvplayer.js"></script>
		<script type="text/javascript">
			var cnt = document.getElementById("wmp");
			var src = '/resources/wmvplayer/wmvplayer.xaml';
			var cfg = {
				file:'${streamfile}',
				height:'400',
				width:'580'
			};
			var ply = new jeroenwijering.Player(cnt,src,cfg);
	</script>
	</c:if>
	<c:if  test="${player == 'nos'}">
		<object id="nos" width="580px" height="400px" type="application/x-shockwave-flash" name="nos" data="/resources/nos_player.swf" style="visibility: visible;">
			<param name="bgcolor" value="#121018">
			<param name="scaleMode" value="noScale">
			<param name="allowFullScreen" value="true">
			<param name="wmode" value="transparent">
			<param name="allowScriptAccess" value="always">
			<param name="flashvars" value="src=${streamfile}&poster=http://s.nos.nl/img/placeholders/studiosport.jpg&autoPlay=true&verbose=true&streamType=dvr&backgroundColor=0x121018&scaleMode=letterBox&controlBarAutoHide=true&controlBarPosition=bottom&liveOffset=8&enableStageVideo=false&javascriptCallbackFunction=playerJsBridge">
		</object>
	</c:if>
	
	<c:if  test="${player == 'delve'}">
		<object id="flashcontent" width="580" height="400" type="application/x-shockwave-flash" data="http://assets.delvenetworks.com/player/loader.swf">
			<param name="menu" value="false">
			<param name="quality" value="high">
			<param name="scale" value="noscale">
			<param name="wmode" value="transparent">
			<param name="allowscriptaccess" value="always">
			<param name="allowfullscreen" value="true">
			<param name="loop" value="false">
			<param name="bgcolor" value="#000000">
			<param name="flashvars" value="${streamfile}">
		</object>
	</c:if>
	
<table class="data">
<tr>
	<sec:authorize access="hasRole('admin')">
	<th>Stream</th>
	<th>Bitrate</th>
	<th>Format</th>
	<th>Scheme</th>
	<th>&nbsp;</th>
	</sec:authorize>
	<th>&nbsp;</th>
</tr>
<c:forEach items="${streamList}" var="stream">
	<tr>
		<sec:authorize access="hasRole('admin')">
		<td>${stream.streamfile}</td>
		<td>${stream.bitrate}</td>
		<td>${stream.format}</td>
		<td>${stream.scheme}</td>
		<td><a href="/stream/delete/${stream.id}">delete</a></td>
		</sec:authorize>
		<th><a href="/stream/play/${stream.id}">play</a></th>
	</tr>
</c:forEach>
</table>
</c:if>
</div>