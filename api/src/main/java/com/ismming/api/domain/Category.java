
package com.ismming.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Category implements Serializable {

    private static final long serialVersionUID = 9061859279002218532L;

    @JsonIgnore
    @NotNull
    private long id;
    @NotNull
    private String name;
    @JsonIgnore
    @NotNull
    private long pid;

    public Category() {
    }

    public Category(String name) {
        this.id = System.currentTimeMillis();
        this.name = name;
        this.pid = 0l;
    }

    public Category(long id, String name, long pid) {
        this.id = id;
        this.name = name;
        this.pid = pid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }
}
