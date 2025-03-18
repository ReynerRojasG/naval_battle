package classes;

import java.util.List;

public class Player {
    private String name;    
    
    private String dificulty;   

    public Player(String name, String dificulty) {
        this.name = name;
        this.dificulty = dificulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDificulty() {
        return dificulty;
    }

    public void setDificulty(String dificultad) {
        this.dificulty = dificulty;
    }
        
}
