package com.pk.myresume;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pk.myresume.adapter.UniversityListViewAdapter;
import com.pk.myresume.model.University;
import com.pk.myresume.util.JSONUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Purvesh on 3/8/2017.
 */

public class EducationFragment extends Fragment {

    private UniversityListViewAdapter universityAdapter;
    ArrayList<University> universityModels;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_education, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView lv = (ListView) view.findViewById(R.id.university_list);

        universityModels =prepareUniversityListFromJSON();
        universityAdapter= new UniversityListViewAdapter(universityModels,getContext());
        lv.setAdapter(universityAdapter);

        getActivity().setTitle("Education");

    }

    /**
     * Parse university JSON File and prepares the data list for University ListView adapter.
     * @return
     */
    public ArrayList<University> prepareUniversityListFromJSON(){

        ArrayList<University> universityModelsList = new ArrayList<University>();

        JSONUtil utilObj = new JSONUtil();
        String jsonString = utilObj.loadJSONFromAsset("university.json",getActivity());

        try {
            JSONArray universityArray = new JSONArray(jsonString);

            for(int i=0; i<universityArray.length(); i++){

                JSONObject obj = (JSONObject) universityArray.get(i);

                String uniName = obj.getString("name");
                String startYear = obj.getString("startYear");
                String endYear = obj.getString("endYear");
                String major = obj.getString("major");
                String grade = obj.getString("grade");
                String uniLogo = obj.getString("image");

                int imageId = this.getResources().getIdentifier(uniLogo, "drawable", getActivity().getPackageName());

                universityModelsList.add(new University(uniName,startYear,endYear,major,grade,imageId));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return universityModelsList;
    }
}
