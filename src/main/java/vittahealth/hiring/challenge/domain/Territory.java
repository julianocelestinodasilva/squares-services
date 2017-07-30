package vittahealth.hiring.challenge.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
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
    private Node startArea;

    @Column
    @Type(type = "NodeJsonType")
    private Node endArea;

    @Column
    private Long paintedArea;

    public Long proportionalPaintedArea() {
        return ((Float) ((new Float(paintedArea) / new Float(area())) * 100)).longValue();
    }

    public Long area() {
        return startArea.area() + endArea.area();
    }

    public Territory() {
    }


    public Territory(String name, Node startArea, Node endArea) {
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

    public Node getStartArea() {
        return startArea;
    }

    public void setStartArea(Node startArea) {
        this.startArea = startArea;
    }

    public Node getEndArea() {
        return endArea;
    }

    public void setEndArea(Node endArea) {
        this.endArea = endArea;
    }

    public Long getPaintedArea() {
        return paintedArea;
    }

    public void setPaintedArea(Long paintedArea) {
        this.paintedArea = paintedArea;
    }
}
