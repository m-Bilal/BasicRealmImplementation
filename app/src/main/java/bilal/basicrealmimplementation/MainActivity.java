package bilal.basicrealmimplementation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    RealmConfiguration config;

    EditText editTextId, editTextName;
    Button buttonLogResults, buttonSave, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = (EditText) findViewById(R.id.edittext_id);
        editTextName = (EditText) findViewById(R.id.edittext_name);
        buttonLogResults = (Button) findViewById(R.id.button_log_results);
        buttonSave = (Button) findViewById(R.id.button_save);
        buttonDelete = (Button) findViewById(R.id.button_delete);
        addActionListeners();

        createRealmConfig();
        realm = Realm.getInstance(config);
    }

    void addActionListeners() {
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToRealm();
            }
        });

        buttonLogResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromRealm();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFromRealm();
            }
        });
    }

    void createRealmConfig() {
        // initialize Realm
        Realm.init(getApplicationContext());

        // create your Realm configuration
        config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .name("myCustomRealm")
                .build();
        Realm.setDefaultConfiguration(config);
    }

    void saveToRealm() {
        Integer id = Integer.parseInt(editTextId.getText().toString());

        realm.beginTransaction();

        MyRealmObject myObject = realm.createObject(MyRealmObject.class, id);
        myObject.setName(editTextName.getText().toString());

        realm.commitTransaction();
    }

    void readFromRealm() {
        // use realm.equalTo() to filter results
        RealmResults<MyRealmObject> results = realm.where(MyRealmObject.class).findAll();
        Log.d("Main", "Realm Results");
        if(results == null || results.size() == 0) {
            Log.d("Result", "Empty");
        } else {
            for (MyRealmObject i : results) {
                Log.d(i.getId() + "", i.getName());
            }
        }
    }

    void deleteFromRealm() {
        realm.beginTransaction();
        RealmResults<MyRealmObject> results = realm.where(MyRealmObject.class).findAll();
        results.deleteAllFromRealm();
        Log.d("Result", "Deleted");
        realm.commitTransaction();
    }
}
