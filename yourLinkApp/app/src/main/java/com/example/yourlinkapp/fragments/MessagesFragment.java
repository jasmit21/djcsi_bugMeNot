package com.example.yourlinkapp.fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.example.yourlinkapp.R;
import com.example.yourlinkapp.adapters.MessageAdapter;
import com.example.yourlinkapp.models.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static com.example.yourlinkapp.activities.ParentSignedInActivity.CHILD_EMAIL_EXTRA;
import static com.example.yourlinkapp.activities.ParentSignedInActivity.CHILD_MESSAGES_EXTRA;

public class MessagesFragment extends androidx.fragment.app.Fragment /*implements OnMessageDeleteClickListener*/ {
	private static final String TAG = "MessagesFragmentTAG";
	private FirebaseDatabase firebaseDatabase;
	private DatabaseReference databaseReference;
	private HashMap<String, Message> messages;
	private ArrayList<Message> messagesList;
	private String childEmail;
	private RecyclerView recyclerViewMessages;
	private MessageAdapter messageAdapter;
	private android.widget.TextView txtNoMessages;
	
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_messages, container, false);
	}
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		firebaseDatabase = FirebaseDatabase.getInstance("https://yourlink-19e96-default-rtdb.firebaseio.com/");
		databaseReference = firebaseDatabase.getReference("users");
		
		recyclerViewMessages = view.findViewById(R.id.recyclerViewMessages);
		txtNoMessages = view.findViewById(R.id.txtNoMessages);
		
		getData();
		
		if (messagesList.isEmpty()) {
			txtNoMessages.setVisibility(android.view.View.VISIBLE);
			recyclerViewMessages.setVisibility(android.view.View.GONE);
		} else {
			txtNoMessages.setVisibility(android.view.View.GONE);
			recyclerViewMessages.setVisibility(android.view.View.VISIBLE);
			recyclerViewMessages.setHasFixedSize(true);
			recyclerViewMessages.setLayoutManager(new LinearLayoutManager(getContext()));
			
			Collections.sort(messagesList, Collections.<Message>reverseOrder());    //descending order
			initializeAdapter(messagesList/*, this*/);
			initializeItemTouchHelper();
		}
		
		
	}
	
	private void getData() {
		android.os.Bundle bundle = getActivity().getIntent().getExtras();
		if (bundle != null) {
			messages = (HashMap<String, Message>) bundle.getSerializable(CHILD_MESSAGES_EXTRA);
			messagesList = new ArrayList<>(messages.values());
			childEmail = bundle.getString(CHILD_EMAIL_EXTRA);
		}
	}
	
	private void initializeAdapter(ArrayList<Message> messages/*, OnMessageDeleteClickListener onMessageDeleteClickListener*/) {
		messageAdapter = new MessageAdapter(getContext(), messages);
		//messageAdapter.setOnMessageDeleteClickListener(onMessageDeleteClickListener);
		recyclerViewMessages.setAdapter(messageAdapter);
	}
	
	private void initializeItemTouchHelper() {
		ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
			@Override
			public boolean onMove(@androidx.annotation.NonNull RecyclerView recyclerView, @androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder, @androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder1) {
				return false;
			}
			
			@Override
			public void onSwiped(@androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder, int i) {
				int position = viewHolder.getAdapterPosition();
				
				deleteMessage(messagesList.get(position));
				messagesList.remove(position);
				messageAdapter.notifyItemRemoved(position);
				messageAdapter.notifyItemRangeChanged(position, messagesList.size());
				if (messagesList.isEmpty()) {
					txtNoMessages.setVisibility(android.view.View.VISIBLE);
					recyclerViewMessages.setVisibility(android.view.View.GONE);
				}
			}
			
		};
		new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerViewMessages);
	}
	
	private void deleteMessage(final Message message) {
		Query query = databaseReference.child("childs").orderByChild("email").equalTo(childEmail);
		query.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
				DataSnapshot nodeShot = dataSnapshot.getChildren().iterator().next();
				final String key = nodeShot.getKey();
				android.util.Log.i(TAG, "onDataChange: key: " + key);
				Query query = databaseReference.child("childs").child(key).child("messages").orderByChild("timeReceived").equalTo(message.getTimeReceived());
				query.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
						if (dataSnapshot.exists()) {
							DataSnapshot snapshot = dataSnapshot.getChildren().iterator().next();
							snapshot.getRef().removeValue();
						}
					}
					
					@Override
					public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
					
					}
				});
			}
			
			@Override
			public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
			
			}
		});
	}
}
