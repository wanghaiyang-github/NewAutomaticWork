<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="row">
	<div class="col-lg-12">
		<section class="panel">
			<div class="panel-body">
				<div class="pagin"><br/>
					<input type="hidden" name="page" id="page" value="${pageInfo.page}"/>
					<input type="hidden" name="pageCount" id="pageCount" value="${pageInfo.pageCount}"/>
					<input type="hidden" name="allRecordCount" id="allRecordCount" value="${pageInfo.allRecordCount}"/>
					<div id="kkpager" style="margin-right:30px;margin-top:-22px"></div>
				</div>
			</div>
		</section>
	</div>
</div>