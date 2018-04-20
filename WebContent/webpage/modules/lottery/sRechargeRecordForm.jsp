<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>RechargeRecord管理</title>
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
			
			$("#actualAccount").bind("keyup",function(){  
				var aval=$("#actualAccount").val();
				aval=aval.replace(/[^\-?\d.]/g,'').replace(/\.{2,}/g,".").replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');  
                $("#actualAccount").val(aval);  
                
//                 obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是  
//                 obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
//                 obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
//                 obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数  

              });  
			
		});
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="sRechargeRecord" action="${ctx}/lottery/sRechargeRecord/audit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		 	  	<tr>
					<td class="width-15 active"><label class="pull-right">玩家名称：</label></td>
					<td class="width-35">
						<input name="playerName" type="text" readonly="readonly"  value="${sRechargeRecord.playerName}"  class="form-control "/>
					</td>
				</tr>
		 	  	<tr>
					<td class="width-15 active"><label class="pull-right">充值金额：</label></td>
					<td class="width-35">
						<input name="rechargeVal" type="text" readonly="readonly"  value="${sRechargeRecord.rechargeVal}"  class="form-control "/>
					</td>
				</tr>
		 	  	<tr>
					<td class="width-15 active"><label class="pull-right">实际到账：</label></td>
					<td class="width-35">
						<input id="actualAccount" name="actualAccount"  type="text"  value="${sRechargeRecord.actualAccount}"  class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">审核结果：</label></td>
					<td class="width-35">
						<select id="status" name="status" class="form-control " >
							<option value="2">充值成功</option>
							<option value="3">充值失败</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-35">
						<form:input path="auditOpinion" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>