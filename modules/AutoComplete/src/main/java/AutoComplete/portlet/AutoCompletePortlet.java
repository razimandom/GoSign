package AutoComplete.portlet;

import AutoComplete.constants.AutoCompletePortletKeys;

import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Raziman Dom
 */
@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=category.sample",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=AutoComplete Portlet",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/view.jsp",
        "javax.portlet.name=" + AutoCompletePortletKeys.AutoComplete,
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)
public class AutoCompletePortlet extends MVCPortlet {

    @Override
    public void serveResource(ResourceRequest resourceRequest,
            ResourceResponse resourceResponse) throws IOException, PortletException {
            String cmd = ParamUtil.getString(resourceRequest, Constants.CMD);
            System.out.println("Constants.CMD: " + cmd);
            if (cmd.equals("get_users")) {
                getUsers(resourceRequest, resourceResponse);
            }
        }
    private void getUsers(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
            JSONArray usersJSONArray = JSONFactoryUtil.createJSONArray();
            ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
            
            System.out.println("=====Retrieving value from text field=====");
            String userEmail = ParamUtil.getString(resourceRequest, "userEmail");
            System.out.println("User entered keyword: ===> " + userEmail);
            
            DynamicQuery userQuery = DynamicQueryFactoryUtil.forClass(User.class, PortalClassLoaderUtil.getClassLoader());
            Criterion criterion = RestrictionsFactoryUtil.like("emailAddress",  StringPool.PERCENT + userEmail + StringPool.PERCENT);
            userQuery.add(criterion);
            JSONObject userJSON = null;
            System.out.println("User query string: ===> " + userQuery.toString());
            
            try {
                List < User > userList = UserLocalServiceUtil.dynamicQuery(userQuery);
                System.out.println("Found word in DB? 0:NO 1:YES ===> " + userList.size());
                for (User user: userList) {
                    userJSON = JSONFactoryUtil.createJSONObject();
                    userJSON.put("signId", user.getUserId());
                    userJSON.put("email", user.getEmailAddress());
                    //userJSON.put("firstName", user.getFirstName());
                    usersJSONArray.put(userJSON);
                    
                    System.out.println(userJSON);
                    
                }
            } catch (Exception e) {}
            PrintWriter out = resourceResponse.getWriter();
            out.println(usersJSONArray.toString());
        }

}