package app.svnit.svnitupdate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.util.Log;

public class ContactsData extends Activity{

	
	public static ArrayList<StudentCouncilContact> contactListEw = null,contactListSac = null;
	//, contactListCc = null;
	private static ArrayList<StudentCouncilContact> contactListCow = null, contactListGajjar = null, contactListBhabha = null,
			contactListSwami = null, contactListMtb = null, contactListSarabhai = null, contactListTagore = null, 
			contactListNehru = null, contactListRaman = null, contactListNarmad = null, contactListOffice = null;
	private static ArrayList<StudentCouncilContact> contactListDean = null, contactListReg = null, 
			contactListOther = null, contactListAcad =null;
	private static ArrayList<StudentCouncilContact> contactListAppmech = null, contactListAppChem = null, 
			contactListAppmh = null, contactListAppphy = null, contactListCivil = null, contactListChem = null, 
			contactListComp = null, contactListElectrical = null, contactListElectronic = null, contactListMech = null ;
    
	public static void parseXML(XmlPullParser parser, String tag)throws XmlPullParserException,IOException{
    	
		switch(tag){
		case "ew":
				contactListEw = getContactList(parser, tag);
			break;
		case "sac":
				contactListSac = getContactList(parser, tag);
			break;
		/*case "cc":
				contactListCc = getContactList(parser, tag);
			break;
		*/case "cow":
				contactListCow = getContactList(parser, tag);
			break;
		case "bhabha":
			contactListBhabha = getContactList(parser, tag);
			break;
		case "gajjar":
			contactListGajjar = getContactList(parser, tag);
			break;
		case "sarabhai":
			contactListSarabhai = getContactList(parser, tag);
			break;
		case "tagore":
			contactListTagore = getContactList(parser, tag);
			break;
		case "nehru":
			contactListNehru = getContactList(parser, tag);
			break;	
		case "raman":
			contactListRaman = getContactList(parser, tag);
			break;
		case "narmad":
			contactListNarmad = getContactList(parser, tag);
			break;
		case "swami":
				contactListSwami = getContactList(parser, tag);
			break;
		case "mtb":
				contactListMtb = getContactList(parser, tag);
			break;
		case "office":
			contactListOffice = getContactList(parser, tag);
			break;
		
		case "dean":
			contactListDean = getContactList(parser, tag);
			break;
		case "registrar":
			contactListReg = getContactList(parser, tag);
			break;
		case "acad":
			contactListAcad = getContactList(parser, tag);
			break;
		case "other":
			contactListOther = getContactList(parser, tag);
			break;
		case "appmech":
			contactListAppmech = getContactList(parser, tag);
			break;
		case "appchem":
			contactListAppChem = getContactList(parser, tag);
			break;
		case "appphy":
			contactListAppphy = getContactList(parser, tag);
			break;
		case "appmh":
			contactListAppmh = getContactList(parser, tag);
			break;
		case "civil":
			contactListCivil = getContactList(parser, tag);
			break;
		case "chem":
			contactListChem = getContactList(parser, tag);
			break;
		case "comp":
			contactListComp = getContactList(parser, tag);
			break;
		case "trical":
			contactListElectrical = getContactList(parser, tag);
			break;
		case "tronic":
			contactListElectronic = getContactList(parser, tag);
			break;
		case "mech":
			contactListMech = getContactList(parser, tag);
			break;
			
		}
		
    }
	
	
    public static ArrayList<StudentCouncilContact> getContactList(XmlPullParser parser, String section)throws XmlPullParserException,IOException{
    	ArrayList<StudentCouncilContact> contacts=null;
        int eventType = parser.getEventType();
        StudentCouncilContact currentcontact = null ;
        while (eventType != XmlPullParser.END_DOCUMENT){
            String name = null;
            switch (eventType){
                
            	case XmlPullParser.START_DOCUMENT:
                	contacts = new ArrayList<StudentCouncilContact>();
                	Log.d("NEW", "DOCUMENT");
               // 	  eventType = XmlPullParser.START_TAG;
                      
                    break;
                    
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    Log.d("START TAG",name+"");
                    if (name.equals(section)){
                    	Log.d("NEW", "CONTACT");
                        currentcontact = new StudentCouncilContact();
                    } else if (currentcontact != null){
                        if (name.equals("designation")){
                           	//currentcontact.designation = parser.nextText();
							currentcontact.setDesignation(parser.nextText());
                        	Log.d("DESIGNATION!!",currentcontact.designation+"");
                        } else if (name.equals("name")){
                        	//currentcontact.name = parser.nextText();
                        	currentcontact.setName(parser.nextText());
                        } else if (name.equals("number")){
                            //currentcontact.number= parser.nextText();
                            currentcontact.setNumber(parser.nextText());
                        }
                        else if (name.equals("email")){
                            //currentcontact.email= parser.nextText();
                        	currentcontact.setEmail(parser.nextText());
                        }   
                    }
                    
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase(section) && currentcontact != null){
                    	contacts.add(currentcontact);
                    	currentcontact = null; 
                    }
                    
            }
            eventType = parser.next();
        }
        
        return contacts;
    }
	 public static LinkedHashMap<String, ArrayList<StudentCouncilContact>> getCouncilData() {
	        LinkedHashMap<String, ArrayList<StudentCouncilContact>> expandableListDetail = 
	        		new LinkedHashMap<String, ArrayList<StudentCouncilContact>>();
	        
	        expandableListDetail.put("Execution Wing", contactListEw);
	        expandableListDetail.put("Student Affairs Committee", contactListSac);	        
	     //   expandableListDetail.put("Class Counsellor", contactListCc);
	        
	        
	        return expandableListDetail;
	    }
	 
	 public static LinkedHashMap<String, ArrayList<StudentCouncilContact>> getAdminData() {
	        LinkedHashMap<String, ArrayList<StudentCouncilContact>> expandableListDetail = 
	        		new LinkedHashMap<String, ArrayList<StudentCouncilContact>>();
	        
	        expandableListDetail.put("Deans", contactListDean);
	        expandableListDetail.put("Registrars", contactListReg);
	        expandableListDetail.put("Academic Section", contactListAcad);
	        expandableListDetail.put("Others", contactListOther);
	        
	        return expandableListDetail;
	    }
	 
	 public static LinkedHashMap<String, ArrayList<StudentCouncilContact>> getHostelData() {
	        LinkedHashMap<String, ArrayList<StudentCouncilContact>> expandableListDetail = 
	        		new LinkedHashMap<String, ArrayList<StudentCouncilContact>>();
	        
	        expandableListDetail.put("Council of Wardens", contactListCow);
	        expandableListDetail.put("Hostel Office", contactListOffice);
	        expandableListDetail.put("Mother Teresa Bhavan", contactListMtb);
	        expandableListDetail.put("Swami Vivekanand Bhavan", contactListSwami);	        
	        expandableListDetail.put("Bhabha Bhavan", contactListBhabha);
	        expandableListDetail.put("Gajjar Bhavan", contactListGajjar);
	        expandableListDetail.put("Sarabhai Bhavan", contactListSarabhai);
	        expandableListDetail.put("Tagore Bhavan", contactListTagore);
	        expandableListDetail.put("Nehru Bhavan", contactListNehru);
	        expandableListDetail.put("Raman Bhavan", contactListRaman);
	        expandableListDetail.put("Narmad Bhavan", contactListNarmad);
	        
	        return expandableListDetail;
	    }

	 public static LinkedHashMap<String, ArrayList<StudentCouncilContact>> getDepartmentData() {
	       
		 LinkedHashMap<String, ArrayList<StudentCouncilContact>> expandableListDetail = 
	        		new LinkedHashMap<String, ArrayList<StudentCouncilContact>>();
	        
	        expandableListDetail.put("Applied Mechanics", contactListAppmech);
	        expandableListDetail.put("Applied Chemistry", contactListAppChem);
	        expandableListDetail.put("Applied Mathematics & Humanities", contactListAppmh);
	        expandableListDetail.put("Applied Physics", contactListAppphy);	        
	        expandableListDetail.put("Civil Engineering", contactListCivil);
	        expandableListDetail.put("Chemical Engineering", contactListChem);
	        expandableListDetail.put("Computer Engineering", contactListComp);
	        expandableListDetail.put("Electrical Engineering", contactListElectrical);
	        expandableListDetail.put("Electronics Engineering", contactListElectronic);
	        expandableListDetail.put("Mechanical Engineering", contactListMech);
	        
	        return expandableListDetail;
	    }
}
