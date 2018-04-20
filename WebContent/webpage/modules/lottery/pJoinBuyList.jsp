<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>JoinBuy管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			laydate({
	            elem: '#beginCreateDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
	        laydate({
	            elem: '#endCreateDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>合买列表 </h5>
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
	<form:form id="searchForm" modelAttribute="pJoinBuy" action="${ctx}/lottery/pJoinBuy/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input  name="orderId" type="hidden" value="${pJoinBuy.orderId}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>方案类型</span>
				<select id="planType"  name="planType" class="form-control m-b">
					<option value=""></option>
					<option value="1" <c:if test="${pJoinBuy.planType==1}">selected</c:if>>发布即公开</option>
					<option value="2" <c:if test="${pJoinBuy.planType==2}">selected</c:if>>截止后公开</option>
					<option value="3" <c:if test="${pJoinBuy.planType==3}">selected</c:if>>完全保密</option>
				</select>
			<span>发起时间：</span>
				<input id="beginCreateDate" name="beginCreateDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${pJoinBuy.beginCreateDate}" pattern="yyyy-MM-dd"/>"/> - 
				<input id="endCreateDate" name="endCreateDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${pJoinBuy.endCreateDate}" pattern="yyyy-MM-dd"/>"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
<!-- 			<shiro:hasPermission name="lottery:pJoinBuy:add"> -->
<!-- 				<table:addRow url="${ctx}/lottery/pJoinBuy/form" title="JoinBuy"></table:addRow>增加按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pJoinBuy:edit"> -->
<!-- 			    <table:editRow url="${ctx}/lottery/pJoinBuy/form" title="JoinBuy" id="contentTable"></table:editRow>编辑按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pJoinBuy:del"> -->
<!-- 				<table:delRow url="${ctx}/lottery/pJoinBuy/deleteAll" id="contentTable"></table:delRow>删除按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pJoinBuy:import"> -->
<!-- 				<table:importExcel url="${ctx}/lottery/pJoinBuy/import"></table:importExcel>导入按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pJoinBuy:export"> -->
<!-- 	       		<table:exportExcel url="${ctx}/lottery/pJoinBuy/export"></table:exportExcel>导出按钮 -->
<!-- 	       	</shiro:hasPermission> -->
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
				<th  class="sort-column orderId">订单ID</th>
				<th  class="sort-column singlePieceMoney">每份单价</th>
				<th  class="sort-column totalPieceNum">总份数</th>
				<th  class="sort-column remainingPieceNum">剩余份数</th>
				<th  class="sort-column planType">方案类型</th>
				<th  class="sort-column leastNum">保底份数</th>
				<th  class="sort-column createDate">发起时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pJoinBuy">
			<tr>
				<td> <input type="checkbox" id="${pJoinBuy.id}" class="i-checks"></td>
				<td>
					${pJoinBuy.orderId}
				</td>
				<td>
					${pJoinBuy.singlePieceMoney}
				</td>
				<td>
					${pJoinBuy.totalPieceNum}
				</td>
				<td>
					${pJoinBuy.remainingPieceNum}
				</td>
				<td>
					<c:if test="${pJoinBuy.planType==1 }">发布即公开</c:if>
					<c:if test="${pJoinBuy.planType==2 }">截止后公开</c:if>
					<c:if test="${pJoinBuy.planType==3 }">完全保密</c:if>
					
				</td>
				<td>
					${pJoinBuy.leastNum}
				</td>
				<td>
					<fmt:formatDate value="${pJoinBuy.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<shiro:hasPermission name="lottery:pOrder:view">
						<a href="${ctx}/lottery/pJoinBuyDetail/?joinBuyId=${pJoinBuy.id}" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看合买详情</a>
					</shiro:hasPermission>
<!-- 					<shiro:hasPermission name="lottery:pJoinBuy:view"> -->
<!-- 						<a href="#" onclick="openDialogView('查看JoinBuy', '${ctx}/lottery/pJoinBuy/form?id=${pJoinBuy.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a> -->
<!-- 					</shiro:hasPermission> -->
<!-- 					<shiro:hasPermission name="lottery:pJoinBuy:edit"> -->
<!--     					<a href="#" onclick="openDialog('修改JoinBuy', '${ctx}/lottery/pJoinBuy/form?id=${pJoinBuy.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a> -->
<!--     				</shiro:hasPermission> -->
<!--     				<shiro:hasPermission name="lottery:pJoinBuy:del"> -->
<!-- 						<a href="${ctx}/lottery/pJoinBuy/delete?id=${pJoinBuy.id}" onclick="return confirmx('确认要删除该JoinBuy吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a> -->
<!-- 					</shiro:hasPermission> -->
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