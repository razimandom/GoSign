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

<!--

<style>

@media (min-width: 1200px) {
    .container{
        max-width: 600px;
    }
}
	
</style>

-->

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

<%
long docId = ParamUtil.getLong(request, "docId");
EntDoc document = EntDocLocalServiceUtil.getEntDoc(docId);
request.setAttribute("document", document);
%>

<portlet:actionURL name="doBack" var="doBack" />
<portlet:actionURL name="doEdit" var="doEdit" />

<div class="container">
	<!-- Request Details Section -->

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
									type="submit">
									<span class="glyphicon glyphicon-user"></span>&nbsp;View
									Profile
								</button>
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
											type="submit">
											<span class="glyphicon glyphicon-user"></span>&nbsp;View
											Profile
										</button>
									</td>
									<td>${document.sign_name}</td>
								</tr>
							</table>

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
				<td>${fileup.file_name}</td>
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

<table><tr><td>
	<aui:form action="<%=doBack%>" method="post" name="name">
		<aui:button name="back" type="submit" value="Back" last="true" />
	</aui:form>
</td>
<!--  
<td>
	<aui:form action="<%=doEdit%>" method="post" name="name">
	<aui:input label="Action: " name="doAction" type="hidden" value="update" readOnly="true"/>
	<aui:input label="Doc Id: " name="docId" type="hidden" value="${document.docId}" readOnly="true"/>
	<aui:button name="update" type="submit" value="Edit Request" last="true" />
</aui:form>
</td>
--></tr></table>

	


</div>