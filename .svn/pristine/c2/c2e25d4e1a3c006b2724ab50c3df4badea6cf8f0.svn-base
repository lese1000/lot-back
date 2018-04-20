<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>余额记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
		  }
	
		  return false;
		}
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
		});
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="pBalanceRecord" action="${ctx}/lottery/pBalanceRecord/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="playerId"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">余额操作类型</label></td>
					<td class="width-35">
						<select name="balanceType" class="form-control ">
							<option value=""></option>
							<option value="1" <c:if test="${pBalanceRecord.balanceType==1}"> selected</c:if> >充值 </option>
							<option value="2" <c:if test="${pBalanceRecord.balanceType==2}"> selected</c:if> >提现</option>
							<option value="3" <c:if test="${pBalanceRecord.balanceType==3}"> selected</c:if> >消费</option>
							<option value="4" <c:if test="${pBalanceRecord.balanceType==4}"> selected</c:if> >中奖</option>
							<option value="5" <c:if test="${pBalanceRecord.balanceType==5}"> selected</c:if> >活动奖励</option>
							<option value="6" <c:if test="${pBalanceRecord.balanceType==6}"> selected</c:if> >积分兑换</option>
						<select>
					</td>
					<td class="width-15 active"><label class="pull-right">余额：</label></td>
					<td class="width-35">
						<form:input path="balanceVal" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>