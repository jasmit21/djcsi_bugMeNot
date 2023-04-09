package com.example.yourlinkapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.interfaces.OnChildClickListener;
import com.example.yourlinkapp.models.Child;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildAdapterViewHolder> {
	private Context context;
	private ArrayList<Child> childs;
	private OnChildClickListener onChildClickListener;
	
	
	public ChildAdapter(Context context, ArrayList<Child> childs) {
		this.context = context;
		this.childs = childs;
	}
	
	public void setOnChildClickListener(OnChildClickListener listener) {
		this.onChildClickListener = listener;
	}
	
	@androidx.annotation.NonNull
	@Override
	public ChildAdapterViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup viewGroup, int i) {
		android.view.View view = LayoutInflater.from(context).inflate(R.layout.card_child, viewGroup, false);
		return new ChildAdapterViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@androidx.annotation.NonNull final ChildAdapterViewHolder childAdapterViewHolder, int i) {
		Child child = childs.get(i);
		childAdapterViewHolder.txtChildName.setText(child.getName());
		
		if (child.getScreenLock() != null) {
			childAdapterViewHolder.switchLockPhone.setChecked(child.getScreenLock().isLocked());
		}
		Picasso.get().load(child.getProfileImage()).placeholder(R.drawable.ic_profile_image).error(R.drawable.ic_profile_image).into(childAdapterViewHolder.imgChild);
		if (child.isAppDeleted()) {
			childAdapterViewHolder.layoutDeletedApp.setVisibility(android.view.View.VISIBLE);
			childAdapterViewHolder.txtDeletedApp.setText(child.getName() + " " + context.getResources().getString(R.string.deleted_the_app));
			childAdapterViewHolder.imgChild.setEnabled(false);
			childAdapterViewHolder.txtChildName.setEnabled(false);
			childAdapterViewHolder.switchLockPhone.setEnabled(false);
			childAdapterViewHolder.switchLockPhone.setClickable(false);
		}
	}
	
	@Override
	public int getItemCount() {
		return childs.size();
	}
	
	public class ChildAdapterViewHolder extends RecyclerView.ViewHolder {
		private CircleImageView imgChild;
		private android.widget.TextView txtChildName;
		private android.widget.Switch switchWebFilter;
		private android.widget.Switch switchLockPhone;
		private LinearLayout layoutDeletedApp;
		private android.widget.TextView txtDeletedApp;
		
		public ChildAdapterViewHolder(@androidx.annotation.NonNull android.view.View itemView) {
			super(itemView);
			imgChild = itemView.findViewById(R.id.imgChild);
			txtChildName = itemView.findViewById(R.id.txtChildName);
			switchWebFilter = itemView.findViewById(R.id.switchWebFilter);
			switchWebFilter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (buttonView.isPressed()) {
						int position = getAdapterPosition();
						onChildClickListener.onWebFilterClick(isChecked, childs.get(position));
					}
					
				}
			});
			
			itemView.setOnClickListener(new android.view.View.OnClickListener() {
				@Override
				public void onClick(android.view.View v) {
					if (onChildClickListener != null) {
						int position = getAdapterPosition();
						if (position != RecyclerView.NO_POSITION)
							//onChildClickListener.onItemClick(v, position);
							onChildClickListener.onItemClick(position);
					}
				}
			});
			
			switchLockPhone = itemView.findViewById(R.id.switchLockPhone);
			switchLockPhone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (buttonView.isPressed()) {
						int position = getAdapterPosition();
						onChildClickListener.onBtnLockClick(isChecked, childs.get(position));
					}
				}
			});
			
			layoutDeletedApp = itemView.findViewById(R.id.layoutDeletedApp);
			layoutDeletedApp.setVisibility(android.view.View.GONE);
			txtDeletedApp = itemView.findViewById(R.id.txtDeletedApp);
			
		}
	}
	
	
}
