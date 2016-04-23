package com.android.contactmap.utils;

import com.android.contactmap.data.ContactData;

import java.util.List;

/**
 * @author DEEPANKAR
 * @since 18-04-2016.
 */
public interface OnContactLoadListener {

    void onSuccess(List<ContactData> data);
    void onFailure(Exception e);
}
