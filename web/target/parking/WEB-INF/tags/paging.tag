<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${currentPageGridState.totalCount>0}">
		<ul class="pagination">
			<c:choose>
				<c:when test="${currentPageGridState.firstPage}">
					<li class="disabled"><a class="material-icons"><i>chevron_left</i></a></li>
				</c:when>
				<c:otherwise>
					<li class="waves-effect"><a class="material-icons"
						href="?page=${currentPageGridState.page-1}"><i>chevron_left</i></a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="1" end="${currentPageGridState.pageCount}" varStatus="loop">
				<c:choose>
					<c:when test="${loop.index == currentPageGridState.page}">
						<li class="active"><a>${loop.index}</a></li>
					</c:when>
					<c:otherwise>
						<li class="waves-effect"><a href="?page=${loop.index}">${loop.index}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${currentPageGridState.lastPage}">
					<li class="disabled"><a class="material-icons"><i>chevron_right</i></a></li>
				</c:when>
				<c:otherwise>
					<li class="waves-effect"><a class="material-icons"
						href="?page=${currentPageGridState.page+1}"><i>chevron_right</i></a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</c:when>
</c:choose>

