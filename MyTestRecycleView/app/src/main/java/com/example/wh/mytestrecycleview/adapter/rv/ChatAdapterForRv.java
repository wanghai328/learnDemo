package com.example.wh.mytestrecycleview.adapter.rv;

import android.content.Context;


import com.example.wh.mytestrecycleview.bean.ChatMessage;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;


/**
 * Created by zhy on 15/9/4.
 */
public class ChatAdapterForRv extends MultiItemTypeAdapter<ChatMessage>
{
    public ChatAdapterForRv(Context context, List<ChatMessage> datas)
    {
        super(context, datas);

        addItemViewDelegate(new MsgSendItemDelagate());
        addItemViewDelegate(new MsgComingItemDelagate());
    }
}
