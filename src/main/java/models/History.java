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

    @Column(name = "learning_time", nullable = false)
    private Integer learning_time;

    @Column(name = "started_at", nullable = false)
    private Timestamp started_at;

    @Column(name = "finished_at", nullable = false)
    private Timestamp finished_at;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;
}
