package com.lorenzo.testtecnicobackend.Dtos;

import java.time.LocalDate;

public class LibraryDto {

    private Integer id;
    private LocalDate dateAdded;
    private LocalDate dateDeleted;
    private Integer timesRead;
    private Integer idUser;
    private String Title;
    private String isbn;
    private String author;
    private String plot;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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
        return "LibraryDto{" +
                "id=" + id +
                ", dateAdded=" + dateAdded +
                ", dateDeleted=" + dateDeleted +
                ", timesRead=" + timesRead +
                ", idUser=" + idUser +
                ", Title='" + Title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", plot='" + plot + '\'' +
                '}';
    }
}
