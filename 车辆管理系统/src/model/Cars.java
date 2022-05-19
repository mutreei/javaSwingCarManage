package model;

/**汽车信息实体
 *
 */
public class Cars {
    private String plate;
    private String model;
    private String owner;
    private String tel;
    private String color;

    public Cars(String plate, String model, String owner, String tel, String color) {
        this.plate = plate;
        this.model = model;
        this.owner = owner;
        this.tel = tel;
        this.color = color;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
