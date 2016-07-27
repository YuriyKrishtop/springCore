/**
 * Created by Iurii_Kryshtop on 7/25/2016.
 */

package ua.epam.spring.core.beans;

public class Client {

    private String id;
    private String name;

    public Client(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}