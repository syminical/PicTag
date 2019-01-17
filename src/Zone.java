
//theworldisquiethere

import java.awt.*;

public abstract class Zone {
    private Shape S;
    
    public Zone(Shape container) {
        S = container;   
    }
    
    public Shape Shape() {
        return S;
    }
    
    public abstract void trigger();
}