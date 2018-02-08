<%@ include file="/init.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com._42Penguins.gosign.service.EntDocLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntDoc"%>
<%@page
	import="com._42Penguins.gosign.service.EntFileUploadLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntFileUpload"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="java.time.temporal.ChronoUnit" %>

<%
long docId = ParamUtil.getLong(request, "docId");
long fileId = ParamUtil.getLong(request, "fileId");
long userId = ParamUtil.getLong(request, "signId");
EntDoc docData = EntDocLocalServiceUtil.getEntDoc(docId);
EntFileUpload fileData = EntFileUploadLocalServiceUtil.getEntFileUpload(fileId);
request.setAttribute("docData", docData);
request.setAttribute("fileData", fileData);

LocalDate nowDate = LocalDate.now();
String deadlineDate = docData.getDoc_deadline();

DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate deadlineDateFormat = LocalDate.parse(deadlineDate, formatterDate);

long daysCount = nowDate.until(deadlineDateFormat, ChronoUnit.DAYS);
%>

<portlet:defineObjects />

<portlet:renderURL var="userReqProfileURL"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="userId" value="${docData.userId}" />
	<portlet:param name="docId" value="${docData.docId}" />
	<portlet:param name="mvcPath" value="/viewProfile.jsp" />
</portlet:renderURL>

<portlet:renderURL var="userSignProfileURL"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="userId" value="${docData.signId}" />
	<portlet:param name="docId" value="${docData.docId}" />
	<portlet:param name="mvcPath" value="/viewProfile.jsp" />
</portlet:renderURL>

<aui:script use="liferay-util-window">
	A.one('#popup_userReqProfile').on('click', function(event) {
		Liferay.Util.openWindow({
			dialog : {
				centered : true,
				height : 350,
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
				height : 350,
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
		value="<%=String.valueOf(fileData.getFileId())%>" />
</portlet:resourceURL>
<portlet:renderURL var="updateDocURL">

	<portlet:param name="docId" value="${docData.docId}" />
	<portlet:param name="mvcPath" value="/updateDoc.jsp" />
</portlet:renderURL>

<portlet:actionURL name="doActionMethod" var="doActionMethod" />

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
		<c:when test="<%=docData.getDoc_status().equals("Pending")%>">
			<div class="progress">
				<div class="progress-bar progress-bar-striped active "
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 40%">40% - Pending</div>
			</div>
			<div class="alert alert-info">
				Please <strong>review</strong> this signature request <strong>before
					${docData.doc_deadline}</strong>
			</div>
		</c:when>
		<c:when test="<%=docData.getDoc_status().equals("Signed")%>">
			<div class="progress">
				<div
					class="progress-bar progress-bar-striped active progress-bar-success"
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 70%">70% - Signed</div>
			</div>
			<div class="alert alert-success">
				You have <strong>signed</strong> this document. Pending verification
				from signature requester.
			</div>
		</c:when>
		<c:when test="<%=docData.getDoc_status().equals("Verified")%>">
			<div class="progress">
				<div
					class="progress-bar progress-bar-striped active progress-bar-info"
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 100%">100% - Verified</div>
			</div>
			<div class="alert alert-info">
				Signature has been verified by <strong>${docData.req_name}</strong>.
				Task completed!
			</div>
		</c:when>
		<c:when test="<%=docData.getDoc_status().equals("Justify")%>">
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
		<c:when test="<%=docData.getDoc_status().equals("Rejected")%>">
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
		<c:when test="<%=docData.getDoc_status().equals("Expired")%>">
			<div class="progress">
				<div
					class="progress-bar progress-bar-striped active progress-bar-dark"
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 100%">100% - Expired</div>
			</div>
			<div class="alert alert-dark">
				This signature request already <strong>expired</strong>. You did not
				respond before the deadline.
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
				<td>${docData.docId}</td>
			</tr>
			<tr>
				<td>Request Title:</td>
				<td>${docData.doc_title}</td>
			</tr>
			<tr>
				<td>Type:</td>
				<td>${docData.doc_type}</td>
			</tr>

			<!--  
			<tr>
				<td>Status:</td>
				<td><c:choose>
						<c:when test="<%=docData.getDoc_status().equals("Pending")%>">
							<div class="text-muted">Pending</div>
						</c:when>
						<c:when test="<%=docData.getDoc_status().equals("Signed")%>">
							<div class="text-success">Signed</div>
						</c:when>
						<c:when test="<%=docData.getDoc_status().equals("Rejected")%>">
							<div class="text-danger">Rejected</div>
						</c:when>
						<c:when test="<%=docData.getDoc_status().equals("Justify")%>">
							<div class="text-warning">Need Justification</div>
						</c:when>
					</c:choose></td>
			</tr>-->
			<tr>
				<td>Date Created:</td>
				<td>${docData.req_dateCreated}</td>
			</tr>
			<tr>
				<td>Date Modified:</td>
				<td>${docData.req_dateModified}</td>
			</tr>
			<tr>
				<td>Deadline:</td>
				<td>${docData.doc_deadline}</td>
			</tr>
						<tr>
				<td>Expired in: </td>
				<td><div Class="text-danger">
				<c:choose>
				<c:when test="<%=daysCount < 0%>">
				Expired
				</c:when>
				<c:when test="<%=daysCount == 0%>">
				Today
				</c:when>
				<c:when test="<%=daysCount > 0%>">
				<%=daysCount%> day(s)
				</c:when>
				</c:choose>
				</div></td>
			</tr>
			<tr>
				<td>Description/Justification:</td>
				<td>${docData.doc_description}</td>
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
							<td>${docData.req_name}</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>Signer:
				</td>
				<td><c:choose>
						<c:when test="<%=docData.getReq_accepted() == true%>">

							<table>
								<tr>
									<td>

										<button id="popup_userSignProfile"
											class="btn btn-warning btn-icon" name="delDocument"
											type="submit">
											View Profile</button>
									</td>
									<td>${docData.sign_name}</td>
								</tr>
							</table>
						</c:when>
						<c:otherwise>
							<i>Signer has not accept this request yet</i>
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
				<td>${fileData.file_name}</td>
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
				<td>${docData.doc_md5}</td>
			</tr>
			<tr>
				<td>Time Created:</td>
				<td>${docData.req_timeCreated}</td>
			</tr>
			<tr>
				<td>Time Modified:</td>
				<td>${docData.req_timeModified}</td>
			</tr>
		</tbody>
	</table>

	<br>

	<div class="alert alert-warning">
		<strong>Reminder!</strong> You cannot edit your response after you
		have rejected or signed the document.
	</div>

	<td><input class="btn btn-primary" type=button value=" Back"
		onClick="javascript: window.history.go(-1)"></td>
	<td>
		<button type="button" class="btn btn-danger" data-toggle="collapse"
			data-target="#delete">Reject</button>
	</td>
	<td>
		<button type="button" class="btn btn-warning" data-toggle="collapse"
			data-target="#justify"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;Request Justification</button>
	</td>
	<td>
		<button type="button" class="btn btn-success" data-toggle="collapse"
			data-target="#sign"><span class="glyphicon glyphicon-edit"></span>&nbsp;Sign Document</button>
	</td>

	<div id="delete" class="collapse">

		<br>
		<div class="alert alert-danger">
			<span class="glyphicon glyphicon-exclamation-sign"></span>&nbsp;Are
			you sure you want to reject this request?
		</div>
		<aui:form action="<%=doActionMethod%>" method="post" name="name">
			<aui:input label="Doc Id: " name="docId" type="hidden"
				value="${docData.docId}" readOnly="true" />
			<aui:input label="File Id: " name="fileId" type="hidden"
				value="${fileData.fileId}" readOnly="true" />
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

		<aui:form action="<%=doActionMethod%>" method="post" name="name">
			<aui:input label="Doc Id: " name="docId" type="hidden"
				value="${docData.docId}" readOnly="true" />
			<aui:input label="File Id: " name="fileId" type="hidden"
				value="${fileData.fileId}" readOnly="true" />
			<aui:input label="Doc Id: " name="doc_status" type="hidden"
				value="${docData.doc_status}" readOnly="true" />
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

		<aui:form action="<%=doActionMethod%>" method="post" name="name"
			enctype="multipart/form-data">
			<aui:input label="Enter 6 pin: " name="userPin" type="type">
				<aui:validator name="required" />
				<aui:validator name="digits" />
			</aui:input>
			<aui:input label="Action: " name="doAction" type="hidden"
				value="Sign" readOnly="true" />
			<aui:input label="Doc Id: " name="docId" type="hidden"
				value="${docData.docId}" readOnly="true" />
			<aui:input label="File Id: " name="fileId" type="hidden"
				value="${fileData.fileId}" readOnly="true" />
			<aui:input label="Doc Id: " name="doc_status" type="hidden"
				value="${docData.doc_status}" readOnly="true" />
			<aui:button cssClass="btn btn-success" name="sign" type="submit"
				value="Sign Document" last="true"
				onClick="return confirm('Are you sure to sign this document?')" />
		</aui:form>

	</div>

</div>
<br>


<liferay-ui:error key="error-sign-fail"
	message="Your 6 digit pin does not match with registered pin." />
<liferay-ui:error key="error-pin-invalid-format"
	message="Please enter your 6 digit pin." />
<liferay-ui:error key="error-status-fail"
	message="Unable to process your request. This document has been rejected, signed, verified, or expired." />
<liferay-ui:error key="error-status-invalid"
	message="Action does not exist! Please contact system admin." />
<liferay-ui:error key="error-sign-fail"
	message="System unable to sign document" />
<liferay-ui:error key="error-reject-fail"
	message="System unable to reject document" />
<liferay-ui:error key="error-justify-fail"
	message="System unable to request justification" />