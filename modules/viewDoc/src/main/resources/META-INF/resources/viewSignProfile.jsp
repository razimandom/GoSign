<%@ include file="/init.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@page import="com._42Penguins.gosign.service.EntKeyLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntKey"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%
long userId = ParamUtil.getLong(request, "signId");
EntKey genkeyData = EntKeyLocalServiceUtil.getEntKey(20156);
request.setAttribute("genkeyData", genkeyData);
String redirect = ParamUtil.getString(request, "backURL");
%>

<div class="container">
  <h3>Signer Profile:</h3>            
  <table class="table table-hover">
    <tbody>
<tr>
	<td>Signer ID:</td>
	<td></td>
</tr>
<tr>
	<td>Signer Name:</td>
	<td>${genkeyData.sign_name}</td>
</tr>
<tr>
	<td>Signer Email:</td>
	<td></td>
</tr>
<tr>
	<td>Signer Public Key:</td>
	<td>${genkeyData.publickey_Data}</td>
</tr>
    </tbody>
  </table>
</div>
