package com.example.assignment2;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import classes.Broker;
import classes.Subscriber;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> implements Subscriber {

    private View.OnClickListener onClickListener;
    private Broker broker = Broker.getInstance();

    public ContactAdapter(List<Contact> contactsList, View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
        this.contactList = contactsList;
    }
    private final List<Contact> contactList;

    public ContactAdapter(List<Contact> contactList){
        this.contactList = contactList;
        broker.registerSubscriber(this,"add","edit");
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

    @Override
    public void message(Object o, String s, Map<String, Object> map) {
        if (s == "add"){
            if (map.containsKey("contact")){
                Contact contact = (Contact)map.get("contact");
                if (contact != null){
                 contact.setListIndex(getItemCount());
                 add(contact);
                }
            }
        }
        else if (s == "edit"){
            if (map.containsKey("contact")){
                Contact contact = (Contact)map.get("contact");
                if (contact != null) {
                    remove(contact.getListIndex());
                    add(contact.getListIndex(), contact);
                }
            }
        }
    }

    protected static class ContactViewHolder extends RecyclerView.ViewHolder{
        public TextView lblName, lblContactNum;
        public ImageView imgAvatar;
        public View itemView;
        public LinearLayout buttons;
        public Contact contact;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            lblName = itemView.findViewById(R.id.lblName);
            lblContactNum = itemView.findViewById(R.id.lblContactNum);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            buttons = itemView.findViewById(R.id.buttons);
            buttons.setVisibility(View.GONE);
            this.itemView = itemView;
        }

        public void setContact(Contact contact) {
            imgAvatar.setImageResource(contact.getImageResourceID());
            lblName.setText(contact.getContactName());
            lblContactNum.setText(contact.getContactNumber());
            this.contact = contact;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public void add(int pos, Contact contact){
        contactList.add(pos, contact);
        notifyItemChanged(pos);
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
