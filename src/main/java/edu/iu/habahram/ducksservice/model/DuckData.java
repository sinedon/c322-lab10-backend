package edu.iu.habahram.ducksservice.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(schema = "ducks")
public final class DuckData {

    @Id
    private int id;
    private String name;
    private String type;

    public DuckData() {

    }
    public DuckData(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String toLine() {
        return String.format("%1$s,%2$s,%3$s", id(), name(), type());
    }

    public String toLine(int id) {
        return String.format("%1$s,%2$s,%3$s", id, name(), type());
    }

    public static DuckData fromLine(String line) {
        String[] tokens = line.split(",");
        return new DuckData(Integer.parseInt(tokens[0]), tokens[1], tokens[2]);
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (DuckData) obj;
        return this.id == that.id &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }

    @Override
    public String toString() {
        return "DuckData[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "type=" + type + ']';
    }
}
