package com.app.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Marcin on 04.06.2016.
 */

@Getter
@Setter
public class DateOfMovie {

    private Date date;
    private Long idCinema;
}
