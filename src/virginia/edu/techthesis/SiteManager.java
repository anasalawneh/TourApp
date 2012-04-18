package virginia.edu.techthesis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SiteManager{
	
	private static SiteManager instance = null;
	
	public static SiteManager getInstance() {
		if(instance == null){
			instance = new SiteManager();
		}
		return instance;
	}
	
	//initialize hash map
	private Map<String, ArrayList<Site>> siteHash = new HashMap<String, ArrayList<Site>>();
	
	//initialize array list
    private ArrayList<Site> siteList = new ArrayList<Site>();
    //private ArrayList<Site> libraryList = new ArrayList<Site>();
    
    //create sites here
    Site clemons = new Site("Clemons", "It's a library that opens 24/7. It smells.", "Library", "clemons");
    Site brownLibrary = new Site("Brown Library", "It's the cleanest library in UVA. Simply amazing.", "Library", "brownlibrary");
    Site rotunda = new Site("Rotunda", "It's a building that looks cool. It's located on the lawn.", "SightSeeing", "rotunda");
    
    //default constructor for site manager
    public SiteManager(){
    	//add sites
    	siteList.add(brownLibrary);
    	siteList.add(clemons);
    	siteList.add(rotunda);
    	
    	//map sites
    	for(int i = 0; i < siteList.size(); i++){
    		//check to see if a mapping exists for the category of the current object...
    		String category = siteList.get(i).getCategory();
    		if(siteHash.containsKey(category)){
    			//get that category arraylist and add the site object
    			siteHash.get(category).add(siteList.get(i));
    		}
    		
    		//if it doesn't exist create a new category array list and add it to the map
    		else{
    			ArrayList<Site> catSiteList = new ArrayList<Site>();
    			catSiteList.add(siteList.get(i));
    			siteHash.put(category, catSiteList);
    		}
    		/*if(siteList.get(i).getCategory().equals("Library")){
    			libraryList.add(siteList.get(i));
    		}*/
    	}
    	
    	//siteHash.put("Library", libraryList);
    	
    }

	public ArrayList<Site> getSiteList() {
		return siteList;
	}
	
	public ArrayList<Site> getCategory(String str) {
		return (siteHash.get(str));
	}

	public void setSiteList(ArrayList<Site> siteArrayList) {
		this.siteList = siteArrayList;
	}
}
