package com.android.contactmap.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.contactmap.R;
import com.android.contactmap.adapter.ViewPagerAdapter;
import com.android.contactmap.data.ContactData;
import com.android.contactmap.utils.ContactsTask;
import com.android.contactmap.utils.OnContactLoadListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author DEEPANKAR
 * @since 17-04-2016.
 */

public class MainActivity extends AppCompatActivity implements OnContactLoadListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.home_tabs)
    TabLayout mTabLayout;

    @Bind(R.id.home_viewpager)
    ViewPager mViewPager;

    private ContactsFragment mContactsFragment;
    private MapsFragment mMapsFragment;
    ArrayList<ContactData> arrayList;

    int dataReceived = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        ViewPagerAdapter homePagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(homePagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        new ContactsTask(this).execute();

        mContactsFragment = (ContactsFragment)homePagerAdapter.getItem(0);
        mMapsFragment = (MapsFragment)homePagerAdapter.getItem(1);

    }

    @Override
    public void onSuccess(List<ContactData> data) {
        dataReceived = 1;
        arrayList = new ArrayList<>(data);
        mContactsFragment.updateList(arrayList);
    }

    @Override
    public void onFailure(Exception e) {
        dataReceived=0;
       this.runOnUiThread(showToast);
    }

    private Runnable showToast = new Runnable() {
        public void run() {
            Toast.makeText(MainActivity.this, "Cannot Fetch Contacts", Toast.LENGTH_SHORT).show();
        }
    };


    public ArrayList<ContactData> getList(){
        return arrayList;
    }
}
