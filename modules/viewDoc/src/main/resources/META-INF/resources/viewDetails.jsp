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
<%@page import="com._42Penguins.gosign.service.EntFileUploadLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntFileUpload"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!-- Start - This script and script for toggler button 

<script src="https://cdn.alloyui.com/3.0.1/aui/aui-min.js"></script>
<link href="https://cdn.alloyui.com/3.0.1/aui-css/css/bootstrap.min.css"></link>

<script type="text/javascript">

YUI().use(
  'aui-toggler',
  function(Y) {
    new Y.Toggler(
      {
        container: '#myToggler',
        animated: true,
        content: '.content',
        expanded: false,
        header: '.header'
      }
    );
  }
);


</script>-->

<!-- End - This script and script for toggler button -->

<!-- Start - Button styles  -->

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

<!-- End - Button styles -->

<%
long docId = ParamUtil.getLong(request, "docId");
long fileId = ParamUtil.getLong(request, "fileId");
EntDoc document = EntDocLocalServiceUtil.getEntDoc(docId);
EntFileUpload fileup = EntFileUploadLocalServiceUtil.getEntFileUpload(fileId);
request.setAttribute("document", document);
request.setAttribute("fileup", fileup);
%>

		<liferay-portlet:renderURL varImpl="viewSignProfileURL">
			<portlet:param name="mvcPath" value="/viewSignProfile.jsp" />
		</liferay-portlet:renderURL>
		<portlet:resourceURL var="viewURL">
            <portlet:param name="fileId" value="<%=String.valueOf(fileup.getFileId())%>" />
        </portlet:resourceURL>


<div class="container">
  <h3><span class="glyphicon glyphicon-briefcase"></span>&nbsp;Request Details:</h3>            
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
</div>

<div class="container">
  <h3><span class="glyphicon glyphicon-user"></span>&nbsp;Requester & Signer Details:</h3>     
  <table class="table table-hover">
    <tbody>
<tr>
	<td width="250">Requester Name:</td>
	<td>${document.req_name}</td>
</tr>
<tr>
	<td >Requester Email:</td>
	<td>${document.req_email}</td>
</tr>
<tr>
	<td>Signer Name: </td>
	<td>${document.sign_name} 
	<a href="<%=viewSignProfileURL.toString()%>" data-toggle="tooltip" title="View public key"><span class="glyphicon glyphicon-lock"></span></a>
	</td>
</tr>
<tr>
	<td>Signer Email:</td>
	<td>${document.sign_email}</td>
</tr>
    </tbody>
  </table>
</div>

<div class="container">
  <h3><span class="glyphicon glyphicon-folder-open"></span>&nbsp;&nbsp;Uploaded Document:</h3>            
  <table class="table table-hover">
    <tbody>
<tr>
	<td width="250">File Name:</td>
	<td>${fileup.file_name}</td>
</tr>
<tr>
	<td>Download:</td>
	<td><aui:form action="<%=viewURL.toString() %>" method="post" name="name">
	<button class="btn btn-primary" name="delDocument" type="submit">Download File</button>
	</aui:form></td>
	
</tr>
    </tbody>
  </table>
</div>

<div class="container">
  <h3><span class="glyphicon glyphicon-briefcase"></span>&nbsp;Other Details:</h3>            
  <table class="table table-hover">
    <tbody>
<tr>
	<td width="250">Request MD5:</td>
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

<portlet:actionURL name="doAction" var="doAction" />

<table>
<tr>
<td>
	<aui:form action="<%=doAction%>" method="post" name="name">
	<aui:input label="Action: " name="doAction" type="hidden" value="back" readOnly="true"/>
	<aui:input label="Doc Id: " name="docId" type="hidden" value="${document.docId}" readOnly="true"/>
	<aui:input label="File Id: " name="fileId" type="hidden" value="${fileup.fileId}" readOnly="true" />
	<aui:button name="back" type="submit" value="Back" last="true" />
	</aui:form>
</td>
<td>
	<button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#delete">Delete</button>
</td>
<td>
	<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#updateDeadline">Change Deadline</button>
</td>
<td>
	<button type="button" class="btn btn-success" data-toggle="collapse" data-target="#verify">Verify Signature</button>
</td>
<!-- 
<td>
	<button class="header btn btn-primary toggler-header-collapsed" >Change Deadline</button>
</td>
 -->
 <!-- 
<td>
	<button class="header btn btn-primary toggler-header-collapsed" >Verify Signature</button>
</td>
 -->

 <!--  
<td>
	<aui:form action="<%=viewURL.toString() %>" method="post" name="name">
	<aui:button cssClass="btngreen" name="delDocument" type="submit" value="Download File" last="true" />
	</aui:form>
</td>
-->

</tr>
</table>

<div id="delete" class="collapse">
<br>
  <div class="alert alert-danger">Are you sure you want to delete this request?</div>
	<aui:form action="<%=doAction%>" method="post" name="name">
	<aui:input label="Action: " name="doAction" type="hidden" value="delete" readOnly="true"/>
	<aui:input label="Doc Id: " name="docId" type="hidden" value="${document.docId}" readOnly="true"/>
	<aui:button cssClass="btn btn-danger" name="delDocument" type="submit" value="Yes. Proceed." last="true" />
	</aui:form>
</div>

<div id="updateDeadline" class="collapse">
<br>
<h3>Change Deadline:</h3>

<aui:form action="<%=doAction%>" method="post" name="name">
	<aui:input label="Action: " name="doAction" type="hidden" value="update" readOnly="true"/>
	<aui:input label="Doc Id: " name="docId" type="hidden" value="${document.docId}" readOnly="true"/>
	<aui:input label="File Id: " name="fileId" type="hidden" value="${fileup.fileId}" readOnly="true"/>
	<aui:input label="New Deadline Date: " name="doc_deadline" type="type" value="${document.doc_deadline}" />
	<aui:button name="update" type="submit" value="Update" last="true" />

</aui:form>
</div>

<div id="verify" class="collapse">
<br>
  <div class="alert alert-info">
  You need to have signer <strong>public key</strong> to verify the signature. <strong><a href="<%=viewSignProfileURL.toString()%>" data-toggle="tooltip" title="View public key"><span class="glyphicon glyphicon-lock"></span>&nbsp;Click Here</a></strong>
  </div>

<h3>Verify Signature:</h3>

<aui:form action="<%=doAction%>" method="post" name="name">
	<aui:input label="Action: " name="doAction" type="hidden" value="verify" readOnly="true"/>
	<aui:input label="Doc Id: " name="docId" type="hidden" value="${document.docId}" readOnly="true"/>
	<aui:input label="File Id: " name="fileId" type="hidden" value="${fileup.fileId}" readOnly="true"/>
	<aui:input label="Insert Signer Public Key: " name="input_pubkey" type="textarea"/>
	<aui:button cssClass="btn btn-success" name="Verify" type="submit" value="Verify" last="true" />

</aui:form>

</div>

</div>



<liferay-ui:error key="error-key" message="Verification failed! Public key does not match with signature." />
<liferay-ui:error key="error-key-invalidECCPubKey" message="Error! This is invalid ECC public key format." />
<liferay-ui:error key="error-key-null" message="Error! Public key field cannot be empty" />
<liferay-ui:error key="error-key-nosign" message="This document has not been signed." />
<liferay-ui:error key="error-key-invalidAction" message="Invalid action." />