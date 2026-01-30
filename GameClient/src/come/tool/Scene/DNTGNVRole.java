package come.tool.Scene;

public class DNTGNVRole
{
    private String name;
    private String size;
    
    public DNTGNVRole(String name, String size) {
        this.upRole(name, size);
    }
    
    public void upRole(String name, String size) {
        this.name = name;
        this.size = size;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSize() {
        return this.size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
}
