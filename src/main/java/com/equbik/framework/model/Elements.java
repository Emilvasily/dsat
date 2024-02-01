package com.equbik.framework.model;

import lombok.*;

/**
 * Emil Vasilyev
 * emilvasily@gmail.com
 * https://www.linkedin.com/in/emilvas/
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Elements {

    /*
     * Elements class represents element described in csv file(or any other data structure)
     */

    private Integer id;
    private String flow;
    private String page;
    private String name;
    private String xpath;
    private Integer typeId;
    private String value;
    private String relatedElement;

}
