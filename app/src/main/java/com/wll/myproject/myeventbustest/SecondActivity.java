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

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );
        Log.i( "wll11", Thread.currentThread().getName() );
        MessageEvent event1= EventBus.getDefault().getStickyEvent(MessageEvent.class);
        Log.i( "wll11", "粘性直接获取："+event1.msg );
    }


    public void send(View view) {
        EventBus.getDefault().postSticky( new MessageEvent2( "无语" ) );
        Intent intent = new Intent( this, MainActivity.class );
        startActivity( intent );
    }
    //必须先用这个方法来接收数据，才能通过getStickeyEvent来获得，
    // 如果在这里面对事件进行了销毁，通过getStickeyEvent不会再获得传递过来的对象。
    //sticky为接收的是粘性事件,priority是设置优先级默认是0,,越大优先级越低。
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true,priority =1 )
    public void getmess(MessageEvent event) {
        Log.i( "wll11", Thread.currentThread().getName() );
        Toast.makeText( this, event.msg, Toast.LENGTH_SHORT ).show();
    }

}
