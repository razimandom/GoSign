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

<style>
@media ( min-width : 1200px) {
	.container {
		max-width: 600px;
	}
}

</style>

<%
	long userId = ParamUtil.getLong(request, "userId");
	EntKey keyData = EntKeyLocalServiceUtil.getEntKey(userId);
	request.setAttribute("keyData", keyData);
%>

<portlet:actionURL name="doBack" var="doBack" />

<div class="container">
	<h3>
		<span class="glyphicon glyphicon-lock"></span>&nbsp;Signer Key
		Details:
	</h3>
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
				<td><textarea class="form-control" readonly rows="4" cols="50">${keyData.publickey_Data}</textarea></td>
			</tr>
			<tr>
				<td>Private Key:</td>
				<td><i>For security purpose, admin cannot view private key</i></td>
			</tr>
		</tbody>
	</table>

	<aui:form action="<%=doBack%>" method="post" name="name">
		<aui:button name="back" type="submit" value="Back" last="true" />
	</aui:form>
</div>