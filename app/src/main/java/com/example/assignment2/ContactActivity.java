package com.example.assignment2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import classes.Broker;

public class ContactActivity extends AppCompatActivity {

    private EditText edtName, edtContactNum;
    private ImageView imgAvatar;
    private Broker broker;

    //These are to check if info has been changed at all
    //Don't want to save a duplicate of a contact
    private String strName, strContactNum;
    private int imgId, pos, newImgId;
    private boolean isEdit = false;
    private ActivityResultLauncher<Intent> getImageLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
        result -> {
            if (result.getResultCode() != Activity.RESULT_OK) return;
            Intent data = result.getData();
            if (data != null) {
                newImgId = data.getExtras().getInt("imageResourceID");
            }
            imgAvatar.setImageResource(newImgId);
        });
        broker = Broker.getInstance();
        edtName = findViewById(R.id.contact_activity_name);
        edtContactNum = findViewById(R.id.contact_activity_contactNum);
        imgAvatar = findViewById(R.id.contact_activity_imgAvatar);
        Intent intent = getIntent();
        if (intent != null){
            Bundle extras = intent.getExtras();
            if (extras != null){
                Contact contact = (Contact) extras.getSerializable("contact");
                isEdit = extras.getBoolean("isEdit");
                if (isEdit){
                    pos = contact.getListIndex();
                }
                if (contact != null){
                    strName = contact.getContactName();
                    strContactNum = contact.getContactNumber();
                    imgId = contact.getImageResourceID();

                    edtName.setText(contact.getContactName());
                    edtContactNum.setText(contact.getContactNumber());
                    imgAvatar.setImageResource(contact.getImageResourceID());
                }
            }
        }
    }

    public void onContactActivityBtnCancelClicked(View view){
        edtName.setText(strName);
        edtContactNum.setText(strContactNum);
        imgAvatar.setImageResource(imgId);
    }


    //Because of how the application lifecycle works, I'm worried this might mess up ContactsListActivity
    //doesn't update on the ContactsListActivity. Could be due to that getAdapterPosition;
    public void onContactActivityBtnAddClicked(View view){
        if (edtName.getText() == null || edtContactNum.getText() == null || imgAvatar.getDrawable() == null){
            Toast.makeText(getApplicationContext(),"no data in the fields", Toast.LENGTH_LONG).show();
        }
        else {
            strName = edtName.getText().toString();
            strContactNum = edtContactNum.getText().toString();
            if (newImgId != 0){
                imgId = newImgId;
            }
            if (isEdit){
                Map<String, Object> args = new HashMap<>(10);
                Contact contact = new Contact(edtName.getText().toString(), edtContactNum.getText().toString(), imgId, pos);
                args.put("contact",contact);
                broker.publish(this,args,"edit");
            }
            else{
                Map<String, Object> args = new HashMap<>(10);
                Contact contact = new Contact(edtName.getText().toString(), edtContactNum.getText().toString(), imgId);
                args.put("contact", contact);
                broker.publish(this,args,"add");
            }
        }
    }

    public void onBtnContactActivityCallClicked(View view){
        //TextView lblContactNum = view.findViewById(R.id.lblContactNum1);
        //Might not have updated
        String contactNum = "tel:" + edtContactNum.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(contactNum));
        startActivity(intent);
    }

    public void onImgAvatarClicked(View view){
        Intent intent = new Intent(this, GalleryActivity.class);
        getImageLauncher.launch(intent);
    }

}