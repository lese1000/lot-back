<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>JoinBuyDetail管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>合买订单详情列表 </h5>
		<div class="ibox-tools">
			<a class="collapse-link">
				<i class="fa fa-chevron-up"></i>
			</a>
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-wrench"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#">选项1</a>
				</li>
				<li><a href="#">选项2</a>
				</li>
			</ul>
			<a class="close-link">
				<i class="fa fa-times"></i>
			</a>
		</div>
	</div>
    
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="pJoinBuyDetail" action="${ctx}/lottery/pJoinBuyDetail/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input  name="joinBuyId" type="hidden" value="${pJoinBuyDetail.joinBuyId}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>玩家姓名：</span>
				<form:input path="playerName" htmlEscape="false" maxlength="20"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
<!-- 			<shiro:hasPermission name="lottery:pJoinBuyDetail:add"> -->
<!-- 				<table:addRow url="${ctx}/lottery/pJoinBuyDetail/form" title="JoinBuyDetail"></table:addRow>增加按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pJoinBuyDetail:edit"> -->
<!-- 			    <table:editRow url="${ctx}/lottery/pJoinBuyDetail/form" title="JoinBuyDetail" id="contentTable"></table:editRow>编辑按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pJoinBuyDetail:del"> -->
<!-- 				<table:delRow url="${ctx}/lottery/pJoinBuyDetail/deleteAll" id="contentTable"></table:delRow>删除按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pJoinBuyDetail:import"> -->
<!-- 				<table:importExcel url="${ctx}/lottery/pJoinBuyDetail/import"></table:importExcel>导入按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pJoinBuyDetail:export"> -->
<!-- 	       		<table:exportExcel url="${ctx}/lottery/pJoinBuyDetail/export"></table:exportExcel>导出按钮 -->
<!-- 	       	</shiro:hasPermission> -->
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
			<a href="${ctx}/lottery/pJoinBuy/" class="btn btn-primary btn-rounded btn-outline btn-sm" ><i class="fa fa-search-plus"></i> 返回合买列表</a>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
		</div>
	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th  class="sort-column playerName">玩家姓名</th>
				<th  class="sort-column buyCount">购买份数</th>
				<th  class="sort-column buyMoney">购买金额</th>
				<th  class="sort-column winPrize">中奖金额</th>
				<th  class="sort-column createDate">下单时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pJoinBuyDetail">
			<tr>
				<td> <input type="checkbox" id="${pJoinBuyDetail.id}" class="i-checks"></td>
				<td>
					${pJoinBuyDetail.playerName}
				</td>
				<td>
					${pJoinBuyDetail.buyCount}
				</td>
				<td>
					${pJoinBuyDetail.buyMoney}
				</td>
				<td>
					${pJoinBuyDetail.winPrize}
				</td>
				<td>
					<fmt:formatDate value="${pJoinBuyDetail.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
		<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
</body>
</html>