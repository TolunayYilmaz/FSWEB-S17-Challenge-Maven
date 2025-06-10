package com.workintech.spring17challenge.model;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class MediumCourseGpa implements  CourseGpa{
    @Override
    public int getGpa() {
        return 5;
    }
}
