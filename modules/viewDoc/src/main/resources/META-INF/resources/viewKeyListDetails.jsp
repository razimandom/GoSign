<%@ include file="/init.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@page import="com._42Penguins.gosign.service.EntKeyLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntKey"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Start - Button styles  -->

<style>
@media ( min-width : 1200px) {
	.container {
		max-width: 600px;
	}
}
</style>

<script>
	function myFunction() {
		var copyText = document.getElementById("myInput");
		copyText.select();
		document.execCommand("Copy");
		alert("Successfully copied signer public key: " + copyText.value);
	}
</script>

<!-- End - Button styles -->

<%
	long userId = ParamUtil.getLong(request, "userId");
	EntKey keyData = EntKeyLocalServiceUtil.getEntKey(userId);
	request.setAttribute("keyData", keyData);
%>

<div class="container panel panel-default">

	<div class="panel-body">
		<h4>
			<span class="glyphicon glyphicon-lock"></span>&nbsp;Signer Key
			Details:
		</h4>
		<table class="table table-hover">
			<tbody>
				<tr>
					<td width="120">Signer ID:</td>
					<td>${keyData.userId}</td>
				</tr>
				<tr>
					<td>Key Owner:</td>
					<td>${keyData.sign_name}</td>
				</tr>
				<tr>
					<td>Public Key:</td>
					<td><textarea id="myInput" class="form-control" readonly rows="4" cols="50">${keyData.publickey_Data}</textarea></td>
				</tr>
			</tbody>
		</table>

		<input class="btn btn-primary" type=button value=" Back"
			onClick="javascript: window.history.go(-1)"> 
			
		<button class="btn btn-success" onclick="myFunction()">Copy to Clipboard</button>

	</div>
</div>