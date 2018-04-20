<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>订单每天统计报表管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/layDate-v5.0.9/laydate/laydate.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
	        laydate.render({
				  elem: '#createDateStart'
				  ,type: 'datetime'
				});
	       laydate.render({
				  elem: '#createDateEnd'
				  ,type: 'datetime'
				});
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>订单每天统计报表</h5>
	</div>
    
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="dayOrderVo" action="${ctx}/lottery/pOrderReport/listFD" method="post" class="form-inline">
		<div class="form-group">
			<span>记录时间：</span>
				<input id="createDateStart" name="createDateStart" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${dayOrderVo.createDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"/> - 
				<input id="createDateEnd" name="createDateEnd" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${dayOrderVo.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left" style="width:50%">
			<shiro:hasPermission name="lottery:pOrderReport:export">
	       		<table:exportExcel url="${ctx}/lottery/pOrderReport/exportFD"></table:exportExcel>
	       	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			<span style="margin-left:3%">总下注金额：${sumData.totalBettingMoney}</span>
			<span style="margin-left:3%">总中奖金额：${sumData.totalWinMoney}</span>
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
				<th  class="sort-column id">序号</th>
				<th  class="sort-column orderDay">下单时间 </th>
				<th  class="sort-column totalBettingMoney">总下注金额</th>
				<th  class="sort-column totalWinMoney">总中奖金额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${dataList}" var="dayOrderVo" varStatus="status">
			<tr>
				<td>${ status.index + 1}</td>  
				<td>
						${dayOrderVo.orderDay}
				</td>
				<td>
						${dayOrderVo.totalBettingMoney}
				</td>
				<td>
						${dayOrderVo.totalWinMoney}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<br/>
	<br/>
	</div>
	</div>
</div>
</body>
</html>