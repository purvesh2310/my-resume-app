package com.pk.myresume;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.pk.myresume.adapter.SkillsExpandableListViewAdapter;
import com.pk.myresume.util.JSONUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SkillsFragment extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, JSONArray> skillsListViewDetails;

    private OnFragmentInteractionListener mListener;

    public SkillsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skills, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        JSONUtil utilObj = new JSONUtil();
        String jsonString = utilObj.loadJSONFromAsset("skills.json",getActivity());

        try{
            JSONObject obj = new JSONObject(jsonString);

            JSONArray lang = (JSONArray) obj.get("programmingLanguage");
            JSONArray frontEnd = (JSONArray) obj.get("frontEnd");
            JSONArray portal = (JSONArray) obj.get("portalframeworks");
            JSONArray projectMgmt = (JSONArray) obj.get("projectManagement");
            JSONArray os = (JSONArray) obj.get("os");

            skillsListViewDetails = new HashMap<String, JSONArray>();

            skillsListViewDetails.put("Programming Languages", lang);
            skillsListViewDetails.put("Front End Technologies",frontEnd);
            skillsListViewDetails.put("Web Portal and Frameworks",portal);
            skillsListViewDetails.put("Project Management Tools",projectMgmt);
            skillsListViewDetails.put("Operating Systems",os);

            expandableListView = (ExpandableListView) view.findViewById(R.id.skills_list_view);
            expandableListTitle = new ArrayList<String>(skillsListViewDetails.keySet());
            expandableListAdapter = new SkillsExpandableListViewAdapter(getContext(), expandableListTitle, skillsListViewDetails);
            expandableListView.setAdapter(expandableListAdapter);

            getActivity().setTitle("Skills");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
