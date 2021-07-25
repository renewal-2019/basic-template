package com.zsl.template.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PersonInfo
 *
 * @author swiftzsl
 * @date 2021/7/25 14:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonInfo {
    private String name;

    private int age;
}
