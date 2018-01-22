package adminViewKey.portlet;

import adminViewKey.constants.AdminViewKeyPortletKeys;

import com._42Penguins.gosign.service.EntDocLocalServiceUtil;
import com._42Penguins.gosign.service.EntFileUploadLocalServiceUtil;
import com._42Penguins.gosign.service.EntKeyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

/**
 * @author razim
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=admin.gosign",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=adminViewKey Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AdminViewKeyPortletKeys.AdminViewKey,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AdminViewKeyPortlet extends MVCPortlet {
	
	/*
	 * Delete document method
	 */
	
	public void doDelKey(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		long userId = ParamUtil.getLong(actionRequest, "userId");
		try {
			EntKeyLocalServiceUtil.deleteEntKey(userId);
			System.out.println("Key user " + userId + "has been deleted");
			SessionMessages.add(actionRequest, "request_processed", "Deleted key user " + userId);
		} catch (PortalException | SystemException e) {
			e.printStackTrace();
		}
	}
	
}