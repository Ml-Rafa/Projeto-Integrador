package br.com.meli.wave4.entities;

public enum TypeRefrigeration {

    FRESH(1, "FRESH"),
    REFRIGERATED(2, "REFRIGERATED"),
    FROZEN(3, "FROZEN");


    private Integer code;
    private String description;

    private TypeRefrigeration(Integer code, String description){
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }
    public String getDescription(){
        return description;
    }

    public static TypeRefrigeration value(String type){
        for(TypeRefrigeration value : TypeRefrigeration.values()){
            if(value.getDescription().equals(type)){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Type Refrigeration Code");
    }

}
