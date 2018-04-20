<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>玩家账号管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
		function nextPlayer(id){
			if(id){
				$("#parent").val(id);
			}else{
				$("#parent").val("");
			}
			$("#searchForm").submit();
		}
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>玩家账号列表 </h5>
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
	<form:form id="searchForm" modelAttribute="pPlayer" action="${ctx}/sys/pPlayer/" method="post" class="form-inline">
		<input id="parent" name="parent" type="hidden" value="${pPlayer.parent}"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>名字：</span>
			<form:input path="playerName" htmlEscape="false" maxlength="100"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<c:if test="${pPlayer.parent==null}">
				<shiro:hasPermission name="sys:pPlayer:add">
					<table:addRow url="${ctx}/sys/pPlayer/form" title="玩家账号"></table:addRow><!-- 增加按钮 -->
				</shiro:hasPermission>
			</c:if>
			<shiro:hasPermission name="sys:pPlayer:edit">
			    <table:editRow url="${ctx}/sys/pPlayer/form" title="玩家账号" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:pPlayer:del">
				<table:delRow url="${ctx}/sys/pPlayer/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
<!-- 			<shiro:hasPermission name="sys:pPlayer:import"> -->
<!-- 				<table:importExcel url="${ctx}/sys/pPlayer/import"></table:importExcel>导入按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="sys:pPlayer:export"> -->
<!-- 	       		<table:exportExcel url="${ctx}/sys/pPlayer/export"></table:exportExcel>导出按钮 -->
<!-- 	       	</shiro:hasPermission> -->
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="nextPlayer()" ><i class="fa fa-search"></i> 返回上一级</button>
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
				<th  class="sort-column playerId">ID</th>
				<th  class="sort-column playerName">名字</th>
				<th  class="sort-column playerType">玩家类型</th>
				<th  class="sort-column createDate">创建时间</th>
				<th  class="sort-column parent">上家</th>
				<th  class="sort-column balanceVal">余额</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pPlayer">
			<tr>
				<td> <input type="checkbox" id="${pPlayer.id}" class="i-checks"></td>
				<td>
					${pPlayer.id}
				</td>
				<td><a  href="#" onclick="openDialogView('查看玩家账号', '${ctx}/sys/pPlayer/form?id=${pPlayer.id}','800px', '500px')">
					${pPlayer.playerName}
				</a></td>
				<td>
					<c:if test="${pPlayer.playerType==1}">
					普通玩家
					</c:if>
					<c:if test="${pPlayer.playerType==2}">
					推广员
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${pPlayer.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pPlayer.parentName}
				</td>
				<td>
					${pPlayer.balanceVal}
				</td>
				<td>
					<shiro:hasPermission name="sys:pPlayer:view">
						<a href="#" onclick="openDialog('修改余额', '${ctx}/lottery/pBalanceRecord/form?playerId=${pPlayer.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 修改余额</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="sys:pPlayer:view">
						<a href="#" onclick="openDialogView('查看玩家账号', '${ctx}/sys/pPlayer/form?id=${pPlayer.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="sys:pPlayer:view">
<!-- 						<a href="${ctx}/sys/pPlayer/nextPlayerList?id=${pPlayer.id}" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看下家账号</a> -->
						<a href="#" onclick="nextPlayer(${pPlayer.id})" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看下家账号</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="sys:pPlayer:edit">
    					<a href="#" onclick="openDialog('修改玩家账号', '${ctx}/sys/pPlayer/form?id=${pPlayer.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="sys:pPlayer:del">
						<a href="${ctx}/sys/pPlayer/delete?id=${pPlayer.id}" onclick="return confirmx('确认要删除该玩家账号吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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