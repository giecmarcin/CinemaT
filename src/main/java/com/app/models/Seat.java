package com.app.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name="Seat")
public class Seat {
    @Id
    @GeneratedValue
    private Long id;

    private int numberOfSeat;

    public Seat() {
    }

    public Seat(int numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    @Override
    public String toString() {
        String text = StringUtils.leftPad(String.valueOf(numberOfSeat), 2, "0"); // --> "005"
        return text;
    }
}
