package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "history")
@NamedQueries({
        @NamedQuery(name = "getAllLeraningHistory", query = "SELECT h FROM History AS h ORDER BY h.id DESC")
})
@Entity
public class History {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "learned_date", nullable = false)
    private Date learmed_date;

    @Column(name = "learning_time")
    private Integer learning_time;

    @Column(name = "started_at")
    private Timestamp started_at;

    @Column(name = "finished_at")
    private Timestamp finished_at;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;
}
