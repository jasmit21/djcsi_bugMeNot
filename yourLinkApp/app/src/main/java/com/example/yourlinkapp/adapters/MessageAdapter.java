package com.example.yourlinkapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.models.Message;
import com.example.yourlinkapp.utils.BackgroundGenerator;
import com.example.yourlinkapp.utils.Constant;
import com.example.yourlinkapp.utils.DateUtils;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageAdapterViewHolder> {
	private Context context;
	private ArrayList<Message> messages;
	//private OnMessageDeleteClickListener onMessageDeleteClickListener;

    /*public void setOnMessageDeleteClickListener(OnMessageDeleteClickListener onMessageDeleteClickListener) {
        this.onMessageDeleteClickListener = onMessageDeleteClickListener;
    }*/
	
	public MessageAdapter(Context context, ArrayList<Message> messages) {
		this.context = context;
		this.messages = messages;
	}
	
	@androidx.annotation.NonNull
	@Override
	public MessageAdapterViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup viewGroup, int i) {
		android.view.View view = LayoutInflater.from(context).inflate(R.layout.card_message, viewGroup, false);
		return new MessageAdapterViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@androidx.annotation.NonNull MessageAdapterViewHolder messageAdapterViewHolder, int i) {
		Message message = messages.get(i);
		messageAdapterViewHolder.txtSenderPhoneNumber.setText(message.getSenderPhoneNumber());
		messageAdapterViewHolder.txtMessageBody.setText(message.getMessageBody());
		messageAdapterViewHolder.txtTimeReceived.setText(DateUtils.getFormattedDate(message.getTimeReceived(), context));
		messageAdapterViewHolder.txtSenderName.setText(message.getContactName());
		if (message.getContactName().equals(Constant.UNKNOWN_NUMBER)) {
			messageAdapterViewHolder.txtMessageBackground.setText("#");
			messageAdapterViewHolder.txtMessageBackground.setBackground(BackgroundGenerator.getBackground(context));
		} else {
			messageAdapterViewHolder.txtMessageBackground.setText(BackgroundGenerator.getFirstCharacters(message.getContactName()));
			messageAdapterViewHolder.txtMessageBackground.setBackground(BackgroundGenerator.getBackground(context));
		}
		
	}
	
	@Override
	public int getItemCount() {
		return messages.size();
	}
	
	public class MessageAdapterViewHolder extends RecyclerView.ViewHolder {
		private android.widget.TextView txtSenderPhoneNumber;
		private android.widget.TextView txtMessageBody;
		private android.widget.TextView txtTimeReceived;
		private android.widget.TextView txtSenderName;
		private android.widget.TextView txtMessageBackground;
		//private Button btnDeleteMessage;
		
		
		public MessageAdapterViewHolder(@androidx.annotation.NonNull android.view.View itemView) {
			super(itemView);
			txtSenderPhoneNumber = itemView.findViewById(R.id.txtSenderPhoneNumber);
			txtMessageBody = itemView.findViewById(R.id.txtMessageBody);
			txtTimeReceived = itemView.findViewById(R.id.txtTimeReceived);
			txtSenderName = itemView.findViewById(R.id.txtSenderName);
			txtMessageBackground = itemView.findViewById(R.id.txtMessageBackground);
            /*btnDeleteMessage = (Button) itemView.findViewById(R.id.btnDeleteMessage);
            btnDeleteMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getPosition();
                    onMessageDeleteClickListener.onMessageDeleteClick(messages.get(position));
                    deleteMessage(position);
                }
            });*/
			
		}
	}

    /*private void deleteMessage(int position) {
        messages.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, messages.size());
    }*/
	
}
