<%@ include file="/init.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@page import="com._42Penguins.gosign.service.EntDocLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntDoc"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!-- Start - Button styles  -->

<style>

@media (min-width: 1200px) {
    .container{
        max-width: 600px;
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

<!-- End - Button styles -->

<%
long docId = ParamUtil.getLong(request, "docId");
EntDoc document = EntDocLocalServiceUtil.getEntDoc(docId);
request.setAttribute("document", document);
%>

<portlet:actionURL name="doBack" var="doBack" />
<portlet:actionURL name="doEdit" var="doEdit" />

<div class="container">
  <h3><span class="glyphicon glyphicon-briefcase"></span>&nbsp;Request Details:</h3>            
  <table class="table table-hover">
    <tbody>
<tr>
	<td width="180">Request ID:</td>
	<td>${document.docId}</td>
</tr>
<tr>
	<td>Request Title:</td>
	<td>${document.doc_title}</td>
</tr>
<tr>
	<td>Type:</td>
	<td>${document.doc_type}</td>
</tr>
<tr>
	<td>Status:</td>
	<td>${document.doc_status}</td>
</tr>
<tr>
	<td>Date Created:</td>
	<td>${document.req_dateCreated}</td>
</tr>
<tr>
	<td>Date Modified:</td>
	<td>${document.req_dateModified}</td>
</tr>
<tr>
	<td>Deadline:</td>
	<td>${document.doc_deadline}</td>
</tr>
<tr>
	<td>Description:</td>
	<td>${document.doc_description}</td>
</tr>
    </tbody>
  </table>
</div>

<div class="container">
  <h3><span class="glyphicon glyphicon-user"></span>&nbsp;Requester & Signer Details:</h3>     
  <table class="table table-hover">
    <tbody>
<tr>
	<td width="180">Requester Name:</td>
	<td>${document.req_name}</td>
</tr>
<tr>
	<td >Requester Email:</td>
	<td>${document.req_email}</td>
</tr>
<tr>
	<td>Signer Name: </td>
	<td>${document.sign_name}</td>
</tr>
<tr>
	<td>Signer Email:</td>
	<td>${document.sign_email}</td>
</tr>
    </tbody>
  </table>
</div>

<div class="container">
  <h3><span class="glyphicon glyphicon-briefcase"></span>&nbsp;Other Details:</h3>            
  <table class="table table-hover">
    <tbody>
<tr>
	<td width="180">Request MD5:</td>
	<td>${document.doc_md5}</td>
</tr>
<tr>
	<td>Time Created: </td>
	<td>${document.req_timeCreated}</td>
</tr>
<tr>
	<td>Time Modified: </td>
	<td>${document.req_timeModified}</td>
</tr>
    </tbody>
  </table>

<br>

<table><tr><td>
	<aui:form action="<%=doBack%>" method="post" name="name">
		<aui:button name="back" type="submit" value="Back" last="true" />
	</aui:form>
</td><td>
	<aui:form action="<%=doEdit%>" method="post" name="name">
	<aui:input label="Action: " name="doAction" type="hidden" value="update" readOnly="true"/>
	<aui:input label="Doc Id: " name="docId" type="hidden" value="${document.docId}" readOnly="true"/>
	<aui:button name="update" type="submit" value="Edit Request" last="true" />
</aui:form>
</td></tr></table>

	


</div>