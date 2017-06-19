package bilal.basicrealmimplementation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    RealmConfiguration config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createRealmConfig();
        realm = Realm.getInstance(config);
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
}
