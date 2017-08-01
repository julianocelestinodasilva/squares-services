package vittahealth.hiring.challenge.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import vittahealth.hiring.challenge.NodeJsonType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@TypeDefs( {@TypeDef( name= "NodeJsonType", typeClass = NodeJsonType.class)})
public class Territory {

    @Id
    @GeneratedValue(generator = "territory")
    @GenericGenerator(name = "territory", strategy = "increment")
    private long id;

    @Column
    private String name;

    @Column
    @Type(type = "NodeJsonType")
    private Node startArea;

    @Column
    @Type(type = "NodeJsonType")
    private Node endArea;

    @Column
    @ElementCollection(targetClass=Node.class,fetch=FetchType.EAGER)
    private List<Node> paintedSquares;

    public boolean belongs(Node square) {
        return ((square.getX() > startArea.getX() && square.getY() > startArea.getY())
                && (square.getX() < endArea.getX() && square.getY() < endArea.getY()));
    }

    public List<Node> paintedSquares() {
        return paintedSquares;
    }

    public void paint (Node squareToPain) {
        if (paintedSquares == null) {
            paintedSquares = new ArrayList<Node>();
        }
        if (!paintedSquares.contains(squareToPain)) {
            paintedSquares.add(squareToPain);
        }
    }

    public long paintedArea() {
        if (paintedSquares == null) {
            return 0;
        } else {
            return paintedSquares.size();
        }
    }

    public boolean isSameArea(Territory other) {
        return (startArea.equals(other.getStartArea())
                && endArea.equals(other.getEndArea()));
    }

    public long proportionalPaintedArea() {
        return ((Float) ((new Float(paintedArea()) / new Float(area())) * 100)).longValue();
    }

    public long area() {
        return startArea.area() + endArea.area();
    }

    public Territory(String name, Node startArea, Node endArea) {
        this.name = name;
        this.startArea = startArea;
        this.endArea = endArea;
    }

    public Territory() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
