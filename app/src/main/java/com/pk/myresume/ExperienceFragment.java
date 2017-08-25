package com.pk.myresume;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.pk.myresume.util.JSONUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Purvesh on 3/12/2017.
 */

public class ExperienceFragment extends Fragment {

    ListView companyListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_experience, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        companyListView = (ListView) view.findViewById(R.id.company_list_view);

        JSONUtil utilObj = new JSONUtil();
        String jsonString = utilObj.loadJSONFromAsset("experience.json",getActivity());

        try{
            JSONArray experience = new JSONArray(jsonString);
            List<Map<String, String>> data = new ArrayList<Map<String, String>>();

            for(int i=0; i<experience.length(); i++){
                JSONObject aCompany = (JSONObject) experience.get(i);
                String companyName = (String) aCompany.get("companyName");
                String position = (String) aCompany.get("position");

                Map<String, String> datum = new HashMap<String, String>(2);
                datum.put("companyName",companyName);
                datum.put("position", position);

                data.add(datum);
            }

            SimpleAdapter adapter = new SimpleAdapter(getContext(), data,
                    android.R.layout.simple_list_item_2,
                    new String[] {"companyName", "position"},
                    new int[] {android.R.id.text1,
                            android.R.id.text2});

            companyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent intent = new Intent(getContext(), CompanyDetailActivity.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
            });

            companyListView.setAdapter(adapter);

            getActivity().setTitle("Experience");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
