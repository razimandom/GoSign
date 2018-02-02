package userViewDocSummary.portlet;

import userViewDocSummary.constants.UserViewDocSummaryPortletKeys;

import com._42Penguins.gosign.model.EntDoc;
import com._42Penguins.gosign.service.EntDocLocalServiceUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author razim
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=userViewDocSummary Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + UserViewDocSummaryPortletKeys.UserViewDocSummary,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UserViewDocSummaryPortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
	
	ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	User currentUser = themeDisplay.getUser();
	long currentUserId = currentUser.getUserId();
	
	int countPending = 0;
	int countJustify = 0;
	int countRejected = 0;
	int countSigned = 0;
	int countVerified = 0;
	
	List<EntDoc> docDataList = EntDocLocalServiceUtil.getEntDocs(-1, -1);
	
	for(EntDoc listdoc:docDataList){
		
		if (listdoc.getUserId() == currentUserId){
			if (listdoc.getDoc_status().equals("Pending")) {
				countPending++;
			} else if (listdoc.getDoc_status().equals("Justify")) {
				countJustify++;
			} else if  (listdoc.getDoc_status().equals("Rejected")) {
				countRejected++;
			} else if (listdoc.getDoc_status().equals("Signed")) {
				countSigned++;
			} else if (listdoc.getDoc_status().equals("Verified")) {
				countVerified++;
			} else {
				System.out.println("Incorrect");
			}
		} else {
			System.out.println("No document found");
		}
		
		
	}
	
	System.out.println("Pending " + countPending);
	System.out.println("Justify " + countJustify);
	System.out.println("Reject" + countRejected);
	System.out.println("Sign " +countSigned);
	System.out.println("Verify " + countVerified);
	
	}	
}