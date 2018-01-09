<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@page import="javax.portlet.PortletException"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@ include file="/init.jsp"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="DocRegistration.model.Document"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="DocRegistration.service.DocumentLocalServiceUtil"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<portlet:defineObjects />

<!--  
<link href="http://cdn.alloyui.com/2.5.0/aui-css/css/bootstrap.min.css"></link>
<script src="http://cdn.alloyui.com/2.5.0/aui/aui-min.js"></script>

            <script>
            YUI().use(
            		  'aui-datepicker',
            		  function(Y) {
            		    var datepicker = new Y.DatePicker(
            		      {
            		        trigger: '#<portlet:namespace />calendarDate',
            		        mask: '%d-%m-%Y',
            		        popover: {
            		          toolbars: {
            		            header: [[
            		              {
            		                icon:'icon-trash',
            		                label: 'Clear',
            		                on: {
            		                  click: function() {
            		                    datepicker.clearSelection();
            		                  }
            		                }
            		              },
            		              {
            		                icon:'icon-globe',
            		                label: 'Deadline date',
            		                on: {
            		                  click: function() {
            		                    datepicker.clearSelection();
            		                    datepicker.selectDates(new Date());
            		                  }
            		                }
            		              }
            		            ]]
            		          },
            		          zIndex: 1
            		        }
            		      }
            		    );
            		  }
            		);
            </script>
                 
<style>
<!--



.deadlineTxt{
	font-size:14px;
	color:#869cad;
}


</style>

-->

<portlet:actionURL name="addDoc" var="addDoc" />

<aui:form action="<%=addDoc.toString()%>" method="post" name="name" enctype="multipart/form-data">
	
	<aui:input label="User ID: " name="currentUserId" type="hidden" value="<%=request.getAttribute("currentUserId") %>" readonly="true" />
	<aui:input label="Requestor Name: " name="req_name" type="hidden" value="<%=request.getAttribute("currentFirstName") %>" readonly="true" />
	<aui:input label="Requestor Email: " name="req_email" type="hidden" value="<%=request.getAttribute("currentEmail") %>" readonly="true" />
	<aui:input label="Date Created: " name="req_dateCreated" type="hidden" value="<%=request.getAttribute("currentDateTime") %>" readonly="true" />
	<aui:input label="Signer Email: " name="sign_email" type="type" />	
	<aui:input label="Upload File: " type="file" name="file" />
	<aui:input label="Document Description/Justification" type="textarea" name="doc_description" />
	
	<aui:input label="Deadline" name="doc_deadline" type="text" value="<%=request.getAttribute("currentDateTime") %>"/>
	<!-- <aui:input id="<portlet:namespace />calendarDate" class="form-control" label="Deadline" name="doc_deadline" type="text" value="Click to select date"/> -->
	
	<!-- Below input using different input tag (not aui:input tag) to implement alloyui date picker trigger
	<p class="deadlineTxt">Deadline:</p> 
	<input name="doc_deadline" id="<portlet:namespace />calendarDate" class="form-control date" type="text" placeholder="dd-mm-yyyy" value="<%=request.getAttribute("currentDateTime") %>">
	<br>-->
	
	<aui:select name="doc_type" label="Document Type: " inlineLabel="true">
	<aui:option label="Offer Letter" value="Offer Letter" selected="true"></aui:option>
	<aui:option label="Hardware/Software Request" value="Hardware/Software Request"></aui:option>
	<aui:option label="Project Sign Off" value="Project Sign Off"></aui:option>
	<aui:option label="Others" value="Others"></aui:option>
	</aui:select>
	
	<aui:button name="submit" type="submit" value="Submit" last="true" />

</aui:form>

