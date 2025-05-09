package com.korea.dependency.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("desktop")// "desktop"이라는 이름으로 빈 등록
public class Desktop implements Computer {

    @Override
    public int getScreenWidth() {
        return 1440;
    }
}