package com.example.yourlinkapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.interfaces.OnContactClickListener;
import com.example.yourlinkapp.models.Contact;
import com.example.yourlinkapp.utils.BackgroundGenerator;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsAdapterViewHolder> {
	private Context context;
	private ArrayList<Contact> contacts;
	private OnContactClickListener onContactClickListener;
	
	public ContactsAdapter(ArrayList<Contact> contacts, Context context) {
		this.contacts = contacts;
		this.context = context;
	}
	
	public void setOnContactClickListener(OnContactClickListener onContactClickListener) {
		this.onContactClickListener = onContactClickListener;
	}
	
	@androidx.annotation.NonNull
	@Override
	public ContactsAdapterViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup viewGroup, int i) {
		android.view.View view = LayoutInflater.from(context).inflate(R.layout.card_contact, viewGroup, false);
		return new ContactsAdapter.ContactsAdapterViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@androidx.annotation.NonNull ContactsAdapterViewHolder contactsAdapterViewHolder, int i) {
		Contact contact = contacts.get(i);
		contactsAdapterViewHolder.txtContactName.setText(contact.getContactName());
		contactsAdapterViewHolder.txtContactNumber.setText(contact.getContactNumber());
		contactsAdapterViewHolder.txtContactBackground.setText(BackgroundGenerator.getFirstCharacters(contact.getContactName()));
		contactsAdapterViewHolder.txtContactBackground.setBackground(BackgroundGenerator.getBackground(context));
	}
	
	@Override
	public int getItemCount() {
		return contacts.size();
		
	}
	
	public class ContactsAdapterViewHolder extends RecyclerView.ViewHolder {
		private android.widget.TextView txtContactName;
		private android.widget.TextView txtContactNumber;
		private ImageButton imgBtnCall;
		private ImageButton imgBtnMessage;
		private android.widget.TextView txtContactBackground;
		
		public ContactsAdapterViewHolder(@androidx.annotation.NonNull android.view.View itemView) {
			super(itemView);
			txtContactName = itemView.findViewById(R.id.txtContactName);
			txtContactNumber = itemView.findViewById(R.id.txtContactNumber);
			txtContactBackground = itemView.findViewById(R.id.txtContactBackground);
			imgBtnCall = itemView.findViewById(R.id.imgBtnCall);
			imgBtnCall.setOnClickListener(new android.view.View.OnClickListener() {
				@Override
				public void onClick(android.view.View view) {
					onContactClickListener.onCallClick(contacts.get(getAdapterPosition()).getContactNumber());
				}
			});
			
			imgBtnMessage = itemView.findViewById(R.id.imgBtnMessage);
			imgBtnMessage.setOnClickListener(new android.view.View.OnClickListener() {
				@Override
				public void onClick(android.view.View view) {
					onContactClickListener.onMessageClick(contacts.get(getAdapterPosition()).getContactNumber());
				}
			});
			
		}
	}
	
	
}
