<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>余额记录管理</title>
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
		<h5>余额记录列表 </h5>
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
	<form:form id="searchForm" modelAttribute="pBalanceRecord" action="${ctx}/lottery/pBalanceRecord/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>余额操作类型：</span>
				<form:select path="balanceType"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:option value="1" label="充值"/>
					<form:option value="2" label="提现"/>
					<form:option value="3" label="消费"/>
					<form:option value="4" label="中奖"/>
					<form:option value="5" label="活动奖励"/>
					<form:option value="6" label="积分兑换"/>
				</form:select>
				<span>玩家名字：</span>
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
<!-- 			<shiro:hasPermission name="lottery:pBalanceRecord:add"> -->
<!-- 				<table:addRow url="${ctx}/lottery/pBalanceRecord/form" title="余额记录"></table:addRow>增加按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pBalanceRecord:edit"> -->
<!-- 			    <table:editRow url="${ctx}/lottery/pBalanceRecord/form" title="余额记录" id="contentTable"></table:editRow>编辑按钮 -->
<!-- 			</shiro:hasPermission> -->
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
<!-- 				<th> <input type="checkbox" class="i-checks"></th> -->
				<th  class="sort-column id">序号</th>
				<th  class="sort-column playerName">客户</th>
				<th  class="sort-column balanceType">余额操作类型</th>
				<th  class="sort-column orderId">订单</th>
				<th  class="sort-column balanceVal">金额</th>
				<th  class="sort-column createDate">时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pBalanceRecord" varStatus="status">
			<tr>
<!-- 				<td> <input type="checkbox" id="${pBalanceRecord.id}" class="i-checks"></td> -->
				<td>${ status.index + 1}</td>  
				<td>
					${pBalanceRecord.playerName}
				</td>
				<td>
					<c:if test="${pBalanceRecord.balanceType==1}">
						充值
					</c:if>
					<c:if test="${pBalanceRecord.balanceType==2}">
						提现
					</c:if>
					<c:if test="${pBalanceRecord.balanceType==3}">
						消费
					</c:if>
					<c:if test="${pBalanceRecord.balanceType==4}">
						中奖
					</c:if>
					<c:if test="${pBalanceRecord.balanceType==5}">
						活动奖励
					</c:if>
					<c:if test="${pBalanceRecord.balanceType==6}">
						积分兑换
					</c:if>
				</td>
				<td>
					${pBalanceRecord.orderId}
				</td>
				<td>
					${pBalanceRecord.balanceVal}
				</td>
				<td>
					<fmt:formatDate value="${pBalanceRecord.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
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