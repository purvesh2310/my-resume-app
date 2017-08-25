package com.pk.myresume.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.pk.myresume.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Purvesh on 3/9/2017.
 */

public class Project {

    private String projectName;
    private String projectType;
    private String projectDescription;
    private String projectDuration;
    private String projectRole;
    private int projectPhoto;

    public Project(String projectName, String projectType, int projectPhoto){
        this.projectName = projectName;
        this.projectType = projectType;
        this.projectPhoto = projectPhoto;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public void setProjectPhoto(int projectPhoto) {
        this.projectPhoto = projectPhoto;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public void setProjectDuration(String projectDuration) {
        this.projectDuration = projectDuration;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public int getProjectPhoto() {return projectPhoto; }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getProjectDuration() {
        return projectDuration;
    }

    public String getProjectRole() {
        return projectRole;
    }

}
