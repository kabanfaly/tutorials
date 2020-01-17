package com.kaba.tutorials.redis;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @since 16 janv. 2020 - 23:27:24
 * @author KABA N'faly
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class Capsule implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String content;
}
