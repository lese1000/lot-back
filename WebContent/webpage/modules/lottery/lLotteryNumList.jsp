<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>彩票期号管理</title>
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
		<h5>彩票期号列表 </h5>
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
	<form:form id="searchForm" modelAttribute="lotteryNum" action="${ctx}/lottery/lLotteryNum/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
<!-- 		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/>支持排序 -->
		<div class="form-group">
			<span>彩票类型：</span>
				<form:select path="lotteryNum"  class="form-control m-b">
					<form:option value="" label=""/>
					<c:forEach items="${lLotteryTyle}" var="ltype">
						<option value="${ltype.id}" <c:if test="${ltype.id==lotteryNum.lotteryTypeId}"> selected </c:if> > ${ltype.lotteryTypeName} </option>
					</c:forEach>
				</form:select>
			<span>期号：</span>
				<input name="lotteryNum" type="text" value="${lotteryNum.lotteryNum}" maxlength="30"  class=" form-control"/>
			<span>开奖时间：</span>
				<input id="beginCreateDate" name="beginCreateDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${lLotteryNum.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/> - 
				<input id="endCreateDate" name="endCreateDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${lLotteryNum.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="lottery:lLotteryNum:add">
				<table:addRow url="${ctx}/lottery/lLotteryNum/form" title="彩票期号"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="lottery:lLotteryNum:edit">
			    <table:editRow url="${ctx}/lottery/lLotteryNum/form" title="彩票期号" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
<!-- 			<shiro:hasPermission name="lottery:lLotteryNum:del"> -->
<!-- 				<table:delRow url="${ctx}/lottery/lLotteryNum/deleteAll" id="contentTable"></table:delRow>删除按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:lLotteryNum:import"> -->
<!-- 				<table:importExcel url="${ctx}/lottery/lLotteryNum/import"></table:importExcel> -->
<!-- 			</shiro:hasPermission> -->
			<shiro:hasPermission name="lottery:lLotteryNum:export">
	       		<table:exportExcel url="${ctx}/lottery/lLotteryNum/export"></table:exportExcel>
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
				<th  class="sort-column lotteryTypeId">彩票类型</th>
				<th  class="sort-column lotteryNum">期号</th>
				<th  class="sort-column winNum">中奖号码</th>
				<th  class="sort-column createDate">创建时间</th>
				<th  class="sort-column createDate">开奖时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lLotteryNum">
			<tr>
				<td> <input type="checkbox" id="${lLotteryNum.id}" class="i-checks"></td>
				<td>${lLotteryNum.lotteryTypeName}</td>
				<td>
					${lLotteryNum.lotteryNum}
				</td>
				<td>
					${lLotteryNum.winNum}
				</td>
				<td>
					<fmt:formatDate value="${lLotteryNum.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${lLotteryNum.openTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<shiro:hasPermission name="lottery:lLotteryNum:view">
						<a href="#" onclick="openDialogView('查看彩票期号', '${ctx}/lottery/lLotteryNum/form?id=${lLotteryNum.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="lottery:lLotteryNum:edit">
    					<a href="#" onclick="openDialog('修改彩票期号', '${ctx}/lottery/lLotteryNum/form?id=${lLotteryNum.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="lottery:lLotteryNum:del">
						<a href="${ctx}/lottery/lLotteryNum/delete?id=${lLotteryNum.id}" onclick="return confirmx('确认要删除该彩票期号吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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