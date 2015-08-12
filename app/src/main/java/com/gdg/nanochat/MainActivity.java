package com.gdg.nanochat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity
    extends AppCompatActivity
{

    private Firebase firebaseRef;

    private EditText messageEditText;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        messageEditText = (EditText) findViewById( R.id.message_text );

        //Allows Firebase client to keep its context
        Firebase.setAndroidContext( this );

        firebaseRef = new Firebase( "https://nanochat-gdg.firebaseio.com/" );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_settings )
        {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    public void onSendButtonClick( View view )
    {
        String message = messageEditText.getText().toString();

        Map<String, Object> values = new HashMap<>();
        values.put( "name", "puf" );
        values.put( "message", message );
        firebaseRef.push().setValue( values );

        messageEditText.setText( "" );
    }
}
