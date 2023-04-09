package com.example.yourlinkapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.adapters.ContactsAdapter;
import com.example.yourlinkapp.interfaces.OnContactClickListener;
import com.example.yourlinkapp.models.Contact;
import com.example.yourlinkapp.utils.Constant;

import java.util.ArrayList;

public class ContactsFragment extends androidx.fragment.app.Fragment implements OnContactClickListener/*, OnPermissionExplanationListener*/ {
	private Context context;
	private RecyclerView recyclerViewContacts;
	private ContactsAdapter contactsAdapter;
	private android.widget.TextView txtNoContacts;
	private android.os.Bundle bundle;
	private ArrayList<Contact> contacts;
	
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_contacts, container, false);
	}
	
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		context = getContext();
		getData();
		
		recyclerViewContacts = view.findViewById(R.id.recyclerViewContacts);
		txtNoContacts = view.findViewById(R.id.txtNoContacts);
		
		if (contacts.isEmpty()) {
			txtNoContacts.setVisibility(android.view.View.VISIBLE);
			recyclerViewContacts.setVisibility(android.view.View.GONE);
		} else {
			txtNoContacts.setVisibility(android.view.View.GONE);
			recyclerViewContacts.setVisibility(android.view.View.VISIBLE);
			recyclerViewContacts.setHasFixedSize(true);
			recyclerViewContacts.setLayoutManager(new LinearLayoutManager(getContext()));
			initializeAdapter(this);
			
		}
	}
	
	
	public void getData() {
		bundle = getActivity().getIntent().getExtras();
		if (bundle != null) {
			contacts = bundle.getParcelableArrayList(Constant.CHILD_CONTACTS_EXTRA);
		}
	}
	
	public void initializeAdapter(OnContactClickListener onContactClickListener) {
		contactsAdapter = new ContactsAdapter(contacts, context);
		contactsAdapter.setOnContactClickListener(onContactClickListener);
		recyclerViewContacts.setAdapter(contactsAdapter);
	}
	
	
	@Override
	public void onCallClick(String contactNumber) {
		Intent intent = new Intent(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel:" + contactNumber));
		startActivity(intent);
	}
	
	@Override
	public void onMessageClick(String contactNumber) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("sms:" + contactNumber));
		startActivity(intent);
	}
	
}
