<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>订单明细管理</title>
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
		<h5>订单明细列表 </h5>
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
	<form:form id="searchForm" modelAttribute="pOrderDetail" action="${ctx}/lottery/pOrderDetail/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
<!-- 			<span>投注号码：</span> -->
<!-- 				<form:input path="bettingNum" htmlEscape="false" maxlength="50"  class=" form-control input-sm"/> -->
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
<!-- 			<shiro:hasPermission name="lottery:pOrderDetail:add"> -->
<!-- 				<table:addRow url="${ctx}/lottery/pOrderDetail/form" title="订单明细"></table:addRow>增加按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pOrderDetail:edit"> -->
<!-- 			    <table:editRow url="${ctx}/lottery/pOrderDetail/form" title="订单明细" id="contentTable"></table:editRow>编辑按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pOrderDetail:del"> -->
<!-- 				<table:delRow url="${ctx}/lottery/pOrderDetail/deleteAll" id="contentTable"></table:delRow>删除按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pOrderDetail:import"> -->
<!-- 				<table:importExcel url="${ctx}/lottery/pOrderDetail/import"></table:importExcel>导入按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pOrderDetail:export"> -->
<!-- 	       		<table:exportExcel url="${ctx}/lottery/pOrderDetail/export"></table:exportExcel>导出按钮 -->
<!-- 	       	</shiro:hasPermission> -->
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
			<a href="${ctx}/lottery/pOrder/" class="btn btn-primary btn-rounded btn-outline btn-sm" ><i class="fa fa-search-plus"></i> 返回订单列表</a>
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
				<th  class="sort-column bettingNum">投注号码</th>
				<th  class="sort-column bettingCount">投注注数</th>
				<th  class="sort-column bettingMoney">投注金额</th>
				<th  class="sort-column rate">倍数</th>
				<th  class="sort-column winPrize">中奖金额</th>
<!-- 				<th>操作</th> -->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pOrderDetail">
			<tr>
				<td> <input type="checkbox" id="${pOrderDetail.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看订单明细', '${ctx}/lottery/pOrderDetail/form?id=${pOrderDetail.id}','800px', '500px')">
					${pOrderDetail.bettingNum}
				</a></td>
				<td>
					${pOrderDetail.bettingCount}
				</td>
				<td>
					${pOrderDetail.bettingMoney}
				</td>
				<td>
					${pOrderDetail.rate}
				</td>
				<td>
					${pOrderDetail.winPrize}
				</td>
<!-- 				<td> -->
<!-- 					<shiro:hasPermission name="lottery:pOrderDetail:view"> -->
<!-- 						<a href="#" onclick="openDialogView('查看订单明细', '${ctx}/lottery/pOrderDetail/form?id=${pOrderDetail.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a> -->
<!-- 					</shiro:hasPermission> -->
<!-- 					<shiro:hasPermission name="lottery:pOrderDetail:edit"> -->
<!--     					<a href="#" onclick="openDialog('修改订单明细', '${ctx}/lottery/pOrderDetail/form?id=${pOrderDetail.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a> -->
<!--     				</shiro:hasPermission> -->
<!--     				<shiro:hasPermission name="lottery:pOrderDetail:del"> -->
<!-- 						<a href="${ctx}/lottery/pOrderDetail/delete?id=${pOrderDetail.id}" onclick="return confirmx('确认要删除该订单明细吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a> -->
<!-- 					</shiro:hasPermission> -->
<!-- 				</td> -->
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