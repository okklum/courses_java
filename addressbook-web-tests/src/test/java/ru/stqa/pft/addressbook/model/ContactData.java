package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@XStreamAlias("contacts")
@Entity //Annotation for using object by hibernate
@Table(name = "addressbook") //for hibernate

public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id") //the name don`t need hibernate for same fields (fieldname "id" yet in DB)
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column
  private String firstname;

  @Expose
  @Column
  private String lastname;

  @Expose
  @Column(name = "home")
  @Type(type = "text") //for hibernate
  private String homePhone = "";
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone = "";
  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone = "";

  @Expose
  @Transient  //dont use this data from SQL by hibernate
  private String allPhones;

  @Expose
  @Column
  @Type(type = "text")
  private String email = "";
    @Expose
  @Column
  @Type(type = "text")
  private String email2 = "";
    @Expose
  @Column
  @Type(type = "text")
  private String email3 = "";

  @Expose
  @Transient
  private String allEMailes;

  @Expose
  @Column
  @Type(type = "text")
  private String address = "";

  @Transient
  //@Column(name = "photo")
  //@Type(type = "text")
  private String photo = "";

  @ManyToMany
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"),inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();


  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEMailes(String allEMailes) {
    this.allEMailes = allEMailes;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  /*public Set<GroupData> getGroups() {
    return groups;
  }*/
  //превращаем созданные по дефолту из параметра множества в объект данного типа
  public GroupSuite getGroups() {
    return new GroupSuite(groups);
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getHomePhone() {
    return homePhone;
  }


  public String getAllPhones() {
    return allPhones;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEMailes() {
    return allEMailes;
  }

  public String getAddress() {
    return address;
  }


  public File getPhoto() {
    if (photo == null){
      photo = "";
    }
    return new File(photo);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
    if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
    if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
    return address != null ? address.equals(that.address) : that.address == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", allEMailes='" + allEMailes + '\'' +
            ", address='" + address + '\'' +
            ", photo='" + photo + '\'' +
            '}';
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
