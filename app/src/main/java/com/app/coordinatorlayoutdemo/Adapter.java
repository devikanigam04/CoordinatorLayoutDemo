package com.app.coordinatorlayoutdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    private List<ContactPojo> contactPojoList;
    private Context context;

    Adapter(List<ContactPojo> contactPojoList, Context context) {
        this.contactPojoList = contactPojoList;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context)
                .inflate(R.layout.item_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(contactPojoList.get(position));
    }

    @Override
    public int getItemCount() {
        return contactPojoList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView id, name, email, address, gender, mobile;

        Holder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.tv_id);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            email = (TextView) itemView.findViewById(R.id.tv_email);
            address = (TextView) itemView.findViewById(R.id.tv_address);
            gender = (TextView) itemView.findViewById(R.id.tv_gender);
            mobile = (TextView) itemView.findViewById(R.id.tv_mobile);
        }

        void bind(ContactPojo contactPojo) {
            id.setText("ID : "+contactPojo.getId());
            name.setText("NAME : "+contactPojo.getName());
            email.setText("EMAIL : "+contactPojo.getEmail());
            address.setText("ADDRESS : "+contactPojo.getAddress());
            gender.setText("GENDER : "+contactPojo.getGender());
            mobile.setText("MOBILE : "+contactPojo.getMobile());
        }
    }
}