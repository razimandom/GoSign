<%@ include file="/init.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com._42Penguins.gosign.service.EntDocLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntDoc"%>
<%@page import="com._42Penguins.gosign.service.EntKeyLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntKey"%>
<%@page import="com._42Penguins.gosign.service.EntFileUploadLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntFileUpload"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://liferay.com/tld/aui" prefix="aui"%>

<%
	long docId = ParamUtil.getLong(request, "docId");
	long fileId = ParamUtil.getLong(request, "fileId");
	EntDoc document = EntDocLocalServiceUtil.getEntDoc(docId);
	EntFileUpload fileup = EntFileUploadLocalServiceUtil.getEntFileUpload(fileId);
	request.setAttribute("document", document);
	request.setAttribute("fileup", fileup);
	String redirect = ParamUtil.getString(request, "backURL");
%>

<portlet:defineObjects />

<portlet:renderURL var="userReqProfileURL"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="userId" value="${document.userId}" />
	<portlet:param name="docId" value="${document.docId}" />
	<portlet:param name="mvcPath" value="/viewProfile.jsp" />
</portlet:renderURL>

<portlet:renderURL var="userSignProfileURL"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="userId" value="${document.signId}" />
	<portlet:param name="docId" value="${document.docId}" />
	<portlet:param name="mvcPath" value="/viewProfile.jsp" />
</portlet:renderURL>

<aui:script use="liferay-util-window">
	A.one('#popup_userReqProfile').on('click', function(event) {
		Liferay.Util.openWindow({
			dialog : {
				centered : true,
				height : 600,
				modal : true,
				width : 500
			},
			id : '<portlet:namespace />dialog',
			title : 'Requester Profile',
			uri : '<%=userReqProfileURL%>'
		});
	});
	A.one('#popup_userSignProfile').on('click', function(event) {
		Liferay.Util.openWindow({
			dialog : {
				centered : true,
				height : 600,
				modal : true,
				width : 500
			},
			id : '<portlet:namespace />dialog',
			title : 'Signer Profile',
			uri : '<%=userSignProfileURL%>'
		});
	});
</aui:script>


<portlet:resourceURL var="viewURL">
	<portlet:param name="dataId"
		value="<%=String.valueOf(fileup.getFileId())%>" />
</portlet:resourceURL>
<portlet:renderURL var="updateDocURL">

	<portlet:param name="docId" value="${document.docId}" />
	<portlet:param name="mvcPath" value="/updateDoc.jsp" />
</portlet:renderURL>

<portlet:actionURL name="doSignAction" var="doSignAction" />

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
	<h3>Status Completion:</h3>

	<c:choose>
		<c:when test="<%=document.getDoc_status().equals("Pending")%>">
			<div class="progress">
				<div class="progress-bar progress-bar-striped active "
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 40%">40% - Pending</div>
			</div>
			<div class="alert alert-info">
				Please <strong>review</strong> this signature request <strong>before ${document.doc_deadline}</strong>
			</div>
		</c:when>
		<c:when test="<%=document.getDoc_status().equals("Signed")%>">
			<div class="progress">
				<div
					class="progress-bar progress-bar-striped active progress-bar-success"
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 70%">70% - Signed</div>
			</div>
			<div class="alert alert-success">
				You have <strong>signed</strong> this document. Pending verification from signature requester.
			</div>
		</c:when>
		<c:when test="<%=document.getDoc_status().equals("Verified")%>">
			<div class="progress">
				<div
					class="progress-bar progress-bar-striped active progress-bar-info"
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 100%">100% - Verified</div>
			</div>
			<div class="alert alert-info">
				Signature has been verified by <strong>${document.req_name}</strong>. Task completed!
			</div>
		</c:when>
		<c:when test="<%=document.getDoc_status().equals("Justify")%>">
			<div class="progress">
				<div
					class="progress-bar progress-bar-striped active progress-bar-warning"
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 50%">50% - Need
					Justification</div>
			</div>
			<div class="alert alert-warning">
				Pending <strong>justification</strong> from signature requester.
			</div>
		</c:when>
		<c:when test="<%=document.getDoc_status().equals("Rejected")%>">
			<div class="progress">
				<div
					class="progress-bar progress-bar-striped active progress-bar-danger"
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 100%">100% - Rejected</div>
			</div>
			<div class="alert alert-danger">
				You have <strong>rejected</strong> this request.
			</div>
		</c:when>
	</c:choose>

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
			
			<!--  
			<tr>
				<td>Status:</td>
				<td><c:choose>
						<c:when test="<%=document.getDoc_status().equals("Pending")%>">
							<div class="text-muted">Pending</div>
						</c:when>
						<c:when test="<%=document.getDoc_status().equals("Signed")%>">
							<div class="text-success">Signed</div>
						</c:when>
						<c:when test="<%=document.getDoc_status().equals("Rejected")%>">
							<div class="text-danger">Rejected</div>
						</c:when>
						<c:when test="<%=document.getDoc_status().equals("Justify")%>">
							<div class="text-warning">Need Justification</div>
						</c:when>
					</c:choose></td>
			</tr>-->
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
		Profile:
	</h3>
	<table class="table table-hover">
		<tbody>
			<tr>
				<td width="250">Requester:</td>
				<td>

					<table>
						<tr>
							<td>
								<button id="popup_userReqProfile"
									class="btn btn-warning btn-icon" name="delDocument"
									type="submit">View Profile</button>
							</td>
							<td>${document.req_name}</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>Signer:<liferay-ui:icon-help
						message="Signer profile will be display after review this request." />
				</td>
				<td><c:choose>
						<c:when test="<%=document.getDoc_status().equals("Pending")%>">
							<i>Pending action from signer</i>
						</c:when>
						<c:otherwise>

							<table>
								<tr>
									<td>

										<button id="popup_userSignProfile"
											class="btn btn-warning btn-icon" name="delDocument"
											type="submit">View Profile</button>
									</td>
									<td>${document.sign_name}</td>
								</tr>
							</table>

						</c:otherwise>

					</c:choose></td>
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
						<button class="btn btn-primary" name="delDocument" type="submit">
							<span class="glyphicon glyphicon-download-alt"></span>&nbsp;Download
							File
						</button>
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
		<input class="btn btn-primary" type=button value=" Back" onClick="javascript: window.history.go(-1)">
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