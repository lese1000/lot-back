<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/common/contabs.js" type="text/javascript"></script>
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
		
		function toJoinBuy(id){
			openTab("${ctx}/lottery/pJoinBuyDetail/detailList?orderId="+id,"合买订单详情",false);
		}
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>订单列表 </h5>
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
	<form:form id="searchForm" modelAttribute="pOrder" action="${ctx}/lottery/pOrder/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>订单号：</span>
				<form:input path="orderNum" htmlEscape="false" maxlength="50"  class=" form-control input-sm"/>
			<span>彩票期号：</span>
				<form:input path="lotteryNum" htmlEscape="false" maxlength="30"  class=" form-control input-sm"/>
			<span>玩家姓名：</span>
				<form:input path="playerName" htmlEscape="false" maxlength="100"  class=" form-control input-sm"/>
				<span>是否合买：</span>
				<select id="isJoinBuy" name="isJoinBuy" class=" form-control input-sm">
					<option value=""></option>
					<option value="0" <c:if test="${pOrder.isJoinBuy==0}">selected</c:if>>否</option>
					<option value="1" <c:if test="${pOrder.isJoinBuy==1}">selected</c:if>>是</option>
				</select>
				<span>订单状态：</span>
				<select id="orderStatus" name="orderStatus" class=" form-control input-sm">
					<option value=""></option>
					<option value="0" <c:if test="${pOrder.orderStatus==0}">selected</c:if>>未开奖</option>
					<option value="1" <c:if test="${pOrder.orderStatus==1}">selected</c:if>>已派奖</option>
				</select>
		</div>	
		<div class="form-group">
			<span>下单时间：</span>
				<input id="beginCreateDate" name="beginCreateDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${pOrder.beginCreateDate}" pattern="yyyy-MM-dd"/>"/> - 
				<input id="endCreateDate" name="endCreateDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${pOrder.endCreateDate}" pattern="yyyy-MM-dd"/>"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
<!-- 			<shiro:hasPermission name="lottery:pOrder:add"> -->
<!-- 				<table:addRow url="${ctx}/lottery/pOrder/form" title="订单"></table:addRow>增加按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pOrder:edit"> -->
<!-- 			    <table:editRow url="${ctx}/lottery/pOrder/form" title="订单" id="contentTable"></table:editRow>编辑按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pOrder:del"> -->
<!-- 				<table:delRow url="${ctx}/lottery/pOrder/deleteAll" id="contentTable"></table:delRow>删除按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pOrder:import"> -->
<!-- 				<table:importExcel url="${ctx}/lottery/pOrder/import"></table:importExcel>导入按钮 -->
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="lottery:pOrder:export"> -->
<!-- 	       		<table:exportExcel url="${ctx}/lottery/pOrder/export"></table:exportExcel>导出按钮 -->
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
				<th  class="sort-column orderNum">订单号</th>
				<th  class="sort-column lotteryType">彩票种类</th>
				<th  class="sort-column lotteryNum">彩票期号</th>
				<th  class="sort-column playerName">玩家姓名</th>
				<th  class="sort-column isJoinBuy">是否合买</th>
				<th  class="sort-column orderStatus">订单状态</th>
				<th  class="sort-column createTime">下单时间</th>
				<th  class="sort-column totalBettingMoney">投注总额</th>
				<th  class="sort-column totalWinMoney">中奖总额</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pOrder">
			<tr>
				<td> <input type="checkbox" id="${pOrder.id}" class="i-checks"></td>
				<td>
					${pOrder.id}
				</td>
				<td>
					${pOrder.orderNum}
				</td>
				<td>
					${pOrder.lotteryType}
				</td>
				<td>
					${pOrder.lotteryNum}
				</td>
				<td>
					${pOrder.playerName}
				</td>
				<td>
					<c:if test="${pOrder.isJoinBuy==0}">
						否
					</c:if>
					<c:if test="${pOrder.isJoinBuy==1}">
						是
					</c:if>
				</td>
				<td>
					<c:if test="${pOrder.orderStatus==0}">
						未开奖
					</c:if>
					<c:if test="${pOrder.orderStatus==1}">
						已开奖
					</c:if>
					
				</td>
				<td>
					<fmt:formatDate value="${pOrder.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pOrder.totalBettingMoney}
				</td>
				<td>
					${pOrder.totalWinMoney}
				</td>
				<td>
					<shiro:hasPermission name="lottery:pOrder:view">
						<a href="${ctx}/lottery/pOrderDetail/?orderId=${pOrder.id}" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看订单详情</a>
					</shiro:hasPermission>
<!-- 					<shiro:hasPermission name="lottery:pOrder:edit"> -->
<!--     					<a href="#" onclick="openDialog('修改订单', '${ctx}/lottery/pOrder/form?id=${pOrder.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a> -->
<!--     				</shiro:hasPermission> -->
<!--     				<shiro:hasPermission name="lottery:pOrder:del"> -->
<!-- 						<a href="${ctx}/lottery/pOrder/delete?id=${pOrder.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a> -->
<!-- 					</shiro:hasPermission> -->
					<c:if test="${pOrder.isJoinBuy==1}">
						<a href="#" onclick="toJoinBuy(${pOrder.id})" class="btn btn-success btn-xs" ><i class="fa fa-search-plus"></i> 查看合买订单</a>
					</c:if>

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