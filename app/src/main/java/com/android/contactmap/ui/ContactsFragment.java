package com.android.contactmap.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.contactmap.R;
import com.android.contactmap.adapter.ContactsListAdapter;
import com.android.contactmap.data.ContactData;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author DEEPANKAR
 * @since 17-04-2016.
 */
public class ContactsFragment extends Fragment {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ContactsListAdapter mAdapter;
    private ArrayList<ContactData> contactDatas;
    boolean dataSend = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        if(((MainActivity)getActivity()).getList()!=null){
            contactDatas = ((MainActivity)getActivity()).getList();
            dataSend = true;
        }else contactDatas = new ArrayList<>();

        mAdapter = new ContactsListAdapter(getActivity(), contactDatas);
        mRecyclerView.setAdapter(mAdapter);


    }

    public void updateList(ArrayList<ContactData> data){
        if(!dataSend && mAdapter!=null){
            mAdapter.add(data);
            dataSend = true;
        }
    }
}
