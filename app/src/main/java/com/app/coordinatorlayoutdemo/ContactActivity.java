package com.app.coordinatorlayoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private List<ContactPojo> pojos;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rv_contacts);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Contact List");

        try {
            pojos = parseJson();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (pojos != null) {
            Adapter adapter = new Adapter(pojos, this);
            rvContacts.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    private List<ContactPojo> parseJson() throws JSONException {

        List<ContactPojo> pojos = new ArrayList<>();
        ContactPojo contactPojo;
        try {
            InputStream is = getAssets().open("sample.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            JSONObject result = new JSONObject(json);
            JSONArray contacts = result.getJSONArray("contacts");
            for (int i = 0; i < contacts.length(); i++) {
                JSONObject item = contacts.getJSONObject(i);
                contactPojo = new ContactPojo();
                contactPojo.setId(item.getString("id"));
                contactPojo.setName(item.getString("name"));
                contactPojo.setEmail(item.getString("email"));
                contactPojo.setAddress(item.getString("address"));
                contactPojo.setGender(item.getString("gender"));
                contactPojo.setMobile(item.getString("mobile"));
                pojos.add(contactPojo);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return pojos;
    }
}