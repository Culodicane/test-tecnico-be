package com.lorenzo.testtecnicobackend.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "LIBRARY")
public class LibraryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LIBRARY")
    private Integer idLibrary;

    @Column(name = "DATE_ADDED")
    private LocalDate dataAdded;

    @Column(name = "DATE_DELETED")
    private LocalDate dateDeleted;

    @Column(name = "TIMES_READ")
    private Integer timesRead;
    @ManyToOne
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    private UserEntity user;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PLOT")
    private String plot;

    public Integer getIdLibrary() {
        return idLibrary;
    }

    public void setIdLibrary(Integer idLibrary) {
        this.idLibrary = idLibrary;
    }

    public LocalDate getDataAdded() {
        return dataAdded;
    }

    public void setDataAdded(LocalDate dataAdded) {
        this.dataAdded = dataAdded;
    }

    public LocalDate getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(LocalDate dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public Integer getTimesRead() {
        return timesRead;
    }

    public void setTimesRead(Integer timesRead) {
        this.timesRead = timesRead;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @Override
    public String toString() {
        return "LibraryEntity{" +
                "idLibrary=" + idLibrary +
                ", dataAdded=" + dataAdded +
                ", dateDeleted=" + dateDeleted +
                ", timesRead=" + timesRead +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", plot='" + plot + '\'' +
                '}';
    }
}
