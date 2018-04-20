<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>彩票规则规则管理</title>
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
		<h5>彩票规则列表 </h5>
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
	<form:form id="searchForm" modelAttribute="lLotteryRule" action="${ctx}/lottery/lLotteryRule/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input  name="lotteryTypeId" type="hidden" value="${lLotteryRule.lotteryTypeId}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
<!-- 			<span>彩票规则名称：</span> -->
<!-- 				<form:input path="lotteryTypeName" htmlEscape="false" maxlength="255"  class=" form-control input-sm"/> -->
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="lottery:lLotteryRule:add">
				<table:addRow url="${ctx}/lottery/lLotteryRule/form?lotteryTypeId=${lLotteryRule.lotteryTypeId}" title="彩票规则"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="lottery:lLotteryRule:edit">
			    <table:editRow url="${ctx}/lottery/lLotteryRule/form" title="彩票规则" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="lottery:lLotteryRule:del">
				<table:delRow url="${ctx}/lottery/lLotteryRule/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
<!-- 			<shiro:hasPermission name="lottery:lLotteryRule:import"> -->
<!-- 				<table:importExcel url="${ctx}/lottery/lLotteryRule/import"></table:importExcel>导入按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:lLotteryRule:export"> -->
<!-- 	       		<table:exportExcel url="${ctx}/lottery/lLotteryRule/export"></table:exportExcel>导出按钮 -->
<!-- 	       	</shiro:hasPermission> -->
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
			<a href="${ctx}/lottery/lLotteryType/" class="btn btn-primary btn-rounded btn-outline btn-sm" ><i class="fa fa-search-plus"></i> 返回彩票种类列表</a>
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
				<th  class="sort-column lotteryTypeName">彩票类型名称</th>
				<th  class="sort-column ruleName">规则名称</th>
				<th  class="sort-column winPrize">中奖金额</th>
				<th  class="sort-column plusAwards">加奖金额</th>
				<th  class="sort-column playType">玩法类型</th>
				<th  class="sort-column selectNumber">选号个数</th>
				<th  class="sort-column status">是否启用</th>
				<th  class="sort-column remark">备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lLotteryRule">
			<tr>
				<td> <input type="checkbox" id="${lLotteryRule.id}" class="i-checks"></td>
				<td>
					${lLotteryRule.lotteryTypeName}
				</td>
				<td>
					${lLotteryRule.ruleName}
				</td>
				<td>
					${lLotteryRule.winPrize}
				</td>
				<td>
					${lLotteryRule.plusAwards}
				</td>
				<td>
					<c:if test="${lLotteryRule.playType==0}">任选</c:if>
					<c:if test="${lLotteryRule.playType==1}">组选</c:if>
					<c:if test="${lLotteryRule.playType==2}">直选</c:if>
				</td>
				<td>
					${lLotteryRule.selectNumber}
				</td>
				<td>
					<c:if test="${lLotteryRule.status==0}">否</c:if>
					<c:if test="${lLotteryRule.status==1}">是</c:if>
				</td>
				<td>
					${lLotteryRule.remark}
				</td>
				<td>
					<shiro:hasPermission name="lottery:lLotteryRule:view">
						<a href="#" onclick="openDialogView('查看彩票规则', '${ctx}/lottery/lLotteryRule/form?id=${lLotteryRule.id}','600px', '300px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="lottery:lLotteryRule:edit">
    					<a href="#" onclick="openDialog('修改彩票规则', '${ctx}/lottery/lLotteryRule/form?id=${lLotteryRule.id}','600px', '300px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="lottery:lLotteryRule:del">
						<a href="${ctx}/lottery/lLotteryRule/delete?id=${lLotteryRule.id}" onclick="return confirmx('确认要删除该彩票规则吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
					</shiro:hasPermission>
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