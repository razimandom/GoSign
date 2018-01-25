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
<%@page import="com.liferay.portal.kernel.model.User" %>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%
long userId = ParamUtil.getLong(request, "userId");
long docId = ParamUtil.getLong(request, "docId");
//EntKey genkeyData = EntKeyLocalServiceUtil.getEntKey(userId);
EntDoc docData = EntDocLocalServiceUtil.getEntDoc(docId);
request.setAttribute("docData", docData);
//request.setAttribute("genkeyData", genkeyData);
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
  
  	<table><tr><td>
  	
  	<liferay-ui:user-display
        markupView="lexicon"
        showUserDetails="false"
        showUserName="false"
		userId="${docData.userId}"
        userName="${docData.sign_name}"/>
  	
  	</td>
  	<td>
  
  <h3>Signer Profile:</h3></td></tr></table>            
  <table class="table table-hover">
    <tbody>
<tr>
	<td width="100">User ID:</td>
	<td>${docData.signId}</td>
</tr>
<tr>
	<td>Full Name:</td>
	<td>${genkeyData.sign_name}</td>
</tr>
<tr>
	<td>Email:</td>
	<td>${docData.sign_email}</td>
</tr>
    </tbody>
  </table>
   <div class="alert alert-info">
    Copy public key below to verify document that has been signed.
  	</div>
  <h3>Public Key: </h3>
 	<aui:form action="#" method="post" name="name" enctype="multipart/form-data">
	<aui:input label="Public Key: " name="pubKey" type="textarea" value="${genkeyData.getPublickey_Data()}" readonly="true" />
	</aui:form>
	
	<input class="btn btn-primary" type=button value=" Back" onClick="javascript: window.history.go(-1)">
	
</div>

