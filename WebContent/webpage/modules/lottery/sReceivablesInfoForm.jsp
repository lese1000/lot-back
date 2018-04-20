<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>ReceivablesInfo管理</title>
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
		<form:form id="inputForm" modelAttribute="sReceivablesInfo" action="${ctx}/lottery/sReceivablesInfo/saveFile" enctype="multipart/form-data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">银行：</label></td>
					<td class="width-35">
						<form:input path="bank" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">账户名字：</label></td>
					<td class="width-35">
						<form:input path="fullName" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">开户行：</label></td>
					<td class="width-35">
						<form:input path="openingBank" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">银行卡号：</label></td>
					<td class="width-35">
						<form:input path="account" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">是否使用图片：</label></td>
					<td class="width-35">
						<select id="useImg" name="useImg" class="form-control">
							<option value="0" <c:if test="${sReceivablesInfo.useImg==0}"> selected </c:if>>否</option>
							<option value="1" <c:if test="${sReceivablesInfo.useImg==1}"> selected </c:if>>是</option>
						</select>
					</td>
					<td class="width-15 active"><label class="pull-right">图片地址：</label></td>
					<td class="width-35">
					<c:if test="${sReceivablesInfo.imgUrl!=null and sReceivablesInfo.imgUrl!=''}"> 
						<img alt="image" class="img-responsive" src="${sReceivablesInfo.imgUrl}">
					</c:if>
						<input type="file" name="file">
<!-- 						<form:input path="imgUrl" htmlEscape="false"    class="form-control "/> -->
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">收款方式</label></td>
					<td class="width-35">
						<select id="type" name="type"  class="form-control">
							<option value="1" <c:if test="${sReceivablesInfo.type==1}"> selected </c:if>>银行转账</option>
							<option value="2" <c:if test="${sReceivablesInfo.type==2}"> selected </c:if>>微信转账</option>
						</select>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>