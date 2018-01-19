package viewKey.portlet;

import viewKey.constants.ViewKeyPortletKeys;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import DocRegistration.model.GenKey;
import DocRegistration.service.GenKeyLocalServiceUtil;

import java.io.IOException;

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
		"com.liferay.portlet.display-category=goSign",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=viewKey Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ViewKeyPortletKeys.ViewKey,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ViewKeyPortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		long userId = currentUser.getUserId();
		super.doView(renderRequest, renderResponse);

		try {
			GenKey genkey = GenKeyLocalServiceUtil.getGenKey(userId);
			String pubKey = genkey.getPublickey_Data();
			String priKey = genkey.getPrivatekey_Data();
			System.out.println(pubKey);
	
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}