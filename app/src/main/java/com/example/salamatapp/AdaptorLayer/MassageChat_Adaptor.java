package com.example.salamatapp.AdaptorLayer;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salamatapp.CommonLayer.DownloadImage;
import com.example.salamatapp.DomainLayer.BaseMessage;
import com.example.salamatapp.MainActivity;
import com.example.salamatapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.v4.content.ContextCompat.getSystemService;

public class MassageChat_Adaptor extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_IMAGE_SENT = 11;
    private static final int VIEW_TYPE_Voice_SENT = 111;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private static final int VIEW_TYPE_IMAGE_RECEIVED = 22;
    private static final int VIEW_TYPE_Voice_RECEIVED = 222;
    String url="https://bermuda-darman.ir/uploads/Chat/";

    private Context mContext;
    private List<BaseMessage> mMessageList;

    public MassageChat_Adaptor(Context context, List<BaseMessage> messageList) {
        mContext = context;
        mMessageList = messageList;
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        BaseMessage message = (BaseMessage) mMessageList.get(position);

        if (message.getSenderReciver()==0) {
            // If the current user is the sender of the message
            if (message.getType()==0){
                return VIEW_TYPE_MESSAGE_SENT;
            }else if (message.getType()==1){
                return VIEW_TYPE_IMAGE_SENT;
            }else if(message.getType()==2){
                return VIEW_TYPE_Voice_SENT;
            }

        } else {
            if (message.getType()==0){
                return VIEW_TYPE_MESSAGE_RECEIVED;
            }else if (message.getType()==1){
                return VIEW_TYPE_IMAGE_RECEIVED;
            }else if (message.getType()==2){
                return VIEW_TYPE_Voice_RECEIVED;
            }
            // If some other user sent the message

        }
        return VIEW_TYPE_MESSAGE_SENT;
    }

    // Inflates the appropriate layout according to the ViewType.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_massage_recive_layout, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_massage_sender_layout, parent, false);
            return new ReceivedMessageHolder(view);
        }else if (viewType==VIEW_TYPE_IMAGE_SENT){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_image_sender_layout, parent, false);
            return new SentImageMessageHolder(view);
        }else if (viewType==VIEW_TYPE_IMAGE_RECEIVED){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_image_recive_layout, parent, false);
            return new ReciveImageMessageHolder(view);
        }else if (viewType==VIEW_TYPE_Voice_SENT){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_voice_send_layout, parent, false);
            return new SentVoiceMessageHolder(view);
        }else if (viewType==VIEW_TYPE_Voice_RECEIVED){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_voice_recive_layout, parent, false);
            return new ReciveVoiceMessageHolder(view);
        }

        return null;
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseMessage message = (BaseMessage) mMessageList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_IMAGE_SENT:
                ((SentImageMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_IMAGE_RECEIVED:
                ((ReciveImageMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_Voice_SENT:
                ((SentVoiceMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_Voice_RECEIVED:
                ((ReciveVoiceMessageHolder) holder).bind(message);
                break;
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }

        void bind(BaseMessage message) {
            messageText.setText(message.getText());

            // Format the stored timestamp into a readable String using method.
            timeText.setText("دکتر");
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
            profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
        }

        void bind(BaseMessage message) {
            messageText.setText(message.getText());

            // Format the stored timestamp into a readable String using method.
            timeText.setText("");

            nameText.setText("بیمار");

        }
    }

    private class SentImageMessageHolder extends RecyclerView.ViewHolder {
        ImageView messageimg;
        TextView timeText;

        SentImageMessageHolder(View itemView) {
            super(itemView);

            messageimg = (ImageView) itemView.findViewById(R.id.img_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }

        void bind(final BaseMessage message) {
            Picasso.with(mContext).load(url+message.getText()).into(messageimg);
            messageimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Downloadfile(url+message.getText());
                }
            });
            // Format the stored timestamp into a readable String using method.
            timeText.setText("");
        }
    }

    private class ReciveImageMessageHolder extends RecyclerView.ViewHolder {
        ImageView messageimg;
        TextView timeText;

        ReciveImageMessageHolder(View itemView) {
            super(itemView);

            messageimg = (ImageView) itemView.findViewById(R.id.img_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }

        void bind(final BaseMessage message) {

            messageimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            // Format the stored timestamp into a readable String using method.
            timeText.setText("");
        }
    }

    private class SentVoiceMessageHolder extends RecyclerView.ViewHolder {
        ImageView messageimg;
        TextView timeText;

        SentVoiceMessageHolder(View itemView) {
            super(itemView);

            messageimg = (ImageView) itemView.findViewById(R.id.img_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }

        void bind(final BaseMessage message) {

            messageimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            // Format the stored timestamp into a readable String using method.
            timeText.setText("");
        }
    }

    private class ReciveVoiceMessageHolder extends RecyclerView.ViewHolder {
        ImageView messageimg;
        TextView timeText;

        ReciveVoiceMessageHolder(View itemView) {
            super(itemView);

            messageimg = (ImageView) itemView.findViewById(R.id.img_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }

        void bind(final BaseMessage message) {

            Picasso.with(mContext).load(url+message.getText()).into(messageimg);
            messageimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Downloadfile(url+message.getText());
                }
            });

            // Format the stored timestamp into a readable String using method.
            timeText.setText("");
        }
    }

    private void Downloadfile(String url){
        if (readAndWriteExternalStorage(mContext)){
            Toast.makeText(mContext, " download start ",
                    Toast.LENGTH_LONG).show();
            DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
            request.setTitle("دانلود فایل");
            request.setAllowedNetworkTypes(2|1);
            request.allowScanningByMediaScanner();
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+System.currentTimeMillis());
            request.setNotificationVisibility(1);
            DownloadManager downloadManager= (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
            downloadManager.enqueue(request);
        }

    }


    public static boolean readAndWriteExternalStorage(Context context){
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return false;
        }else{
            return true;
        }
    }

    public static boolean audioRecord(Context context){
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECORD_AUDIO}, 2);
            return false;
        }else{
            return true;
        }
    }

}