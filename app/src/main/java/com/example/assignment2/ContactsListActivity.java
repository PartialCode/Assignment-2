package com.example.assignment2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ContactsListActivity extends AppCompatActivity {

    private RecyclerView rwContactsList;
    private ContactAdapter.ContactViewHolder selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        List<Contact> contactList = new ArrayList<>(11);
        {
            contactList.add(new Contact("Android", "+27 47 550 3857", 0));
            contactList.add(new Contact("Java", "+27 40 702 4423", 1));
            contactList.add(new Contact("C#", "+27 07 434 0697", 2));
            contactList.add(new Contact("Delphi", "+27 87 003 5977", 3));
            contactList.add(new Contact("Python", "+27 06 681 0929", 4));
            contactList.add(new Contact("F#", "+27 36 520 6599", 5));
            contactList.add(new Contact("Go", "+27 61 162 5905", 6));
            contactList.add(new Contact("Haskell", "+27 40 968 0871", 7));
            contactList.add(new Contact("Rust", "+27 67 615 7835", 8));
            contactList.add(new Contact("Javascript", "+27 07 074 1532", 9));
        }
        rwContactsList = findViewById(R.id.rwContactsList);
        ContactAdapter adapter = new ContactAdapter(contactList);
        rwContactsList.setAdapter(adapter);
        rwContactsList.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        //might need to add EqualSpaceItemDecoration class sir created
        adapter.setOnClickListener(view -> {
            ContactAdapter.ContactViewHolder cvh =
                    (ContactAdapter.ContactViewHolder)rwContactsList.findContainingViewHolder(view);
            ViewGroup parent = (ViewGroup) view.getParent();
            //changing the views like this might not lead to proper bindings inside the objects
            if (selected != null){
                selected.itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_contact, parent, false);
                selected.imgAvatar = selected.itemView.findViewById(R.id.imgAvatar);
                selected.lblContactNum = selected.itemView.findViewById(R.id.lblContactNum);
                selected.lblName = selected.itemView.findViewById(R.id.lblName);
            }
            cvh.itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_contact_selected,parent, false);
            cvh.imgAvatar = cvh.itemView.findViewById(R.id.imgAvatar1);
            cvh.lblContactNum = cvh.itemView.findViewById(R.id.lblContactNum1);
            cvh.lblName = cvh.itemView.findViewById(R.id.lblName1);
            selected = cvh;
        });
    }

    public void onBtnCallClicked(View view){
        TextView lblContactNum = view.findViewById(R.id.lblContactNum1);
        String contactNum = "tel:" + lblContactNum.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(contactNum));
        startActivity(intent);
    }

    public void onBtnEditClicked(View view){}

    public void onBtnMessageClicked(View view){
        TextView lblContactNum = view.findViewById(R.id.lblContactNum1);
        Uri smsUri = Uri.parse("smsto:"+lblContactNum.getText().toString());
        Intent intent = new Intent(Intent.ACTION_SENDTO,smsUri);
        intent.putExtra("sms_body","Hello World");
        startActivity(intent);
    }

    public void onBtnAddClicked(View view){
        Contact newContact = new Contact();

    }
}