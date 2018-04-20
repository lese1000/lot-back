<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>彩票种类规则管理</title>
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
		<form:form id="inputForm" modelAttribute="lLotteryRule" action="${ctx}/lottery/lLotteryRule/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="lotteryTypeId"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><font color="red">*</font><label class="pull-right">规则：</label></td>
					<td class="width-35">
						<form:select path="ruleId" class="form-control required">
							<c:forEach items="${ruleList}" var="rule">
								<form:option value="${rule.id}" label="${rule.ruleName}"/>
							</c:forEach>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">是否启用：</label></td>
					<td class="width-35">
						<form:select path="status" class="form-control ">
							<form:option value="0" label="否"/>
							<form:option value="1" label="是"/>
						</form:select>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">加奖金额：</label></td>
					<td class="width-35">
						<form:input path="plusAwards" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right"></label></td>
					<td class="width-35">
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>