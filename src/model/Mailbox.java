/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cge19
 */
@Entity
@Table(name = "MAILBOX")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mailbox.findAll", query = "SELECT m FROM Mailbox m"),
    @NamedQuery(name = "Mailbox.findByCanvasaccountid", query = "SELECT m FROM Mailbox m WHERE m.canvasaccountid = :canvasaccountid"),
    @NamedQuery(name = "Mailbox.findByEmailsender", query = "SELECT m FROM Mailbox m WHERE m.emailsender = :emailsender"),
    @NamedQuery(name = "Mailbox.findByEmailtitle", query = "SELECT m FROM Mailbox m WHERE m.emailtitle = :emailtitle"),
    @NamedQuery(name = "Mailbox.findByEmaildate", query = "SELECT m FROM Mailbox m WHERE m.emaildate = :emaildate")})
public class Mailbox implements Serializable {

    @Column(name = "EMAILTEXT")
    private String emailtext;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CANVASACCOUNTID")
    private Integer canvasaccountid;
    @Basic(optional = false)
    @Column(name = "EMAILSENDER")
    private String emailsender;
    @Column(name = "EMAILTITLE")
    private String emailtitle;
    @Basic(optional = false)
    @Column(name = "EMAILDATE")
    @Temporal(TemporalType.DATE)
    private Date emaildate;

    public Mailbox() {
    }

    public Mailbox(Integer canvasaccountid) {
        this.canvasaccountid = canvasaccountid;
    }

    public Mailbox(Integer canvasaccountid, String emailsender, Date emaildate) {
        this.canvasaccountid = canvasaccountid;
        this.emailsender = emailsender;
        this.emaildate = emaildate;
    }

    public Integer getCanvasaccountid() {
        return canvasaccountid;
    }

    public void setCanvasaccountid(Integer canvasaccountid) {
        this.canvasaccountid = canvasaccountid;
    }

    public String getEmailsender() {
        return emailsender;
    }

    public void setEmailsender(String emailsender) {
        this.emailsender = emailsender;
    }

    public String getEmailtitle() {
        return emailtitle;
    }

    public void setEmailtitle(String emailtitle) {
        this.emailtitle = emailtitle;
    }

    public Date getEmaildate() {
        return emaildate;
    }

    public void setEmaildate(Date emaildate) {
        this.emaildate = emaildate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (canvasaccountid != null ? canvasaccountid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mailbox)) {
            return false;
        }
        Mailbox other = (Mailbox) object;
        if ((this.canvasaccountid == null && other.canvasaccountid != null) || (this.canvasaccountid != null && !this.canvasaccountid.equals(other.canvasaccountid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Mailbox[ canvasaccountid=" + canvasaccountid + " ]";
    }

    public String getEmailtext() {
        return emailtext;
    }

    public void setEmailtext(String emailtext) {
        this.emailtext = emailtext;
    }
    
}