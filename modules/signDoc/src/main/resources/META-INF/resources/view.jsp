<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>

<%@page import="javax.portlet.PortletException"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@include file="/init.jsp"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="DocRegistration.model.Document"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="DocRegistration.service.DocumentLocalServiceUtil"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

<!-- Start - CSS Styles for text in table -->

<style>
.txtred{
	color:red}
	
.txtgreen{
    color:green
}

.txtgrey{
    color:grey
}

.txtorange{
    color:orange
}

	
</style>

<!-- End - CSS Styles for text in table -->

<liferay-portlet:renderURL varImpl="iteratorURL"> 
	<portlet:param name="mvcPath" value="/view.jsp"/>

</liferay-portlet:renderURL>

<liferay-ui:search-container 
	emptyResultsMessage="No pending signature required."
	headerNames="Doc ID, Requestor Name, Requestor Email" 
	iteratorURL="<%=iteratorURL %>"
	delta="10"
	deltaConfigurable="true"
>
	
	<liferay-ui:search-container-results>
	
	<%
	
	//String currentURL = PortalUtil.getCurrentURL(request);

	List<Document> docList = DocumentLocalServiceUtil.getDocuments(-1, -1);
	results = ListUtil.subList(docList, searchContainer.getStart(), searchContainer.getEnd());
	searchContainer.setTotal(docList.size());
	searchContainer.setResults(results);
	
	
	%>
	

 	</liferay-ui:search-container-results>
	
	
	
	<liferay-ui:search-container-row 
			className="DocRegistration.model.Document"
			modelVar="document"
			keyProperty="userId">

    <portlet:renderURL var="viewDocURL">
    
    <portlet:param name="docId" value="${document.docId}" />
    <portlet:param name="userId" value="${document.userId}" />
    <portlet:param name="mvcPath" value="/viewDetails.jsp" />
    
    </portlet:renderURL>
			
		<liferay-ui:search-container-column-user userId="${document.userId}" showDetails="false" name="User"></liferay-ui:search-container-column-user>
		<liferay-ui:search-container-column-text name="Doc ID" property="docId">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Requestor Name" property="req_name">
		</liferay-ui:search-container-column-text>
		
		<liferay-ui:search-container-column-text name="Requestor Email" property="req_email">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Signer Email" property="sign_email">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Type" property="doc_type">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Deadline" property="doc_deadline">
		</liferay-ui:search-container-column-text>
		
		<c:choose>
                <c:when test="<%= document.getDoc_status().equals("Pending")%>">
                        <liferay-ui:search-container-column-text cssClass="txtgrey" name="Status" property="doc_status">
						  </liferay-ui:search-container-column-text>
                </c:when>
                <c:when test="<%= document.getDoc_status().equals("Signed") %>">
                        <liferay-ui:search-container-column-text cssClass="txtgreen" name="Status" property="doc_status">
						  </liferay-ui:search-container-column-text>
                </c:when>
                <c:when test="<%= document.getDoc_status().equals("Rejected") %>">
                        <liferay-ui:search-container-column-text cssClass="txtred" name="Status" property="doc_status">
						  </liferay-ui:search-container-column-text>
                </c:when>
                <c:when test="<%= document.getDoc_status().equals("Need Justification") %>">
                        <liferay-ui:search-container-column-text cssClass="txtorange" name="Status" property="doc_status">
						  </liferay-ui:search-container-column-text>
                </c:when>
         </c:choose>
		
		
		<liferay-ui:search-container-column-text name="Details" href="${viewDocURL}" value="View">
		</liferay-ui:search-container-column-text>

	
		</liferay-ui:search-container-row>
		
		

		
	<liferay-ui:search-iterator searchContainer="<%=searchContainer %>" />

</liferay-ui:search-container>