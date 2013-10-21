<%@ tag import="org.springframework.util.StringUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="pagedListHolder" required="true" type="org.springframework.beans.support.PagedListHolder" %>
<%@ attribute name="pagedLink" required="true" type="java.lang.String" %>

<link href="css/pagination.css" rel="stylesheet" type="text/css"/>

<c:if test="${pagedListHolder.pageCount > 1}">
    <c:if test="${!pagedListHolder.firstPage}">
        <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage()-1)) %>">&lt;</a></span>
    </c:if>
    <c:if test="${pagedListHolder.firstLinkedPage > 0}">
        <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", "0") %>">1</a></span>
    </c:if>
    <c:if test="${pagedListHolder.firstLinkedPage > 1}">
        <span class="pagingDots">...</span>
    </c:if>
    <c:forEach begin="${pagedListHolder.firstLinkedPage}" end="${pagedListHolder.lastLinkedPage}" var="i">
        <c:choose>
            <c:when test="${pagedListHolder.page == i}">
                <span class="pagingItemCurrent">${i+1}</span>
            </c:when>
            <c:otherwise>
                <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(jspContext.getAttribute("i"))) %>">${i+1}</a></span>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${pagedListHolder.lastLinkedPage < pagedListHolder.pageCount - 2}">
        <span class="pagingDots">...</span>
    </c:if>
    <c:if test="${pagedListHolder.lastLinkedPage < pagedListHolder.pageCount - 1}">
        <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPageCount()-1)) %>">${pagedListHolder.pageCount}</a></span>
    </c:if>
    <c:if test="${!pagedListHolder.lastPage}">
        <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage()+1)) %>">&gt;</a></span>
    </c:if>
</c:if>