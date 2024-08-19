package com.example.assignment2;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classes.Broker;


public class ContactsListActivity extends AppCompatActivity {

    //TODO: update code to make use of index already inside Contact Object to find it's position in list for edits

    public static final String TAG = "ContactsListActivity";
    private RecyclerView rwContactsList;
    private ContactAdapter.ContactViewHolder selected;
    private int posOfSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        List<Contact> contactList = new ArrayList<>(11);
        {
            contactList.add(new Contact("Android", "+27 47 550 3857", R.drawable.avatar_01,0));
            contactList.add(new Contact("Java", "+27 40 702 4423", R.drawable.avatar_02,1));
            contactList.add(new Contact("C#", "+27 07 434 0697", R.drawable.avatar_03,2));
            contactList.add(new Contact("Delphi", "+27 87 003 5977", R.drawable.avatar_04,3));
            contactList.add(new Contact("Python", "+27 06 681 0929", R.drawable.avatar_05,4));
            contactList.add(new Contact("F#", "+27 36 520 6599", R.drawable.avatar_06,5));
            contactList.add(new Contact("Go", "+27 61 162 5905", R.drawable.avatar_07,6));
            contactList.add(new Contact("Haskell", "+27 40 968 0871", R.drawable.avatar_08,7));
            contactList.add(new Contact("Rust", "+27 67 615 7835", R.drawable.avatar_09,8));
            contactList.add(new Contact("Javascript", "+27 07 074 1532", R.drawable.avatar_pokemon,9));
        }
        rwContactsList = findViewById(R.id.rwContactsList);
        ContactAdapter adapter = new ContactAdapter(contactList);

        rwContactsList.setAdapter(adapter);
        rwContactsList.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        rwContactsList.addItemDecoration(new EqualSpaceItemDecoration(18));
        adapter.setOnClickListener(view -> {
            ContactAdapter.ContactViewHolder cvh =
                    (ContactAdapter.ContactViewHolder)rwContactsList.findContainingViewHolder(view);
            if (selected == null){
                selected = cvh;
                cvh.buttons.setVisibility(View.VISIBLE);
            }
            else if (cvh == selected){
                cvh.buttons.setVisibility(View.GONE);
                selected = null;
            }
            else{
                selected.buttons.setVisibility(View.GONE);
                cvh.buttons.setVisibility(View.VISIBLE);
                selected = cvh;
            }
        });
    }

    public void onBtnCallClicked(View view){
        TextView lblContactNum = findViewById(R.id.lblContactNum);
        String contactNum = "tel:" + lblContactNum.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(contactNum));
        startActivity(intent);
    }

    public void onBtnEditClicked(View view){
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra("isEdit",true);
        Contact contact = selected.contact;
        intent.putExtra("contact", contact);
        startActivity(intent);
    }

    public void onBtnMessageClicked(View view){
        TextView lblContactNum = findViewById(R.id.lblContactNum);
        Uri smsUri = Uri.parse("smsto:"+lblContactNum.getText().toString());
        Intent intent = new Intent(Intent.ACTION_SENDTO,smsUri);
        intent.putExtra("sms_body","Hello World");
        startActivity(intent);
    }


    //How we going to add info to this contact??
    public void onBtnAddClicked(View view){
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }
}