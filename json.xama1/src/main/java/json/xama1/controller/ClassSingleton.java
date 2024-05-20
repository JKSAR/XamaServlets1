package json.xama1.controller;

public final class ClassSingleton {

    private static ClassSingleton INSTANCE;
    // private String info = "Initial info class";
    
    private int mesaNum = 0;
    
    
    private ClassSingleton() {        
    }
    
	public static ClassSingleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ClassSingleton();
        }
        
        return INSTANCE;
    }

    // getters and setters	
    public int getMesaNum() {
		return mesaNum;
	}

	public void setMesaNum(int mesaNum) {
		this.mesaNum = mesaNum;
	}
}
