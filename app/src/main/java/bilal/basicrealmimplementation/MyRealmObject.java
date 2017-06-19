package bilal.basicrealmimplementation;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Bilal on 19-Jun-17.
 */

public class MyRealmObject extends RealmObject {

    @PrimaryKey
    private int id;

    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
