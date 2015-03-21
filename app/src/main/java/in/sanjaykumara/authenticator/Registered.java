package in.sanjaykumara.authenticator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import in.sanjaykumara.authenticator.library.DatabaseHandler;

/**
 * Created by sanjay kumar on 22-01-2015.
 */
public class Registered extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        HashMap<String,String> user = new HashMap<String, String>();
        user = db.getUserDetails();
        final TextView name = (TextView)findViewById(R.id.reg_hi);
        name.setText("Hi " + user.get("fname") + ", please login to continue.");

        Button login = (Button) findViewById(R.id.reg_login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Login.class);
                startActivityForResult(myIntent, 0);
                finish();
            }
        });
    }
}
