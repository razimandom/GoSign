package uploadDoc.portlet;

//import java.io.File;
//import org.apache.commons.io.FileUtils;
import uploadDoc.constants.UploadDocPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.portal.kernel.dao.jdbc.OutputBlob;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.ByteArrayFileInputStream;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import DocRegistration.model.Document;
import DocRegistration.service.DocumentLocalServiceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.io.FileUtils;
import org.osgi.service.component.annotations.Component;

/**
 * @author razim
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=goSign",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=uploadDoc Portlet",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + UploadDocPortletKeys.UploadDoc, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class UploadDocPortlet extends MVCPortlet {

	public void uploadFileAction(ActionRequest actionRequest, ActionResponse actionResponse) {

	}

	/*
	 * doView method to get information of current logged user
	 * 
	 */

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();

		/*
		 * Function to fetch current date and time
		 *
		*/
		
		//LocalDate localDate = LocalDate.now();
		//LocalTime localTime = LocalTime.now();
	    //Month month = localDateTime.getMonth();
	    //int day = localDateTime.getDayOfMonth();
	    //int seconds = localDateTime.getSecond();
	    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		LocalDateTime localDateTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatDateTime = localDateTime.format(formatter);
		
		renderRequest.setAttribute("currentUserId", currentUser.getUserId());
		renderRequest.setAttribute("currentFirstName", currentUser.getFirstName());
		renderRequest.setAttribute("currentEmail", currentUser.getEmailAddress());
		renderRequest.setAttribute("currentDateTime", formatDateTime);

		super.doView(renderRequest, renderResponse);
	}

	/*
	 * addDoc method to retrieve data from the form then set the data to db
	 * 
	 */

	@ProcessAction(name = "addDoc")
	public void addDoc(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		System.out.println("====================== Start ======================");
		System.out.println("Retrieve data from upload form...");
		System.out.println("...");
		System.out.println("...");

		long docId = CounterLocalServiceUtil.increment();
		long currentUserId = ParamUtil.getLong(actionRequest, "currentUserId");
		long fileId = CounterLocalServiceUtil.increment();
		String req_name = ParamUtil.getString(actionRequest, "req_name");
		String req_email = ParamUtil.getString(actionRequest, "req_email");
		String req_dateCreated = ParamUtil.getString(actionRequest, "req_dateCreated");
		String sign_email = ParamUtil.getString(actionRequest, "sign_email");
		String doc_description = ParamUtil.getString(actionRequest, "doc_description");
		String doc_deadline = ParamUtil.getString(actionRequest, "doc_deadline");
		String doc_type = ParamUtil.getString(actionRequest, "doc_type");

		System.out.println("Retrieved data:");
		System.out.println("Document ID: " + docId);
		System.out.println("Requestor ID: " + currentUserId);
		System.out.println("File ID: " + fileId);
		System.out.println("Requestor Name: " + req_name);
		System.out.println("Requestor Email: " + req_email);
		System.out.println("Request Created: " + req_dateCreated);
		System.out.println("Signer Email: " + sign_email);
		System.out.println("Document Description/Justification: " + doc_description);
		System.out.println("Deadline: " + doc_deadline);
		System.out.println("Document Type: " + doc_type);
		System.out.println("...");
		System.out.println("...");
		
		/*
		 * Function to upload
		 * 
		 */
		
		  
	 
	        /**
	         * Create and set the primary key
	         */
	        //long id = CounterLocalServiceUtil.increment(BlobDemo.class.getName());
	        //BlobDemo blobDemo = BlobDemoLocalServiceUtil.createBlobDemo(id);
	 
	        //blobDemo.setName(uploadedFileName);
	        //blobDemo.setBlobData(blobData);
	        //blobDemo.setMimeType(MimeTypesUtil.getContentType(file));
	        //BlobDemoLocalServiceUtil.addBlobDemo(blobDemo);
	    

		/*
		 * Function to add data to database
		 */

		try {
			
			UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
	         
	        /**
	         * Get the uploaded file name with extension
	         */
	         
	        String uploadedFileName = uploadRequest.getFileName("file");
	         
	        File file = uploadRequest.getFile("file");
	         
	        InputStream inputStream = new FileInputStream(file);
	        /**
	         * Below is the actual blob data
	         */
	        OutputBlob blobData = new OutputBlob(inputStream, file.length());

			System.out.println("Adding data to database...");

			Document doc = DocumentLocalServiceUtil.createDocument(docId);
			doc.setReq_name(req_name);
			doc.setReq_email(req_email);
			doc.setSign_email(sign_email);
			doc.setReq_dateCreated(req_dateCreated);
			doc.setDoc_deadline(doc_deadline);
			doc.setDoc_description(doc_description);
			doc.setDoc_status("Pending");
			doc.setDoc_type(doc_type);
			doc.setUserId(currentUserId);
			doc.setFileId(fileId);
			doc.setFile_name(uploadedFileName);
			doc.setFile_blob(blobData);
			doc.setFile_type(MimeTypesUtil.getContentType(file));
			
			doc = DocumentLocalServiceUtil.addDocument(doc);

			System.out.println("Data added to database");
			System.out.println("======================== End ======================");

			// actionResponse.setRenderParameter("mvcPath", "/details.jsp");

		} catch (SystemException e) {
			System.out.println("Failed to insert data to database");
			e.printStackTrace();
			System.out.println("======================== End ======================");

		}

	}
	
    /**
     * Here serveResource method is used for displaying blob data
     */
	
	/**
	 *  Commented. This function is to view uploaded file. 
	 *  This function already added to view portlet.
	
    @Override
    public void serveResource(ResourceRequest resourceRequest,
            ResourceResponse resourceResponse) throws IOException,
            PortletException {
 
        try {
            long dataId = ParamUtil.getLong(resourceRequest, "dataId");
            
            Document doc = DocumentLocalServiceUtil.getDocument(dataId);
            //BlobDemo blobDemo = BlobDemoLocalServiceUtil.getBlobDemo(dataId);
            if (doc != null) {
                Blob blob = doc.getFile_blob();
                byte[] binaryData = blob.getBytes(1, (int) blob.length());
                // resourceResponse.setContentType(blobDemo.getMimeType());
 
                resourceResponse.setContentType("application/application-download");
                resourceResponse.setProperty("Content-disposition","attachement; filename=" + doc.getFile_name());
                OutputStream o = resourceResponse.getPortletOutputStream();
                o.write(binaryData);
                o.flush();
                o.close();
                resourceResponse.flushBuffer();
            }
 
        } catch (Exception e) {
 
        }
    }
    
    */

	@ProcessAction(name = "updateDoc")
	public void updateDoc(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		System.out.println("======================== Start ======================");
		System.out.println("Retrieve data from update form...");
		System.out.println("...");
		System.out.println("...");

		long docId = CounterLocalServiceUtil.increment();
		String req_name = ParamUtil.getString(actionRequest, "req_name");
		String req_email = ParamUtil.getString(actionRequest, "req_email");
		String sign_email = ParamUtil.getString(actionRequest, "sign_email");
		String doc_description = ParamUtil.getString(actionRequest, "doc_description");
		String doc_deadline = ParamUtil.getString(actionRequest, "doc_deadline");
		String doc_type = ParamUtil.getString(actionRequest, "doc_type");

		System.out.println("Retrieved data:");
		System.out.println("Document ID: " + docId);
		System.out.println("Requestor Name: " + req_name);
		System.out.println("Requestor Email: " + req_email);
		System.out.println("Signer Email: " + sign_email);
		System.out.println("Document Description/Justification: " + doc_description);
		System.out.println("Deadline: " + doc_deadline);
		System.out.println("Document Type: " + doc_type);
		System.out.println("...");
		System.out.println("...");

		try {

			System.out.println("Adding data to database...");

			Document doc = DocumentLocalServiceUtil.createDocument(docId);
			doc.setReq_name(req_name);
			doc.setReq_email(req_email);
			doc.setSign_email(sign_email);
			doc.setDoc_deadline(doc_deadline);
			doc.setDoc_description(doc_description);
			doc.setDoc_status("Unsigned");
			doc.setDoc_type(doc_type);

			doc = DocumentLocalServiceUtil.addDocument(doc);

			System.out.println("Data added to database");
			System.out.println("======================== End ======================");

			// actionResponse.setRenderParameter("mvcPath", "/details.jsp");

		} catch (SystemException e) {
			System.out.println("Failed to insert data to database");
			e.printStackTrace();
			System.out.println("======================== End ======================");

		}

	}

}