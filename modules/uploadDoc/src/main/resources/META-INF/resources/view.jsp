<%@ include file="/init.jsp"%>
<%@page import="com.liferay.portal.kernel.service.persistence.UserUtil"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="javax.portlet.PortletException"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com._42Penguins.gosign.service.EntDocLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntDoc"%>
<%@page import="java.util.Date" %>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>

<portlet:defineObjects />

<portlet:resourceURL var="getUsers">
	<portlet:param name="<%=Constants.CMD%>" value="get_users" />
</portlet:resourceURL>

<%
Date nowDate = new Date();
int startDay, startMonth, startYear;
startDay = 1;
startYear = nowDate.getYear()+1900; 
startMonth = 0;
%>

<!--  

<aui:script>
	AUI()
			.use(
					'autocomplete-list',
					'aui-base',
					'aui-io-request',
					'autocomplete-filters','autocomplete-highlighters',function (A) {var testData;new A.AutoCompleteList({
																allowBrowserAutocomplete : 'true',
									activateFirstItem : 'true',
									inputNode : '#<portlet:namespace />myInputNode',
									resultTextLocator : 'email',
									render : 'true',
									resultHighlighter : 'phraseMatch',
									resultFilters : [ 'phraseMatch' ],
									source : function() {
										var inputValue = A
												.one(
														"#<portlet:namespace />myInputNode")
												.get('value');
										var myAjaxRequest=A.io.request('<%=getUsers.toString()%>',{
															dataType : 'json',
															method : 'POST',
															data : {
																<portlet:namespace />userEmail : inputValue,
															},
															autoLoad : false,
															sync : false,
															on : {
																success : function() {
																	var data = this
																			.get('responseData');
																	testData = data;
																}
															}
														});
										myAjaxRequest.start();
										return testData;
									},
								});
					});

	
</aui:script>

-->

<div class="portlet-header">
Submit Request
</div>
<br>

<portlet:actionURL name="addDoc" var="addDoc" />

<aui:form action="<%=addDoc.toString()%>" method="post" name="name" enctype="multipart/form-data">
	
	<aui:input label="Complete URL (Auto): " name="currentCompURL" type="hidden" value="<%=request.getAttribute("currentCompURL")%>" readonly="true" />
	
	<aui:input label="Title: " name="doc_title" helpMessage="In brief about this document/request.">
	<aui:validator name="required"/>
	<aui:validator name="maxLength">50</aui:validator>
	</aui:input>
	
	<aui:input id="myInputNode" name="sign_email" label="Signer Email: " helpMessage="Email of the user that will sign this document">
	<aui:validator name="required"/>
	<aui:validator name="email"/>
	<aui:validator name="maxLength">30</aui:validator>
	</aui:input>
	
	<aui:input label="Upload File: " type="file" name="file" helpMessage="Upload document that need to be sign digitally">
	<aui:validator name="required"/>
	<aui:validator name="acceptFiles">'pdf,rar,zip,doc,docx,xlsx,xls'</aui:validator>
	</aui:input>
	
	<aui:input label="Description: " type="textarea" name="doc_description" helpMessage="Justification or description of signature request">
	<aui:validator name="maxLength">300</aui:validator>
	</aui:input>
	
	 
	<label>Deadline:</label><liferay-ui:input-date
	name="doc_deadline" 
	firstEnabledDate="<%=nowDate%>" 
	dayValue="<%=startDay%>" 
    monthValue="<%=startMonth%>"
    yearValue="<%=startYear%>"
	/><br>

	<aui:select name="doc_type" label="Document Type: " inlineLabel="true">
	<aui:option label="Project Sign Off" value="Project Sign Off" selected="true"></aui:option>
	<aui:option label="Offer Letter" value="Offer Letter"></aui:option>
	<aui:option label="Hardware Request" value="Hardware Request"></aui:option>
	<aui:option label="Software Request" value="Software Request"></aui:option>
	<aui:option label="Others" value="Others"></aui:option>
	</aui:select>
	
	<aui:button name="submit" type="submit" value="Submit" last="true" />

</aui:form>

<liferay-ui:error key="error-submit"
	message="Failed to submit request." />
<liferay-ui:error key="error-file-size"
	message="File size exceeded 10MB" />
<liferay-ui:error key="error-deadline"
	message="Deadline less than current date" />