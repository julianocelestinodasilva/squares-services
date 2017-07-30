package vittahealth.hiring.challenge.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import vittahealth.hiring.challenge.NodeJson;
import vittahealth.hiring.challenge.NodeJsonType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@TypeDefs( {@TypeDef( name= "NodeJsonType", typeClass = NodeJsonType.class)})
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

    @Column
    @Type(type = "NodeJsonType")
    private NodeJson testEndArea;

    public Territory() {
    }

    public NodeJson getTestEndArea() {
        return testEndArea;
    }

    public void setTestEndArea(NodeJson testEndArea) {
        this.testEndArea = testEndArea;
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
