package vittahealth.hiring.challenge;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Territory {

    @Id
    @GeneratedValue(generator = "territory")
    @GenericGenerator(name = "territory", strategy = "increment")
    private Long id;

    @Column
    private String name;

    @Column
    private String startArea;

    @Column
    private String endArea;

    @Column
    private Long area;

    @Column
    private Long paintedArea;

    public Territory() {
    }

    public Territory(String name, String startArea, String endArea) {
        this.name = name;
        this.startArea = startArea;
        this.endArea = endArea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartArea() {
        return startArea;
    }

    public void setStartArea(String startArea) {
        this.startArea = startArea;
    }

    public String getEndArea() {
        return endArea;
    }

    public void setEndArea(String endArea) {
        this.endArea = endArea;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Long getPaintedArea() {
        return paintedArea;
    }

    public void setPaintedArea(Long paintedArea) {
        this.paintedArea = paintedArea;
    }
}
