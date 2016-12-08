

package be.hogent.hackthefuture.domein;


public class Photo {

    private String name;
    private String value;
    private String remark;
    private String datetime;
    private String researcher;


    public Photo() {
    }


    public Photo(String name, String value, String remark, String datetime, String researcher) {
        super();
        this.name = name;
        this.value = value;
        this.remark = remark;
        this.datetime = datetime;
        this.researcher = researcher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }


    public String getRemark() {
        return remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }


    public String getResearcher() {
        return researcher;
    }

    public void setResearcher(String researcher) {
        this.researcher = researcher;
    }

}