package utils;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;


public class GoogleSheetUtils {
	 private static final String APPLICATION_NAME = "HRM-Test";

	    // 🔴 Your Google Sheet ID
	    private static final String SPREADSHEET_ID =
	            "1v6SApZlcrqWFceSlmkh_voXwhjnnDMIvmplmqnY_c30";

	    // ==============================
	    // GOOGLE SHEETS SERVICE
	    // ==============================
	    private static Sheets getSheetsService() throws Exception {

	        GoogleCredentials credentials =
	                GoogleCredentials.fromStream(
	                        new FileInputStream("src/main/resources/credentials.json"))
	                        .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));

	        return new Sheets.Builder(
	                GoogleNetHttpTransport.newTrustedTransport(),
	                GsonFactory.getDefaultInstance(),
	                new HttpCredentialsAdapter(credentials))
	                .setApplicationName(APPLICATION_NAME)
	                .build();
	    }

	    // ==============================
	    // READ DATA FROM SHEET
	    // ==============================
	    public static List<List<Object>> getSheetData(String range) throws Exception {

	        Sheets service = getSheetsService();

	        ValueRange response = service.spreadsheets()
	                .values()
	                .get(SPREADSHEET_ID, range)
	                .execute();

	        return response.getValues();
	    }

	    // ==============================
	    // CREATE HEADERS
	    // ==============================
	    public static void createHeaders() throws Exception {

	        Sheets service = getSheetsService();

	        ValueRange headers = new ValueRange()
	                .setValues(Arrays.asList(
	                        Arrays.asList(
	                                "FirstName",
	                                "LastName",
	                                "Email",
	                                "Phone",
	                                "Status"
	                        )));

	        service.spreadsheets()
	                .values()
	                .update(
	                        SPREADSHEET_ID,
	                        "Sheet2!A1:E1",
	                        headers)
	                .setValueInputOption("RAW")
	                .execute();
	    }

	    // ==============================
	    // WRITE TEST RESULT
	    // ==============================
	    public static void writeResult(
	            String fname,
	            String lname,
	            String email,
	            String phone,
	            String status) throws Exception {

	        Sheets service = getSheetsService();

	        ValueRange body = new ValueRange()
	                .setValues(Arrays.asList(
	                        Arrays.asList(
	                                fname,
	                                lname,
	                                email,
	                                phone,
	                                status
	                        )));

	        service.spreadsheets()
	                .values()
	                .append(
	                        SPREADSHEET_ID,
	                        "Sheet2!A:E",
	                        body)
	                .setValueInputOption("RAW")
	                .execute();
	    }

}
