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
        @NamedQuery(name = "getAllLearningHistory", query = "SELECT h FROM History AS h ORDER BY h.learned_date DESC"),
        @NamedQuery(name = "getHistoryCount", query = "SELECT COUNT(h) FROM History AS h")
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
    private Date learned_date;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLearned_date() {
        return learned_date;
    }

    public void setLearned_date(Date learned_date) {
        this.learned_date = learned_date;
    }

    public Integer getLearning_time() {
        return learning_time;
    }

    public void setLearning_time(Integer learning_time) {
        this.learning_time = learning_time;
    }

    public Timestamp getStarted_at() {
        return started_at;
    }

    public void setStarted_at(Timestamp started_at) {
        this.started_at = started_at;
    }

    public Timestamp getFinished_at() {
        return finished_at;
    }

    public void setFinished_at(Timestamp finished_at) {
        this.finished_at = finished_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
