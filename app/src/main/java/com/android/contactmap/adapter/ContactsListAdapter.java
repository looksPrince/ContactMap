package com.android.contactmap.adapter;

/**
 * @author DEEPANKAR
 * @since 17-04-2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.contactmap.R;
import com.android.contactmap.data.ContactData;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


@SuppressWarnings("deprecation")
public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactItemViewHolder> {

    private Context mContext;
    private List<ContactData> mList;

    public ContactsListAdapter(Context context, ArrayList<ContactData> contacts){
        mContext = context;
        mList = contacts;
    }


    public class ContactItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.name) TextView contactName;
        @Bind(R.id.email) TextView emailId;
        @Bind(R.id.phone) TextView phoneNo;
        @Bind(R.id.officePhone) TextView officePhone;

        public ContactItemViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }


    @Override
    public ContactItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.contact_details, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactItemViewHolder holder, int position) {
        holder.contactName.setText("Name: "+mList.get(position).getName());
        holder.emailId.setText("EmailId: "+mList.get(position).getEmail());
        holder.phoneNo.setText("PhoneNo: "+mList.get(position).getPhone());
        holder.officePhone.setText("Office PhoneNo: "+mList.get(position).getOfficePhone());
    }

    @Override
    public int getItemCount() {return mList.size();}

    public void add(ArrayList<ContactData> data){
        mList.addAll(data);
        notifyDataSetChanged();
    }


}

