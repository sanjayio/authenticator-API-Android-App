package in.sanjaykumara.authenticator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import in.sanjaykumara.authenticator.library.DatabaseHandler;
import in.sanjaykumara.authenticator.library.UserFunctions;

/**
 * Created by sanjay kumar on 22-01-2015.
 */
public class Home extends Activity {

    Button btnLogout;
    Button changepas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        btnLogout = (Button) findViewById(R.id.btn_logout);
        changepas = (Button) findViewById(R.id.btn_change);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        //load user details from sqlite database.
        HashMap<String,String> user = new HashMap<String, String>();
        user = db.getUserDetails();

        //change password click.
        changepas.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                Intent chgpass = new Intent(getApplicationContext(), ChangePassword.class);
                startActivity(chgpass);
            }

        });

        //logout button click.
        btnLogout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                UserFunctions logout = new UserFunctions();
                logout.logoutUser(getApplicationContext());
                Intent login = new Intent(getApplicationContext(), Login.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login);
                finish();
            }
        });

        final TextView fname = (TextView) findViewById(R.id.user_fname);
        fname.setText("First Name: "+user.get("fname"));
        final TextView lname = (TextView) findViewById(R.id.user_lname);
        lname.setText("Last Name: "+user.get("lname"));
        final TextView email = (TextView) findViewById(R.id.user_email);
        email.setText("Email: "+user.get("email"));
        final TextView username = (TextView) findViewById(R.id.user_uname);
        username.setText("Username: "+user.get("uname"));
        final TextView created = (TextView) findViewById(R.id.user_created);
        created.setText("Created at: "+user.get("created_at"));

    }
}
