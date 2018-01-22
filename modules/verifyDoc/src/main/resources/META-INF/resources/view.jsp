<%@include file="/init.jsp"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@page import="javax.portlet.PortletException"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com._42Penguins.gosign.service.EntDocLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntDoc"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>

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
		
					//ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

					//String signEmail = themeDisplay.getUser().getDisplayEmailAddress();
					//System.out.println(signEmail);
		
					//String remoteUserId = request.getRemoteUser();
					//long userId = Long.valueOf(remoteUserId);
					//System.out.println(userId);
					
					
					//User userEmail = (User) request.getAttribute(currentEmail);
					//System.out.println(userEmail);
					
					String currentUserEmail = (String) request.getAttribute("currentEmail");
					
					//String email = user.getEmailAddress
					//long currentUserId = request.getAttribute("currentUserId");
					List<EntDoc> docList = EntDocLocalServiceUtil.findBySignEmail(currentUserEmail, -1, -1);
					//List<Document> docList = DocumentLocalServiceUtil.findBySignId(currentUserId, -1, -1);
			
					//List<Document> docList = DocumentLocalServiceUtil.getDocuments(-1, -1);
					results = ListUtil.subList(docList, searchContainer.getStart(), searchContainer.getEnd());
					//results = UserLocalServiceUtil.getUsers(searchContainer.getStart(), searchContainer.getEnd());
					searchContainer.setTotal(docList.size());
					searchContainer.setResults(results);
		%>
	

 	</liferay-ui:search-container-results>
	
	
	
	<liferay-ui:search-container-row 
			className="com._42Penguins.gosign.model.EntDoc"
			modelVar="document"
			keyProperty="userId">

    <portlet:renderURL var="viewDocURL">   
    <portlet:param name="docId" value="${document.docId}" />
    <portlet:param name="userId" value="${document.userId}" />
    <portlet:param name="fileId" value="${document.fileId}" />
    <portlet:param name="mvcPath" value="/viewDetails.jsp" />  
    </portlet:renderURL>
    
		<liferay-ui:search-container-column-text value="<%=String.valueOf(row.getPos() + 1)%>" name="No" />
		<liferay-ui:search-container-column-text name="Req ID" property="docId">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-user userId="${document.userId}" showDetails="false" name="User"></liferay-ui:search-container-column-user>
		<liferay-ui:search-container-column-text name="Requested By" property="req_name">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Request Type" property="doc_type">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Created On" property="req_dateCreated">
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
                <c:when test="<%= document.getDoc_status().equals("Justify") %>">
                        <liferay-ui:search-container-column-text cssClass="txtorange" name="Status" property="doc_status">
						  </liferay-ui:search-container-column-text>
                </c:when>
         </c:choose>
		
		
		<liferay-ui:search-container-column-text name="Action" href="${viewDocURL}" value="View">
		</liferay-ui:search-container-column-text>

	
		</liferay-ui:search-container-row>
		
	<liferay-ui:search-iterator searchContainer="<%=searchContainer %>" />

</liferay-ui:search-container>