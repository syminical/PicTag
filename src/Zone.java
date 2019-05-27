import java.awt.*;

public abstract class Zone {
    private final WindowBox Parent;
    private Shape S;
    
    public Zone(WindowBox P, Shape Sh) {
        Parent = P;
        S = Sh;   
    }
    
    public Shape Shape() {
        return S;
    }
    
    public abstract void entered();
    public abstract void clicked();
    
    public WindowBox Parent() { return Parent; }
}