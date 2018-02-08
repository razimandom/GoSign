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
<%@page
	import="com._42Penguins.gosign.service.EntFileUploadLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntFileUpload"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="java.time.temporal.ChronoUnit" %>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://liferay.com/tld/aui" prefix="aui"%>

<!-- Start - Button styles  -->

<style>
@media ( min-width : 1200px) {
	.container {
		max-width: 800px;
	}
}
</style>
<!-- End - Button styles -->

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

<!-- Render Popup URL -->

<portlet:renderURL var="popupUrl"
	windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcPath" value="/viewKeyList.jsp" />
</portlet:renderURL>

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

<!-- Popup window function -->

<aui:script use="liferay-util-window">
	A.one('#popup_id').on('click', function(event) {
		Liferay.Util.openWindow({
			dialog : {
				centered : true,
				height : 550,
				modal : true,
				width : 500
			},
			id : '<portlet:namespace />dialog',
			title : 'List of Registered Signer',
			uri : '<%=popupUrl%>'
		});
	});
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
	


<!-- URL -->

<portlet:actionURL name="doActionMethod" var="doActionMethod" />

<liferay-portlet:renderURL varImpl="viewSignProfileURL">
	<portlet:param name="userId" value="${docData.signId}" />
	<portlet:param name="docId" value="${docData.docId}" />
	<portlet:param name="mvcPath" value="/viewSignProfile.jsp" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="viewReqProfileURL">
	<portlet:param name="userId" value="${docData.userId}" />
	<portlet:param name="docId" value="${docData.docId}" />
	<portlet:param name="mvcPath" value="/viewSignProfile.jsp" />
</liferay-portlet:renderURL>

<portlet:resourceURL var="viewURL">
	<portlet:param name="fileId"
		value="<%=String.valueOf(fileData.getFileId())%>" />
</portlet:resourceURL>

<!-- Start Main Function -->

<div class="container">

	<!-- Completion status bar -->

	<h3>Status Completion:</h3>

	<c:choose>
		<c:when test="<%=docData.getDoc_status().equals("Pending")%>">
			<div class="progress">
				<div class="progress-bar progress-bar-striped active "
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 40%">40% - Pending</div>
			</div>
			<div class="alert alert-info">
				Your signature request is <strong>still in progress</strong>. You
				will receive email after signer review your request.
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
				Your document has been signed by <strong>${docData.sign_name}</strong>.
				Please proceed to <a href="#verifyId">verify the signature.</a>
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
				You have verified your document. This is valid signature signed by <strong>${docData.sign_name}</strong>.
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
				Signer need more <strong>justification</strong> on this request.
				Please contact signer for more details.
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
				Your signature request has been <strong>rejected</strong>. If this
				is a mistake, you can recreate your request again.
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
				Your signature request is <strong>expired</strong>. Signer does not respond to your request.
			</div>
		</c:when>
	</c:choose>

	<!-- Request Details Section -->

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

	<!-- Requester and Signer Profile Section -->

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
									type="submit">View Profile
								</button>
							</td>
							<td>${docData.req_name}</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>Signer:<liferay-ui:icon-help
						message="Signer profile will be display after signer accept this request." />
				</td>
				<td><c:choose>
						<c:when test="<%=docData.getReq_accepted() == true%>">
							<table>
								<tr>
									<td>

										<button id="popup_userSignProfile"
											class="btn btn-warning btn-icon" name="delDocument"
											type="submit">View Profile</button>
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

	<!-- Uploaded Document Section -->

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
						<button class="btn btn-primary btn-icon" name="delDocument"
							type="submit">
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

	<table>
		<tr>
			<td><!--<aui:form action="<%=doActionMethod%>" method="post" name="name">
					<aui:input label="Action: " name="doAction" type="hidden"
						value="back" readOnly="true" />
					<aui:input label="Doc Id: " name="docId" type="hidden"
						value="${docData.docId}" readOnly="true" />
					<aui:input label="File Id: " name="fileId" type="hidden"
						value="${fileData.fileId}" readOnly="true" />
					<aui:button name="back" type="submit" value="Back" last="true" />
				</aui:form> -->
				 <input class="btn btn-primary" type=button value=" Back"
				onClick="javascript: window.history.go(-1)"> 
				</td>
			<td>
				<button type="button" class="btn btn-danger" data-toggle="collapse"
					data-target="#delete">Delete</button>
			</td>
			<!-- 
			<td>
				<button type="button" class="btn btn-warning" data-toggle="collapse"
					data-target="#email">
					Send Email
					<liferay-ui:icon-help
						message="Send email to signer as reminder or if signer need more justification." />
				</button>
			</td>
			 -->
			<td>

				<div id="popup_id">
					<button class="btn btn-success" name="delDocument" type="submit">
						<span class="glyphicon glyphicon-user"></span>&nbsp;Signer List
					</button>
				</div>

			</td>
			<!-- 
			<td>
				<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#updateDeadline">Change Deadline</button>
			</td>
			 -->
			<td><aui:form action="<%=doActionMethod%>" method="post" name="name">
					<aui:input label="Action: " name="doAction" type="hidden"
						value="showkey" readOnly="true" />
					<aui:input label="Doc Id: " name="docId" type="hidden"
						value="${docData.docId}" readOnly="true" />
					<aui:input label="File Id: " name="fileId" type="hidden"
						value="${fileData.fileId}" readOnly="true" />
					<button class="btn btn-success" name="pubkey" type="submit">
						<span class="glyphicon glyphicon-lock"></span>&nbsp;Retrieve
						Public Key
					</button>
				</aui:form></td>
		</tr>
	</table>

	<div id="delete" class="collapse">
		<br>
		<div class="alert alert-danger">
			<span class="glyphicon glyphicon-exclamation-sign"></span>&nbsp;Are
			you sure you want to delete this request?
		</div>
		<aui:form action="<%=doActionMethod%>" method="post" name="name">
			<aui:input label="Action: " name="doAction" type="hidden"
				value="delete" readOnly="true" />
			<aui:input label="Doc Id: " name="docId" type="hidden"
				value="${docData.docId}" readOnly="true" />
			<aui:input label="File Id: " name="fileId" type="hidden"
				value="${fileData.fileId}" readOnly="true" />
			<aui:button cssClass="btn btn-danger" name="delDocument"
				type="submit" value="Yes. Proceed." last="true" />
		</aui:form>
	</div>

	<div id="updateDeadline" class="collapse">
		<br>
		<h3>Change Deadline:</h3>

		<aui:form action="<%=doActionMethod%>" method="post" name="name">
			<aui:input label="Action: " name="doAction" type="hidden"
				value="update" readOnly="true" />
			<aui:input label="Doc Id: " name="docId" type="hidden"
				value="${docData.docId}" readOnly="true" />
			<aui:input label="File Id: " name="fileId" type="hidden"
				value="${fileData.fileId}" readOnly="true" />
			<aui:input label="New Deadline Date: " name="doc_deadline"
				type="type" value="${docData.doc_deadline}" />
			<aui:button name="update" type="submit" value="Update" last="true" />
		</aui:form>
	</div>

	<div id="email" class="collapse">
		<br>
		<h3>Send Email:</h3>

		<aui:form action="<%=doActionMethod%>" method="post" name="name">
			<aui:input label="Action: " name="doAction" type="hidden"
				value="email" readOnly="true" />
			<aui:input label="Doc Id: " name="docId" type="hidden"
				value="${docData.docId}" readOnly="true" />
			<aui:input label="File Id: " name="fileId" type="hidden"
				value="${fileData.fileId}" readOnly="true" />
			<aui:input label="Comments: " name="comments" type="textarea" />
			<aui:button name="update" type="submit" value="Submit" last="true" />
		</aui:form>
	</div>


	<br>
	<div class="alert alert-warning">
		You need to have signer <strong>public key</strong> to verify the
		signature.
	</div>
	<div class="alert alert-info">
		Click <strong><span class="glyphicon glyphicon-user"></span>&nbsp;Signer
			List </strong> to view key or click <strong><span
			class="glyphicon glyphicon-lock"></span>&nbsp;Retrieve Public Key</strong> to
		automatically retrieve signer key
	</div>

	<h3 id="verifyId">Verify Signature:</h3>

	<aui:form action="<%=doActionMethod%>" method="post" name="name">
		<aui:input label="Action: " name="doAction" type="hidden"
			value="verify" readOnly="true" />
		<aui:input label="Doc Id: " name="docId" type="hidden"
			value="${docData.docId}" readOnly="true" />
		<aui:input label="File Id: " name="fileId" type="hidden"
			value="${fileData.fileId}" readOnly="true" />
		<aui:input label="Insert Signer Public Key: " name="input_pubkey"
			type="textarea" value="<%=request.getAttribute("pubKey")%>" />
		<aui:button cssClass="btn btn-success" name="Verify" type="submit"
			value="Verify Signature" last="true" />

	</aui:form>

</div>

<liferay-ui:error key="error-key"
	message="Verification failed! Public key does not match with signature." />
<liferay-ui:error key="error-key-invalidECCPubKey"
	message="This is invalid ECC public key format." />
<liferay-ui:error key="error-key-null"
	message="Public key field cannot be empty" />
<liferay-ui:error key="error-key-nosign"
	message="This document has not been signed or already rejected." />
<liferay-ui:error key="error-key-nokey"
	message="No key found. Signer has not generate the key yet." />
<liferay-ui:error key="error-key-invalidAction"
	message="Invalid action." />
<liferay-ui:error key="error-verify-fail-verified"
	message="Document already verified." />
<liferay-ui:error key="error-verify-fail-no-sign"
	message="Document need to be sign first before verify." />
<liferay-ui:error key="error-verify-fail-rejected-expired"
	message="Cannot verify document that was rejected/expired." />
<liferay-ui:error key="error-verify-fail-no-action"
	message="System unable to fetch document status" />