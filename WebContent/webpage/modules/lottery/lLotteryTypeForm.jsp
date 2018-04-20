<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>彩票种类管理</title>
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
		<form:form id="inputForm" modelAttribute="lLotteryType" action="${ctx}/lottery/lLotteryType/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">规则：</label></td>
					<td class="width-35">
						<form:select path="ruleId" class="form-control ">
							<form:option value="" label=""/>
							
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">彩票种类：</label></td>
					<td class="width-35">
						<form:input path="lotteryTypeName" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">投注单价：</label></td>
					<td class="width-35">
						<form:input path="singlePrice" htmlEscape="false"    class="form-control "/>
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