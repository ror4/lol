package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Champion extends Model {
    public String nom;
    public String ligne;
    public Integer preference;
}
