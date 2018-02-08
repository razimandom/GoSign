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
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="java.time.temporal.ChronoUnit" %>


<style>

@media (min-width: 1200px) {
    .container{
        max-width: 600px;
    }
}
	
</style>

<%
long docId = ParamUtil.getLong(request, "docId");
EntDoc docData = EntDocLocalServiceUtil.getEntDoc(docId);
request.setAttribute("docData", docData);

LocalDate nowDate = LocalDate.now();
String deadlineDate = docData.getDoc_deadline();
DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate deadlineDateFormat = LocalDate.parse(deadlineDate, formatterDate);
long daysCount = nowDate.until(deadlineDateFormat, ChronoUnit.DAYS);
%>

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

<portlet:actionURL name="doBack" var="doBack" />

<div class="container">
	<!-- Request Details Section -->

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
				<td width="200">Request ID:</td>
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
				<td width="200">Requester:</td>
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

	<h3>
		<span class="glyphicon glyphicon-briefcase"></span>&nbsp;Other
		Details:
	</h3>
	<table class="table table-hover">
		<tbody>
			<tr>
				<td width="200">Request MD5:</td>
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

<table><tr><td>
	<aui:form action="<%=doBack%>" method="post" name="name">
		<aui:button name="back" type="submit" value="Back" last="true" />
	</aui:form>
</td>
</tr></table>

	


</div>