package virginia.edu.techthesis;

import android.os.Parcel;
import android.os.Parcelable;

public class Site implements Parcelable { 
	
	
	public double latitude;
	public double longitude;
	public String vicinity;
	public String[] type;
	public double rating;
	public String icon;
	public String url;

	private String name; 
    private String description;
    private String category;
    private String imageFile;
    private String reference;
    private String id;

	public Site(String name, String description, String category, String imageFile) { 
        this.name = name; 
        this.description = description;
        this.category = category;
        this.imageFile = imageFile;
    } 
    
	public Site(String name, String description, String category) { 
        this.name = name; 
        this.description = description;
        this.category = category;
    } 
	
    public Site(String name){
    	this.name = name;
    }
	
    public Site(double latitude, double longitude, String vicinity,double rating, String name, String reference,
			String id) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.vicinity = vicinity;
		this.rating = rating;
		this.name = name;
		this.reference = reference;
		this.id = id;
	}
    public Site(double latitude, double longitude, String vicinity,double rating, String name, String reference,
			String id, String icn) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.vicinity = vicinity;
		this.rating = rating;
		this.name = name;
		this.reference = reference;
		this.id = id;
		this.icon = icn;
	}
    
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
    public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
    
    public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public static final Parcelable.Creator<Site> CREATOR = 
        new Parcelable.Creator<Site>() { 
        public Site createFromParcel(Parcel in) { 
            String nameParam = in.readString(); 
            String referenceParam = in.readString();
            String idParam = in.readString();
            double latitudeParam = in.readDouble();
            double longitudeParam = in.readDouble();
            double ratingParam = in.readDouble();
            String vicinityParam = in.readString();
            String iconParam = in.readString();
            //String imageFileParam = in.readString();
            //return new Site(nameParam, descriptionParam, categoryParam, imageFileParam);
            return new Site(latitudeParam, longitudeParam, vicinityParam, ratingParam, nameParam, referenceParam, idParam, iconParam); 
        } 

        public Site[] newArray(int size) { 
            return new Site[size]; 
        } 
    }; 

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel p, int flags) {
		// TODO Auto-generated method stub
        p.writeString(name); 
        p.writeString(reference);
        p.writeString(id);
        p.writeDouble(latitude);
        p.writeDouble(longitude);
        p.writeDouble(rating);
        p.writeString(vicinity);
        p.writeString(icon);
	}
} 
