package com.andx.micro.api.core.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by andongxu on 17-2-3.
 */
public class IdDto implements Serializable {

    @Min(0)
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
