package com.wll.myproject.myeventbustest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

    }


    public void sendMessage(View view) {
        //这里发布消息
        Log.i( "wll11main:", Thread.currentThread().getName() );
        EventBus.getDefault().postSticky( new MessageEvent( "wll1" ));
        Intent intent = new Intent( this, SecondActivity.class );
        startActivity( intent );

    }

    //订阅消息之后才能通过注解来获得消息,
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getmess(MessageEvent2 event) {
        Toast.makeText( this, event.msg, Toast.LENGTH_SHORT ).show();
        Log.i( "wll11",event.msg );
    }


}
