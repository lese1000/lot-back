<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>ReceivablesInfo管理</title>
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
		<h5>收账账号列表 </h5>
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
	<form:form id="searchForm" modelAttribute="sReceivablesInfo" action="${ctx}/lottery/sReceivablesInfo/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
<!-- 		<div class="form-group"> -->
<!-- 			<span>bank：</span> -->
<!-- 				<form:input path="bank" htmlEscape="false" maxlength="50"  class=" form-control input-sm"/> -->
<!-- 			<span>full_name：</span> -->
<!-- 				<form:input path="fullName" htmlEscape="false" maxlength="20"  class=" form-control input-sm"/> -->
<!-- 			<span>create_date：</span> -->
<!-- 				<input id="beginCreateDate" name="beginCreateDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm" -->
<!-- 					value="<fmt:formatDate value="${sReceivablesInfo.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/> -  -->
<!-- 				<input id="endCreateDate" name="endCreateDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm" -->
<!-- 					value="<fmt:formatDate value="${sReceivablesInfo.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/> -->
<!-- 		 </div>	 -->
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="lottery:sReceivablesInfo:add">
				<table:addRow url="${ctx}/lottery/sReceivablesInfo/form" title="ReceivablesInfo"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="lottery:sReceivablesInfo:edit">
			    <table:editRow url="${ctx}/lottery/sReceivablesInfo/form" title="ReceivablesInfo" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="lottery:sReceivablesInfo:del">
				<table:delRow url="${ctx}/lottery/sReceivablesInfo/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="lottery:sReceivablesInfo:import">
				<table:importExcel url="${ctx}/lottery/sReceivablesInfo/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="lottery:sReceivablesInfo:export">
	       		<table:exportExcel url="${ctx}/lottery/sReceivablesInfo/export"></table:exportExcel><!-- 导出按钮 -->
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
				<th  class="sort-column bank">银行</th>
				<th  class="sort-column fullName">账户名字</th>
				<th  class="sort-column openingBank">开户行</th>
				<th  class="sort-column account">银行卡号</th>
				<th  class="sort-column useImg">是否使用图片</th>
				<th  class="sort-column imgUrl">图片地址</th>
				<th  class="sort-column type">收款方式</th>
				<th  class="sort-column createDate">创建时间</th>
				<th  class="sort-column updateDate">更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sReceivablesInfo">
			<tr>
				<td> <input type="checkbox" id="${sReceivablesInfo.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看ReceivablesInfo', '${ctx}/lottery/sReceivablesInfo/form?id=${sReceivablesInfo.id}','800px', '500px')">
					${sReceivablesInfo.bank}
				</a></td>
				<td>
					${sReceivablesInfo.fullName}
				</td>
				<td>
					${sReceivablesInfo.openingBank}
				</td>
				<td>
					${sReceivablesInfo.account}
				</td>
				<td>
					<c:if test="${sReceivablesInfo.useImg==0}">
						否
					</c:if>
					<c:if test="${sReceivablesInfo.useImg==1}">
						是
					</c:if>
				</td>
				<td>
					${sReceivablesInfo.imgUrl}
				</td>
				<td>
					<c:if test="${sReceivablesInfo.type==1}">
					银行转账
					</c:if>
					<c:if test="${sReceivablesInfo.type==2}">
					微信转账
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${sReceivablesInfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sReceivablesInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<shiro:hasPermission name="lottery:sReceivablesInfo:view">
						<a href="#" onclick="openDialogView('查看收账账号信息', '${ctx}/lottery/sReceivablesInfo/form?id=${sReceivablesInfo.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="lottery:sReceivablesInfo:edit">
    					<a href="#" onclick="openDialog('修改收账账号信息', '${ctx}/lottery/sReceivablesInfo/form?id=${sReceivablesInfo.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="lottery:sReceivablesInfo:del">
						<a href="${ctx}/lottery/sReceivablesInfo/delete?id=${sReceivablesInfo.id}" onclick="return confirmx('确认要删除该ReceivablesInfo吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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