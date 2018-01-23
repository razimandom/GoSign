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
<%@page import="com._42Penguins.gosign.service.EntDocLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntDoc"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%
long userId = ParamUtil.getLong(request, "signId");
long docId = ParamUtil.getLong(request, "docId");
EntKey genkeyData = EntKeyLocalServiceUtil.getEntKey(userId);
EntDoc docData = EntDocLocalServiceUtil.getEntDoc(docId);
request.setAttribute("docData", docData);
request.setAttribute("genkeyData", genkeyData);
String redirect = ParamUtil.getString(request, "backURL");
%>

<style>

@media (min-width: 1200px) {
    .container{
        max-width: 800px;
    }
}

td{
	padding:5px}
	
.btn {
	border: none; /* Remove borders */
	color: white; /* Add a text color */
	padding: 8px 28px; /* Add some padding */
	cursor: pointer; /* Add a pointer cursor on mouse-over */
}
	
</style>

<div class="container">
  <h3>Signer Profile:</h3>            
  <table>
    <tbody>
<tr>
	<td width="250">Signer ID:</td>
	<td>${docData.signId}</td>
</tr>
<tr>
	<td>Signer Name:</td>
	<td>${genkeyData.sign_name}</td>
</tr>
<tr>
	<td>Signer Email:</td>
	<td>${docData.sign_email}</td>
</tr>
    </tbody>
  </table>
  <br>
 	<aui:form action="#" method="post" name="name" enctype="multipart/form-data">
	<aui:input label="Public Key: " name="pubKey" type="textarea" value="${genkeyData.getPublickey_Data()}" readonly="true" />
	</aui:form>
  	<div class="alert alert-info">
    Copy public key above to verify document that has been signed to check if the signature is valid from correct person (signer).
  	</div>
</div>
