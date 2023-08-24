package model.entities;

public class Department {
    
    private Integer idInteger;
    private String nameString;

    public Department(){        
    }

    public Department(Integer idInteger, String nameString) {
        this.idInteger = idInteger;
        this.nameString = nameString;
    }

    public Department(String nameString) {        
       this.nameString = nameString;
    }
    
    public Integer getIdInteger() {
        return idInteger;
    }

    public Department setIdInteger(Integer idInteger) {
        this.idInteger = idInteger;
        return this;
    }

    public String getNameString() {
        return nameString;
    }

    public Department setNameString(String nameString) {
        this.nameString = nameString;
        return this;
    }

    @Override
    public String toString() {
        return "Department [idInteger=" + idInteger + ", nameString=" + nameString + "]";
    }
}
