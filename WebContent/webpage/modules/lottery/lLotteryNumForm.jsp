<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>彩票期号管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/layDate-v5.0.9/laydate/laydate.js"></script>
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
// 			laydate({
// 	            elem: '#openTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
// 	            type: 'datetime',
// 	            format:'yyyy-MM-dd HH:mm:ss',
// 	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
// 	        });

			laydate.render({
			  elem: '#openTime'
			  ,type: 'datetime'
			});
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
		<form:form id="inputForm" modelAttribute="lLotteryNum" action="${ctx}/lottery/lLotteryNum/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>彩票类型：</label></td>
					<td class="width-35">${lotteryTypeId} 
						<select id="lotteryTypeId" name="lotteryTypeId" class="form-control required">
							<option value=""></option>
							<c:forEach items="${lLotteryTyle}" var="ltype">
								<option value="${ltype.id}" <c:if test="${ltype.id==LotteryTypeId}"> selected </c:if> > ${ltype.lotteryTypeName} </option>
							</c:forEach>
						<select>
					</td>
					<td class="width-15 active"><label class="pull-right">期号：</label></td>
					<td class="width-35">
						<form:input path="lotteryNum" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">中奖号码：</label></td>
					<td class="width-35">
						<form:input path="winNum" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">开奖时间：</label></td>
		   			<td class="width-35" ><input id="openTime" name="openTime" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${lLotteryNum.openTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/> - </td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>