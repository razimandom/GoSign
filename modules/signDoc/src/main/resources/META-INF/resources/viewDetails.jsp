<%@ include file="/init.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@page import="DocRegistration.model.Document"%>
<%@page import="DocRegistration.service.DocumentLocalServiceUtil"%>
<%@page import="DocRegistration.model.GenKey"%>
<%@page import="DocRegistration.service.GenKeyLocalServiceUtil"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<portlet:defineObjects />

<%
long docId = ParamUtil.getLong(request, "docId");
Document document = DocumentLocalServiceUtil.getDocument(docId);
request.setAttribute("document", document);
String redirect = ParamUtil.getString(request, "backURL");
%>

<!--  

<script src="https://cdn.alloyui.com/3.0.1/aui/aui-min.js"></script>
<link href="https://cdn.alloyui.com/3.0.1/aui-css/css/bootstrap.min.css"></link>-->

		<portlet:resourceURL var="viewURL">
            <portlet:param name="dataId"
                value="<%=String.valueOf(document.getDocId())%>" />
        </portlet:resourceURL>

<!-- This style need to put in css file later 

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

<!-- This table style need to be add in CSS file later -->

<style type="text/css">
td{
	padding:5px}
	
.btn {
    border: none; /* Remove borders */
    color: white; /* Add a text color */
    padding: 14px 28px; /* Add some padding */
    cursor: pointer; /* Add a pointer cursor on mouse-over */
}

.btngreen {background-color: #4CAF50;} /* Green */
.btngreen:hover {background-color: #46a049;}

.btnblue {background-color: #2196F3;} /* Blue */
.btnblue:hover {background: #0b7dda;}

.btnorange {background-color: #ff9800;} /* Orange */
.btnorange:hover {background: #e68a00;}

.btnred {background-color: #f44336;} /* Red */ 
.btnred:hover {background: #da190b;}

.btngray {background-color: #e7e7e7; color: black;} /* Gray */ 
.btngray:hover {background: #ddd;}

</style>


<h3>Request Details</h3>

<table>
<tr>
	<td>Request ID:</td>
	<td>${document.docId}</td>
</tr>
<tr>
	<td>Request MD5:</td>
	<td>${document.file_md5}</td>
</tr>
<tr>
	<td>Request Timestamp: </td>
	<td></td>
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
	<td>File Name:</td>
	<td>${document.file_name}</td>
</tr>
<tr>
	<td>Download:</td>
	<td><aui:form action="<%=viewURL.toString() %>" method="post" name="name">
	<button name="delDocument" type="submit">Download File</button>
	</aui:form></td>
	
</tr>
<tr>
	<td>Description/Justification:</td>
	<td>${document.doc_description}</td>
</tr>
</table>

<h3>Signer & Requestor Details</h3>

<table>
<tr>
	<td>Requestor Name:</td>
	<td>${document.req_name}</td>
</tr>
<tr>
	<td>Requestor Email:</td>
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
</table>
<br>
<portlet:renderURL var="updateDocURL">
<portlet:param name="docId" value="${document.docId}" />
<portlet:param name="mvcPath" value="/updateDoc.jsp" />
    
</portlet:renderURL>

<portlet:actionURL name="doRejectDoc" var="doRejectDoc" />
<portlet:actionURL name="doSignDoc" var="doSignDoc" />
<portlet:actionURL name="doBack" var="doBack" />
<portlet:actionURL name="doReqJustification" var="doReqJustification" />

<table>
<tr>
<td>
	<aui:form action="<%=doBack%>" method="post" name="name">
	<aui:button cssClass="btngrey" name="back" type="submit" value="Back" last="true" />
	</aui:form>
</td>
<td>
	<button class="header btn btn-primary toggler-header-collapsed" css="btnorange" >Need Justification</button>
</td>
<td>
	<aui:form action="<%=doRejectDoc%>" method="post" name="name">
	<aui:input label="Doc Id: " name="docId" type="type" value="${document.docId}" readOnly="true"/>
	<aui:input label="Doc Id: " name="req_name" type="hidden" value="${document.req_name}" readOnly="true"/>
	<aui:input label="Doc Id: " name="req_email" type="hidden" value="${document.req_email}" readOnly="true"/>
	<aui:input label="Doc Id: " name="req_dateCreated" type="hidden" value="${document.req_dateCreated}" readOnly="true"/>
	<aui:input label="Doc Id: " name="sign_email" type="hidden" value="${document.sign_email}" readOnly="true"/>
	<aui:input label="Doc Id: " name="doc_deadline" type="hidden" value="${document.doc_deadline}" readOnly="true"/>
	<aui:input label="Doc Id: " name="doc_type" type="hidden" value="${document.doc_type}" readOnly="true"/>
	<aui:input label="Doc Id: " name="doc_status" type="type" value="${document.doc_status}" readOnly="true"/>
	<aui:button cssClass="btnred" name="reject" type="submit" value="Reject" last="true" onClick= "return confirm('Are you sure to reject this request?')"/>
	</aui:form>
</td>
</tr></table>

<h3>Sign Document: </h3>

<aui:form action="<%=doSignDoc%>" method="post" name="name">
	<aui:input label="Enter 6 pin: " name="userPin" type="type"/>
	<aui:input label="Doc Id: " name="docId" type="hidden" value="${document.docId}" readOnly="true"/>
	<aui:input label="Doc Id: " name="doc_status" type="hidden" value="${document.doc_status}" readOnly="true"/>
	<aui:button cssClass="btngreen" name="sign" type="submit" value="Sign Document" last="true" onClick= "return confirm('Are you sure to sign this document?')"/>
</aui:form>

<div class="content toggler-content-collapsed" id="myToggler">

<h3>Request for justification: </h3>

<portlet:actionURL name="updateDoc" var="updateDoc" />

<aui:form action="<%=doReqJustification%>" method="post" name="name" >
	<aui:input label="Doc Id: " name="docId" type="hidden" value="${document.docId}" readOnly="true"/>
	<aui:input label="Doc Id: " name="req_name" type="hidden" value="${document.req_name}" readOnly="true"/>
	<aui:input label="Doc Id: " name="req_email" type="hidden" value="${document.req_email}" readOnly="true"/>
	<aui:input label="Doc Id: " name="req_dateCreated" type="hidden" value="${document.req_dateCreated}" readOnly="true"/>
	<aui:input label="Doc Id: " name="sign_email" type="hidden" value="${document.sign_email}" readOnly="true"/>
	<aui:input label="Doc Id: " name="doc_deadline" type="hidden" value="${document.doc_deadline}" readOnly="true"/>
	<aui:input label="Doc Id: " name="doc_type" type="hidden" value="${document.doc_type}" readOnly="true"/>
	<aui:input label="Doc Id: " name="doc_status" type="hidden" value="${document.doc_status}" readOnly="true"/>
	<aui:input label="Add comments: " type="textarea" name="justificationMsg" value="Please provide more justification on this request." />
	<aui:button name="req_justification" type="submit" value="Send Email" last="true" onClick= "return confirm('Proceed to send email?')" />
</aui:form>

</div>