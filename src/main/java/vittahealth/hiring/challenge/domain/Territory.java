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
    @Type(type = "NodeJsonType")
    private NodeJson startArea;

    @Column
    @Type(type = "NodeJsonType")
    private NodeJson endArea;

    @Column
    private Long area;

    @Column
    private Long paintedArea;

    public Territory() {
    }


    public Territory(String name, NodeJson startArea, NodeJson endArea) {
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

    public NodeJson getStartArea() {
        return startArea;
    }

    public void setStartArea(NodeJson startArea) {
        this.startArea = startArea;
    }

    public NodeJson getEndArea() {
        return endArea;
    }

    public void setEndArea(NodeJson endArea) {
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
