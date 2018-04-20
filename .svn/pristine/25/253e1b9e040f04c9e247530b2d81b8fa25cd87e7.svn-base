<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>CashRecord管理</title>
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
		<h5>提现申请列表 </h5>
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
	<form:form id="searchForm" modelAttribute="sCashRecord" action="${ctx}/lottery/sCashRecord/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>玩家名字：</span>
				<form:input path="playerName" htmlEscape="false" maxlength="50"  class=" form-control input-sm"/>
<!-- 			<span>bank：</span> -->
<!-- 				<form:input path="bank" htmlEscape="false" maxlength="100"  class=" form-control input-sm"/> -->
			<span>申请时间：</span>
				<input id="beginCreateDate" name="beginCreateDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${sCashRecord.beginCreateDate}" pattern="yyyy-MM-dd"/>"/> - 
				<input id="endCreateDate" name="endCreateDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${sCashRecord.endCreateDate}" pattern="yyyy-MM-dd"/>"/>
			<span>审核结果</span>
				<select id="status" name="status" class=" form-control input-sm">
					<option value=""></option>
					<option value="1" <c:if test="${sCashRecord.status==1}">selected</c:if>>待审核</option>
					<option value="2" <c:if test="${sCashRecord.status==2}">selected</c:if>>提现成功</option>
					<option value="3" <c:if test="${sCashRecord.status==3}">selected</c:if>>提现失败</option>
				</select>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			
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
				<th  class="sort-column playerName">玩家姓名</th>
				<th  class="sort-column fullName">提现人</th>
				<th  class="sort-column bank">银行名称</th>
				<th  class="sort-column account">银行账户</th>
				<th  class="sort-column cashVal">提现金额</th>
				<th  class="sort-column createDate">申请时间</th>
				<th  class="sort-column status">审核结果</th>
				<th  class="sort-column status">审核人</th>
				<th  class="sort-column status">审核意见</th>
				<th  class="sort-column status">审核时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sCashRecord">
			<tr>
				<td> <input type="checkbox" id="${sCashRecord.id}" class="i-checks"></td>
				<td>
					${sCashRecord.playerName}
				</td>
				<td>
					${sCashRecord.fullName}
				</td>
				<td>
					${sCashRecord.bank}
				</td>
				<td>
					${sCashRecord.account}
				</td>
				<td>
					${sCashRecord.cashVal}
				</td>
				<td>
					<fmt:formatDate value="${sCashRecord.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${sCashRecord.status==1}"> 待审核</c:if>
						<c:if test="${sCashRecord.status==2}"> 提现成功</c:if>
						<c:if test="${sCashRecord.status==3}"> 提现失败</c:if>
				</td>
				<td>
					${sCashRecord.auditor}
				</td>
				<td>
					${sCashRecord.auditOpinion}
				</td>
				<td>
					<fmt:formatDate value="${sCashRecord.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${sCashRecord.status==1}"> 
					<shiro:hasPermission name="lottery:sCashRecord:edit">
    					<a href="#" onclick="openDialog('审核提现申请', '${ctx}/lottery/sCashRecord/form?id=${sCashRecord.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 审核提现申请</a>
    				</shiro:hasPermission>
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