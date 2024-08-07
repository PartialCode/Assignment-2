package com.example.assignment2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private View.OnClickListener onClickListener;

    public ContactAdapter(List<Contact> contactsList, View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
        this.contactList = contactsList;
    }
    private final List<Contact> contactList;

    public ContactAdapter(List<Contact> contactList){
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_contact, parent, false);
        ContactViewHolder cvh = new ContactViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.setContact(contact);
        holder.itemView.setOnClickListener(onClickListener);
    }

    public void setOnClickListener(View.OnClickListener listener){
        onClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    protected static class ContactViewHolder extends RecyclerView.ViewHolder{
        public TextView lblName, lblContactNum;
        public ImageView imgAvatar;
        public View itemView;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            lblName = itemView.findViewById(R.id.lblName);
            lblContactNum = itemView.findViewById(R.id.lblContactNum);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            this.itemView = itemView;
        }

        public void setContact(Contact contact) {
            //int imgId = itemView.getResources().getIdentifier(resourceName,"drawable",getClass().getPackageName());
            try{
                if (imgAvatar != null){
                    if (contact.getIconIndex() >= 9){
                        imgAvatar.setImageResource(R.drawable.avatar_pokemon);
                    }
                    else{
                        String resourceName = "avatar_0" + (contact.getIconIndex()+1);
                        int imgId = R.drawable.class.getField(resourceName).getInt(null);
                        imgAvatar.setImageResource(imgId);
                    }
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

    public void add(Contact contact){
        contactList.add(contact);
        notifyItemChanged(contactList.size()-1);
    }

    public void remove(int pos){
        contactList.remove(pos);
        notifyItemRemoved(pos);
    }

    public Contact get(int pos){
        return contactList.get(pos);
    }
}
