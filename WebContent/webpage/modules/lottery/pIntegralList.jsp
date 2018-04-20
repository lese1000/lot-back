<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>积分管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			laydate({
	            elem: '#updateDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>积分列表 </h5>
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
	<form:form id="searchForm" modelAttribute="pIntegral" action="${ctx}/lottery/pIntegral/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
<!-- 		<div class="form-group"> -->
<!-- 			<span>update_date：</span> -->
<!-- 				<input id="updateDate" name="updateDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm" -->
<!-- 					value="<fmt:formatDate value="${pIntegral.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/> -->
<!-- 			<span>player_id：</span> -->
<!-- 				<form:input path="playerId" htmlEscape="false" maxlength="20"  class=" form-control input-sm"/> -->
<!-- 		 </div>	 -->
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="lottery:pIntegral:add">
				<table:addRow url="${ctx}/lottery/pIntegral/form" title="积分"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="lottery:pIntegral:edit">
			    <table:editRow url="${ctx}/lottery/pIntegral/form" title="积分" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="lottery:pIntegral:del">
				<table:delRow url="${ctx}/lottery/pIntegral/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="lottery:pIntegral:import">
				<table:importExcel url="${ctx}/lottery/pIntegral/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="lottery:pIntegral:export">
	       		<table:exportExcel url="${ctx}/lottery/pIntegral/export"></table:exportExcel><!-- 导出按钮 -->
	       	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
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
				<th  class="sort-column integralId">integral_id</th>
				<th  class="sort-column integralVal">积分值</th>
				<th  class="sort-column updateDate">update_date</th>
				<th  class="sort-column playerId">player_id</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pIntegral">
			<tr>
				<td> <input type="checkbox" id="${pIntegral.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看积分', '${ctx}/lottery/pIntegral/form?id=${pIntegral.id}','800px', '500px')">
					${pIntegral.integralId}
				</a></td>
				<td>
					${pIntegral.integralVal}
				</td>
				<td>
					<fmt:formatDate value="${pIntegral.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pIntegral.playerId}
				</td>
				<td>
					<shiro:hasPermission name="lottery:pIntegral:view">
						<a href="#" onclick="openDialogView('查看积分', '${ctx}/lottery/pIntegral/form?id=${pIntegral.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="lottery:pIntegral:edit">
    					<a href="#" onclick="openDialog('修改积分', '${ctx}/lottery/pIntegral/form?id=${pIntegral.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="lottery:pIntegral:del">
						<a href="${ctx}/lottery/pIntegral/delete?id=${pIntegral.id}" onclick="return confirmx('确认要删除该积分吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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