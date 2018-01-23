<%@ include file="/init.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@page import="com._42Penguins.gosign.service.EntDocLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntDoc"%>
<%@page
	import="com._42Penguins.gosign.service.EntFileUploadLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntFileUpload"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<portlet:defineObjects />

<%
	long docId = ParamUtil.getLong(request, "docId");
	long fileId = ParamUtil.getLong(request, "fileId");
	EntDoc document = EntDocLocalServiceUtil.getEntDoc(docId);
	EntFileUpload fileup = EntFileUploadLocalServiceUtil.getEntFileUpload(fileId);
	request.setAttribute("document", document);
	request.setAttribute("fileup", fileup);
	String redirect = ParamUtil.getString(request, "backURL");
%>

<!--  

<script src="https://cdn.alloyui.com/3.0.1/aui/aui-min.js"></script>
<link href="https://cdn.alloyui.com/3.0.1/aui-css/css/bootstrap.min.css"></link>-->

<portlet:resourceURL var="viewURL">
	<portlet:param name="dataId"
		value="<%=String.valueOf(fileup.getFileId())%>" />
</portlet:resourceURL>
<portlet:renderURL var="updateDocURL">

	<portlet:param name="docId" value="${document.docId}" />
	<portlet:param name="mvcPath" value="/updateDoc.jsp" />
</portlet:renderURL>

<portlet:actionURL name="doSignAction" var="doSignAction" />
<portlet:actionURL name="doBack" var="doBack" />

<style type="text/css">
td {
	padding: 5px
}

.btn {
	border: none; /* Remove borders */
	color: white; /* Add a text color */
	padding: 8px 28px; /* Add some padding */
	cursor: pointer; /* Add a pointer cursor on mouse-over */
}
</style>


<div class="container">
	<h3>
		<span class="glyphicon glyphicon-briefcase"></span>&nbsp;Request
		Details:
	</h3>
	<table class="table table-hover">
		<tbody>
			<tr>
				<td width="250">Request ID:</td>
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
				<td>Description/Justification:</td>
				<td>${document.doc_description}</td>
			</tr>
		</tbody>
	</table>

	<h3>
		<span class="glyphicon glyphicon-user"></span>&nbsp;Requester & Signer
		Details:
	</h3>
	<table class="table table-hover">
		<tbody>
			<tr>
				<td width="250">Requester Name:</td>
				<td>${document.req_name}</td>
			</tr>
			<tr>
				<td>Requester Email:</td>
				<td>${document.req_email}</td>
			</tr>
			<tr>
				<td>Signer Name:</td>
				<td>${document.sign_name}
				</td>
			</tr>
			<tr>
				<td>Signer Email:</td>
				<td>${document.sign_email}</td>
			</tr>
		</tbody>
	</table>

	<h3>
		<span class="glyphicon glyphicon-folder-open"></span>&nbsp;&nbsp;Uploaded
		Document:
	</h3>
	<table class="table table-hover">
		<tbody>
			<tr>
				<td width="250">File Name:</td>
				<td>${fileup.file_name}</td>
			</tr>
			<tr>
				<td>Download:</td>
				<td><aui:form action="<%=viewURL.toString()%>" method="post"
						name="name">
						<button class="btn btn-primary" name="delDocument" type="submit">Download
							File</button>
					</aui:form></td>

			</tr>
		</tbody>
	</table>

	<h3>
		<span class="glyphicon glyphicon-briefcase"></span>&nbsp;Other
		Details:
	</h3>
	<table class="table table-hover">
		<tbody>
			<tr>
				<td width="250">Request MD5:</td>
				<td>${document.doc_md5}</td>
			</tr>
			<tr>
				<td>Time Created:</td>
				<td>${document.req_timeCreated}</td>
			</tr>
			<tr>
				<td>Time Modified:</td>
				<td>${document.req_timeModified}</td>
			</tr>
		</tbody>
	</table>

	<br>

	<div class="alert alert-warning">
		<strong>Reminder!</strong> You cannot edit your response after you
		have rejected or signed the document.
	</div>

	<td>
		<button type="button" class="btn btn-primary">Back</button>
	</td>
	<td>
		<button type="button" class="btn btn-danger" data-toggle="collapse"
			data-target="#delete">Reject</button>
	</td>
	<td>
		<button type="button" class="btn btn-warning" data-toggle="collapse"
			data-target="#justify">Request Justification</button>
	</td>
	<td>
		<button type="button" class="btn btn-success" data-toggle="collapse"
			data-target="#sign">Sign Document</button>
	</td>

	<div id="delete" class="collapse">

		<br>
		<div class="alert alert-danger">
			<span class="glyphicon glyphicon-exclamation-sign"></span>&nbsp;Are
			you sure you want to reject this request?
		</div>
		<aui:form action="<%=doSignAction%>" method="post" name="name">
			<aui:input label="Doc Id: " name="docId" type="hidden"
				value="${document.docId}" readOnly="true" />
			<aui:input label="File Id: " name="fileId" type="hidden"
				value="${fileup.fileId}" readOnly="true" />
			<aui:input label="Action: " name="doAction" type="hidden"
				value="Reject" readOnly="true" />
			<aui:button cssClass="btn btn-danger" name="reject" type="submit"
				value="Yes. Reject this request." last="true" />
		</aui:form>

	</div>



	<div id="justify" class="collapse">
		<br>
		<h3>Request for justification:</h3>

		<portlet:actionURL name="updateDoc" var="updateDoc" />

		<aui:form action="<%=doSignAction%>" method="post" name="name">
			<aui:input label="Doc Id: " name="docId" type="hidden"
				value="${document.docId}" readOnly="true" />
			<aui:input label="File Id: " name="fileId" type="hidden"
				value="${fileup.fileId}" readOnly="true" />
			<aui:input label="Doc Id: " name="doc_status" type="hidden"
				value="${document.doc_status}" readOnly="true" />
			<aui:input label="Action: " name="doAction" type="hidden"
				value="Justify" readOnly="true" />
			<aui:input label="Add comments: " type="textarea"
				name="justificationMsg"
				value="Please provide more justification on this request." />
			<aui:button name="req_justification" type="submit" value="Send Email"
				last="true" onClick="return confirm('Proceed to send email?')" />
		</aui:form>

	</div>



	<div id="sign" class="collapse">
		<br>
		<h3>Sign Document:</h3>

		<aui:form action="<%=doSignAction%>" method="post" name="name"
			enctype="multipart/form-data">
			<aui:input label="Enter 6 pin: " name="userPin" type="type">
				<aui:validator name="required" />
				<aui:validator name="digits" />
			</aui:input>
			<aui:input label="Action: " name="doAction" type="hidden"
				value="Sign" readOnly="true" />
			<aui:input label="Doc Id: " name="docId" type="hidden"
				value="${document.docId}" readOnly="true" />
			<aui:input label="File Id: " name="fileId" type="hidden"
				value="${fileup.fileId}" readOnly="true" />
			<aui:input label="Doc Id: " name="doc_status" type="hidden"
				value="${document.doc_status}" readOnly="true" />
			<aui:button cssClass="btn btn-success" name="sign" type="submit"
				value="Sign Document" last="true"
				onClick="return confirm('Are you sure to sign this document?')" />
		</aui:form>

	</div>

</div>


<liferay-ui:error key="error-key-signFail"
	message="Invalid pin! Your 6 digits pin does not match with registered pin." />
<liferay-ui:error key="error-key-invalidPinFormat"
	message="Error! Please enter your 6 digits pin." />
<liferay-ui:error key="error-key-statusFail"
	message="Unable to process your request. This document has been rejected or signed." />
<liferay-ui:error key="error-key-statusInvalid"
	message="Error! Action does not exist! Please contact system admin." />