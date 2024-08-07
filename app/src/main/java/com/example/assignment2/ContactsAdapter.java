package com.example.assignment2;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private View.OnClickListener onClickListener;

    public ContactsAdapter(List<Contact> contactsList, View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
        this.contactsList = contactsList;
    }
    private List<Contact> contactsList;

    public ContactsAdapter(List<Contact> contactsList){
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected static class ContactsViewHolder extends RecyclerView.ViewHolder{
        public TextView lblName, lblContactNum;
        public ImageView imgAvatar;
        public View itemView;
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            lblName = itemView.findViewById(R.id.lblName);
            lblContactNum = itemView.findViewById(R.id.lblContactsNum);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            this.itemView = itemView;
        }

        public void setContact(Contact contact) {
            String resourceName = "avatar_0" + contact.getIconIndex();
            //int imgId = itemView.getResources().getIdentifier(resourceName,"drawable",getClass().getPackageName());
            try{
                if (imgAvatar != null){
                    int imgId = R.drawable.class.getField(resourceName).getInt(null);
                    imgAvatar.setImageResource(imgId);
                }
            }
            catch (Exception e){
                for (StackTraceElement element : e.getStackTrace()){
                    Log.e("ContactsViewHolder",element.toString());
                }
            }
            finally {
                lblName.setText(contact.getContactName());
                lblContactNum.setText(contact.getContactNumber());
            }

        }

        @Override
        public String toString() {
            return super.toString();
        }
    }


}
