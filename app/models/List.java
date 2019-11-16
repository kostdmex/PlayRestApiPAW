package models;

import io.ebean.Model;

import javax.persistence.*;

@Entity
public class List extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public Integer numberOnBoard;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    public Board board;

    @OneToMany(mappedBy = "list")
    public java.util.List<Card> cards;


    public List(String name, Integer numberOnBoard, Board board) {
        this.name = name;
        this.numberOnBoard = numberOnBoard;
        this.board = board;
    }

    @Override
    public String toString() {
        return "List{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOnBoard=" + numberOnBoard +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOnBoard() {
        return numberOnBoard;
    }

    public void setNumberOnBoard(Integer numberOnBoard) {
        this.numberOnBoard = numberOnBoard;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public java.util.List<Card> getCards() {
        return cards;
    }

    public void setCards(java.util.List<Card> cards) {
        this.cards = cards;
    }
}
