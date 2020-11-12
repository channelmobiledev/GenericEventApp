package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class ActivityModel implements Parcelable{
    private static final String TAG = "Activity Model";

    private int id;
    private String title;
    private String place;
    private String start;
    private Bitmap imageId;
    private String image;
    private String descr;
    private String end;



    public ActivityModel() { }

    public ActivityModel(String title, String place, String start, String end, String image) {
        this.title = title;
        this.place = place;
        this.start = start;
        this.end = end;
        this.image = image;
    }

    protected ActivityModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        place = in.readString();
        start = in.readString();
        imageId = in.readParcelable(Bitmap.class.getClassLoader());
        image = in.readString();
        descr = in.readString();
        end = in.readString();
    }

    public static final Creator<ActivityModel> CREATOR = new Creator<ActivityModel>() {
        @Override
        public ActivityModel createFromParcel(Parcel in) {
            return new ActivityModel(in);
        }

        @Override
        public ActivityModel[] newArray(int size) {
            return new ActivityModel[size];
        }
    };

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImageId() { return imageId; }

    public void setImageId(Bitmap imageId) { this.imageId = imageId; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public boolean hasImages() {
        return this.image != null ? true : false;
    }

    public String getImage() {
        if (hasImages()) {
        }

        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(place);
        parcel.writeString(start);
        parcel.writeParcelable(imageId, i);
        parcel.writeString(image);
        parcel.writeString(descr);
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
