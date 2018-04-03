package ml.derrickabreo.navigationandpersistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    private EditText name;
    private EditText college;
    private Button button;
    private TextView welcome;

    //we use sharedPreferences to store data from the edittext in the phone memory
    private SharedPreferences storingdata;

    //these are the Key Values. (Shared preferences use [key, Value] pair
    //Key value should be a constant string. We use key to store and retrieve data
    private static final String NAME_USER= "NAME_USER";
    private static final String COLLEGE_USER= "COLLEGE_USER";
    String default_value ="Unknown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        android.support.v7.widget.Toolbar tool = findViewById(R.id.settingsToolbar);
        setSupportActionBar(tool);

        //This was eplained yesterday
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Persistence");
        ab.setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.editText_name);
        college = findViewById(R.id.edittextCollege);
        button = findViewById(R.id.button);4
        welcome = findViewById(R.id.welcomeText);

        //Open Sahred preference in PRIVATE mode (initializing preference)
        storingdata = getPreferences(Context.MODE_PRIVATE);

        //READ operration, to check if there are any previously stored values, and store it in a string variable
        //getString(KEY, DEFAULT VALUE),
        //getString() is used to fetch previously stored value using the KEY.
        //If the user hasn't stored anything in the memory(or if the app runs for the first time), default_value will be shown.
        String nameStr = storingdata.getString(NAME_USER,"No name");
        String collegeStr = storingdata.getString(COLLEGE_USER,default_value);

        //This will change the textview to the data fetched from the memory using getString()
        welcome.setText("Hi"+ " "+nameStr +" "+"from "+ collegeStr );

        //write operation, this mode is initiated when the user clicks the button.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To modify the values we use SharedPreferences.Editor.
                //Here we use storingdata.edit() to specify that, we want to edit the above defined sharedPreference
                SharedPreferences.Editor editor =   storingdata.edit();

                //To store/modify previously stored data we use putString(KEY, VALUE)
                //the first parameter to putString is the Key valueb and second parameter is the actual vaule
                //to get the text which user has entered into edittext, getText() is used, to convert it to string toString() is used
                editor.putString(NAME_USER,name.getText().toString());
                editor.putString(COLLEGE_USER,college.getText().toString());

                //Once we have stored/Modified the data we save it using commit()
                //It works just like save feature in NotePad
                editor.commit();

                welcome.setText("Hi"+ " "+name.getText().toString() +" "+"from "+ college.getText().toString() );
            }
        });


    }
}
