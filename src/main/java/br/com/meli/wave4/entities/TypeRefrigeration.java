package br.com.meli.wave4.entities;

public enum TypeRefrigeration {

    FRESH(1, "fresh"),
    REFRIGERATED(2, "refrigerated"),
    FROZEN(3, "frozen");


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
