package com.example.chitchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Message>messages;
    final int ITEM_SENT=1;
    final int ITEM_RECEIVE=2;

    public MessagesAdapter(Context context, ArrayList<Message>messages){
        this.context=context;
        this.messages=messages;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==ITEM_SENT){
            View view= LayoutInflater.from(context).inflate(R.layout.item_sent,parent,false);
            return new SentViewHolder(view);
        }
        else{
            View view= LayoutInflater.from(context).inflate(R.layout.item_receive,parent,false);
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message=messages.get(position);
        if(FirebaseAuth.getInstance().getUid().equals(message.getSenderId())){
            return ITEM_SENT;
        }
        else{
            return ITEM_RECEIVE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message=messages.get(position);
        if(holder.getClass()==SentViewHolder.class){
            SentViewHolder viewHolder=(SentViewHolder) holder;
            viewHolder.message_sent.setText(message.getMessage());
        }
        else{
            ReceiverViewHolder viewHolder=(ReceiverViewHolder) holder;
            viewHolder.message_rec.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class SentViewHolder extends RecyclerView.ViewHolder{
         public EditText message_sent;
        public SentViewHolder(@NonNull View itemView) {
            super(itemView);
            message_sent=itemView.findViewById(R.id.message);
        }
    }
    public class ReceiverViewHolder extends RecyclerView.ViewHolder{
        public EditText message_rec;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            message_rec=itemView.findViewById(R.id.message);
        }
    }
}
