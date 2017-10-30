/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaapp.persistence.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Person")
//@NamedQueries({
//    @NamedQuery(name ="", query = "")
//})
public class PersonVO implements Serializable {

    private Integer id;
    private String apellido1;
    private String apellido2;
    private String nombre;
    private Set<PhoneVO> phoneVOS;// = new HashSet<>();

    public PersonVO(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PersonPhone", joinColumns = { @JoinColumn(name = "personId") }, inverseJoinColumns = { @JoinColumn(name = "phoneId") })
    public Set<PhoneVO> getPhoneVOS() {
        return phoneVOS;
    }

    public void setPhoneVOS(Set<PhoneVO> phoneVOS) {
        this.phoneVOS = phoneVOS;}

    @Override
    public String toString(){

        return apellido1 + " " + apellido2 + ", " + nombre;
    }

}